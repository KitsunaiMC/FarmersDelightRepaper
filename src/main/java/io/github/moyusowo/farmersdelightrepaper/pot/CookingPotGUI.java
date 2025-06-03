package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.SoundKey;
import io.github.moyusowo.farmersdelightrepaper.resource.TranslatableText;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.gui.ArtisanBlockGUI;
import io.github.moyusowo.neoartisanapi.api.persistence.type.ItemStackDataType;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class CookingPotGUI extends ArtisanBlockGUI {

    private static final NamespacedKey isBurning = FarmersDelightRepaper.create("cutting_board.is_burning");
    private static final NamespacedKey progress = FarmersDelightRepaper.create("cutting_board.progress");
    private static final NamespacedKey time = FarmersDelightRepaper.create("cutting_board.time");
    private static final NamespacedKey[] items = new NamespacedKey[] {
            FarmersDelightRepaper.create("cutting_board.item_1"),
            FarmersDelightRepaper.create("cutting_board.item_2"),
            FarmersDelightRepaper.create("cutting_board.item_3"),
            FarmersDelightRepaper.create("cutting_board.item_4"),
            FarmersDelightRepaper.create("cutting_board.item_5"),
            FarmersDelightRepaper.create("cutting_board.item_6")
    };
    private static final NamespacedKey bowl = FarmersDelightRepaper.create("cutting_board.has_bowl");
    private static final NamespacedKey product = FarmersDelightRepaper.create("cutting_board.product");

    private final BukkitRunnable burningTask, soundTask, savingTask, cookingTask;

    private static final List<Integer> itemSlot = List.of(
            10, 11, 12,
            19, 20, 21
    );
    private static final int bowlSlot = 33, resultSlot = 15;

    protected CookingPotGUI(Location location) {
        super(FarmersDelightRepaper.getInstance(), 45, TranslatableText.container_cooking_pot, location);
        FarmersDelightRepaper.getInstance().getLogger().info(location.toString());
        this.burningTask = generateBurningTask();
        this.soundTask = generateSoundTask();
        this.savingTask = generateSavingTask();
        this.cookingTask = generateCookingTask();
    }

    @Override
    protected void setItemInInventory() {
        inventory.setItem(0, NeoArtisanAPI.getItemRegistry().getItemStack(Keys.cooking_pot_gui_1));
        for (int i = 0; i < items.length; i++) {
            inventory.setItem(itemSlot.get(i), getArtisanBlockData().getPersistentDataContainer().getOrDefault(items[i], ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        }
        inventory.setItem(bowlSlot, getArtisanBlockData().getPersistentDataContainer().getOrDefault(bowl, ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        inventory.setItem(resultSlot, getArtisanBlockData().getPersistentDataContainer().getOrDefault(product, ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        this.soundTask.runTaskTimer(FarmersDelightRepaper.getInstance(), 0L, 20L * 4);
        this.burningTask.runTaskTimer(FarmersDelightRepaper.getInstance(), 0L, 1L);
        this.savingTask.runTaskTimer(FarmersDelightRepaper.getInstance(), 20L, 1L);
        this.cookingTask.runTaskTimer(FarmersDelightRepaper.getInstance(), 0L, 1L);
    }

    private BukkitRunnable generateCookingTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                final int timeValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(time, PersistentDataType.INTEGER, 0);
                final CookingPotRecipe r = getInventoryRecipe();
                final CookingPotGenerator g = CookingPotRecipe.matches(r);
                final ItemStack res = inventory.getItem(resultSlot);
                final boolean isBurningValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(isBurning, PersistentDataType.BOOLEAN, false);
                if (isBurningValue && g != null && (res == null || (g.generate().isSimilar(res) && g.generate().getAmount() + res.getAmount() <= res.getMaxStackSize()))) {
                    if (timeValue < g.time()) {
                        getArtisanBlockData().getPersistentDataContainer().set(time, PersistentDataType.INTEGER, timeValue + 1);
                        getArtisanBlockData().getPersistentDataContainer().set(progress, PersistentDataType.INTEGER, (int) ((double) timeValue / (double) g.time() * 20.0));
                    } else {
                        getArtisanBlockData().getPersistentDataContainer().remove(progress);
                        getArtisanBlockData().getPersistentDataContainer().remove(time);
                        for (int i = 0; i < 6; i++) {
                            final ItemStack itemStack = inventory.getItem(itemSlot.get(i));
                            if (itemStack != null) {
                                itemStack.setAmount(itemStack.getAmount() - 1);
                            }
                        }
                        final ItemStack bowl = inventory.getItem(bowlSlot);
                        if (bowl != null) {
                            bowl.setAmount(bowl.getAmount() - 1);
                        }
                        inventory.setItem(resultSlot, g.generate());
                    }
                } else {
                    getArtisanBlockData().getPersistentDataContainer().remove(progress);
                    getArtisanBlockData().getPersistentDataContainer().remove(time);
                }
            }
        };
    }

    private BukkitRunnable generateSoundTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                final boolean isBurningValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(isBurning, PersistentDataType.BOOLEAN, false);
                final int progressValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(progress, PersistentDataType.INTEGER, 0);
                if (isBurningValue) {
                    if (progressValue > 0) {
                        location.getWorld().playSound(
                                Sound.sound().type(SoundKey.cooking_pot_boil_soup)
                                        .source(Sound.Source.BLOCK)
                                        .pitch(1.0f)
                                        .volume(1.5f)
                                        .build(),
                                location.x(),
                                location.y(),
                                location.z()
                        );
                    } else {
                        location.getWorld().playSound(
                                Sound.sound().type(SoundKey.cooking_pot_boil)
                                        .source(Sound.Source.BLOCK)
                                        .pitch(1.0f)
                                        .volume(1.5f)
                                        .build(),
                                location.x(),
                                location.y(),
                                location.z()
                        );
                    }
                }
            }
        };
    }

    private BukkitRunnable generateBurningTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (location.getBlock().getRelative(BlockFace.DOWN).getType() == Material.CAMPFIRE ||
                    location.getBlock().getRelative(BlockFace.DOWN).getType() == Material.FIRE) {
                    getArtisanBlockData().getPersistentDataContainer().set(isBurning, PersistentDataType.BOOLEAN, true);
                    location.getWorld().spawnParticle(
                            Particle.DUST,
                            location.clone().add(0.5, 0.6, 0.5),
                            2,
                            0.10, 0.05, 0.10,
                            0.05, // 上升速度
                            new Particle.DustOptions(Color.fromARGB(255, 255, 255, 255), 0.5f)
                    );
                    final int progressValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(progress, PersistentDataType.INTEGER, 0);
                    inventory.setItem(0, NeoArtisanAPI.getItemRegistry().getItemStack(Keys.cooking_pot_gui_fire_progress.get(progressValue)));
                } else {
                    getArtisanBlockData().getPersistentDataContainer().set(isBurning, PersistentDataType.BOOLEAN, false);
                    final int progressValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(progress, PersistentDataType.INTEGER, 0);
                    inventory.setItem(0, NeoArtisanAPI.getItemRegistry().getItemStack(Keys.cooking_pot_gui_progress.get(progressValue)));
                }
            }
        };
    }

    @Override
    public void onTerminate() {
        HandlerList.unregisterAll(this);
        this.burningTask.cancel();
        this.soundTask.cancel();
        this.savingTask.cancel();
        this.cookingTask.cancel();
    }

    @Override
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getInventory().getHolder(false) instanceof CookingPotGUI)) return;
        if (event.getClickedInventory().getHolder(false) instanceof CookingPotGUI) {
            if (itemSlot.contains(event.getSlot())) {
                return;
            }
            if (event.getSlot() == bowlSlot && event.getCursor().getType() == Material.BOWL) {
                return;
            }
            if (event.getSlot() == bowlSlot && event.getCursor().isEmpty()) {
                return;
            }
            if (event.getSlot() == resultSlot) {
                ItemStack result = inventory.getItem(resultSlot);
                if (result != null) {
                    if (event.isShiftClick()) {
                        Map<Integer, ItemStack> leftovers = event.getWhoClicked().getInventory().addItem(result.clone());
                        if (!leftovers.isEmpty()) {
                            ItemStack reduce = result.clone();
                            reduce.setAmount(reduce.getAmount() - leftovers.get(0).getAmount());
                            event.getWhoClicked().getInventory().removeItem(reduce);
                        } else {
                            inventory.setItem(resultSlot, null);
                        }
                    } else if (event.getCursor().getType() != Material.AIR && event.getCursor().isSimilar(result) && event.getCursor().getAmount() + result.getAmount() < event.getCursor().getMaxStackSize()) {
                        inventory.setItem(resultSlot, null);
                        event.getCursor().setAmount(event.getCursor().getAmount() + result.getAmount());
                    } else if (event.getCursor().getType() == Material.AIR) {
                        inventory.setItem(resultSlot, null);
                        event.getWhoClicked().setItemOnCursor(result.clone());
                    }
                }
            }
        } else {
            if (!event.isShiftClick()) {
                return;
            }
        }
        event.setCancelled(true);
    }

    @Override
    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!(event.getInventory().getHolder(false) instanceof CookingPotGUI)) return;
        if (event.getInventory() == inventory) {
            for (Integer slot : event.getInventorySlots()) {
                if (!itemSlot.contains(slot)) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }

    private BukkitRunnable generateSavingTask() {
        return new BukkitRunnable() {
            private void saveItems() {
                for (int i = 0; i < itemSlot.size(); i++) {
                    ItemStack itemStack = inventory.getItem(itemSlot.get(i));
                    if (itemStack != null) getArtisanBlockData().getPersistentDataContainer().set(items[i], ItemStackDataType.ITEM_STACK, itemStack);
                    else getArtisanBlockData().getPersistentDataContainer().remove(items[i]);
                }
            }

            private void saveBowl() {
                ItemStack itemStack = inventory.getItem(bowlSlot);
                if (itemStack != null) getArtisanBlockData().getPersistentDataContainer().set(bowl, ItemStackDataType.ITEM_STACK, itemStack.clone());
                else getArtisanBlockData().getPersistentDataContainer().remove(bowl);
            }

            @Override
            public void run() {
                saveBowl();
                saveItems();
            }
        };
    }

    private CookingPotRecipe getInventoryRecipe() {
        boolean hasBowl = inventory.getItem(bowlSlot) != null && (!inventory.getItem(bowlSlot).isEmpty());
        return new CookingPotRecipe(
                hasBowl,
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(0))),
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(1))),
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(2))),
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(3))),
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(4))),
                NeoArtisanAPI.getItemRegistry().getRegistryId(inventory.getItem(itemSlot.get(5)))
        );
    }
}
