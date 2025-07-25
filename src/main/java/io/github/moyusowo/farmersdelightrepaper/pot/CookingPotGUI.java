package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.SoundKey;
import io.github.moyusowo.farmersdelightrepaper.resource.TranslatableText;
import io.github.moyusowo.neoartisanapi.api.block.gui.ArtisanBlockGUI;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanRecipe;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import io.github.moyusowo.neoartisanapi.api.util.ItemStackDataType;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private static final NamespacedKey expSave = FarmersDelightRepaper.create("cutting_board.exp_save");

    private static final List<Integer> itemSlot = List.of(
            10, 11, 12,
            19, 20, 21
    );
    private static final int bowlSlot = 33, resultSlot = 15;

    public CookingPotGUI(Location location) {
        super(FarmersDelightRepaper.getInstance(), 45, TranslatableText.container_cooking_pot, location);
    }

    private void cookingTask() {
        final int timeValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(time, PersistentDataType.INTEGER, 0);
        final Optional<CookingPotRecipe> recipe = getInventoryRecipe();
        final ItemStack res = inventory.getItem(resultSlot);
        final boolean isBurningValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(isBurning, PersistentDataType.BOOLEAN, false);
        if (isBurningValue && recipe.isPresent() && (res == null || (recipe.get().getResultGenerator().generate().isSimilar(res) && recipe.get().getResultGenerator().generate().getAmount() + res.getAmount() <= res.getMaxStackSize()))) {
            if (timeValue < recipe.get().getTime()) {
                getArtisanBlockData().getPersistentDataContainer().set(time, PersistentDataType.INTEGER, timeValue + 1);
                getArtisanBlockData().getPersistentDataContainer().set(progress, PersistentDataType.INTEGER, (int) ((double) timeValue / (double) recipe.get().getTime() * 20.0));
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
                inventory.setItem(resultSlot, recipe.get().getResultGenerator().generate());
                getArtisanBlockData().getPersistentDataContainer().set(expSave, PersistentDataType.FLOAT, recipe.get().getExp());
            }
        } else {
            getArtisanBlockData().getPersistentDataContainer().remove(progress);
            getArtisanBlockData().getPersistentDataContainer().remove(time);
        }
    }

    private void soundTask() {
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

    private void burningTask() {
        final Material material = location.getBlock().getRelative(BlockFace.DOWN).getType();
        if (material == Material.CAMPFIRE) {
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
            inventory.setItem(0, Registries.ITEM.getItemStack(Keys.cooking_pot_gui_fire_progress.get(progressValue)));
        } else {
            getArtisanBlockData().getPersistentDataContainer().set(isBurning, PersistentDataType.BOOLEAN, false);
            final int progressValue = getArtisanBlockData().getPersistentDataContainer().getOrDefault(progress, PersistentDataType.INTEGER, 0);
            inventory.setItem(0, Registries.ITEM.getItemStack(Keys.cooking_pot_gui_progress.get(progressValue)));
        }
    }

    @Override
    public void terminate() {
        final ItemStack[] itemStacks = new ItemStack[6];
        for (int i = 0; i < 6; i++) {
            itemStacks[i] = ItemStack.empty();
        }
        for (int i = 0; i < 6; i++) {
            if (getArtisanBlockData().getPersistentDataContainer().has(items[i])) {
                itemStacks[i] = getArtisanBlockData().getPersistentDataContainer().get(items[i], ItemStackDataType.ITEM_STACK);
                getArtisanBlockData().getPersistentDataContainer().remove(items[i]);
            }
        }
        for (ItemStack itemStack : itemStacks) {
            if (!itemStack.isEmpty()) {
                location.getWorld().dropItemNaturally(
                        location.add(0, 1, 0),
                        itemStack
                );
            }
        }
    }

    @Override
    protected void init() {
        inventory.setItem(0, Registries.ITEM.getItemStack(Keys.cooking_pot_gui_1));
        for (int i = 0; i < items.length; i++) {
            inventory.setItem(itemSlot.get(i), getArtisanBlockData().getPersistentDataContainer().getOrDefault(items[i], ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        }
        inventory.setItem(bowlSlot, getArtisanBlockData().getPersistentDataContainer().getOrDefault(bowl, ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        inventory.setItem(resultSlot, getArtisanBlockData().getPersistentDataContainer().getOrDefault(product, ItemStackDataType.ITEM_STACK, ItemStack.empty()));
        addLifecycleTask(this::soundTask, 0L, 20L * 4, false, false);
        addLifecycleTask(this::burningTask, 0L, 1L, false, false);
        addLifecycleTask(this::savingTask, 20L, 1L, false, false);
        addLifecycleTask(this::cookingTask, 0L, 1L, false, false);
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
                            getArtisanBlockData().getPersistentDataContainer().remove(expSave);
                        }
                    } else if (event.getCursor().getType() != Material.AIR && event.getCursor().isSimilar(result) && event.getCursor().getAmount() + result.getAmount() < event.getCursor().getMaxStackSize()) {
                        inventory.setItem(resultSlot, null);
                        getArtisanBlockData().getPersistentDataContainer().remove(expSave);
                        event.getCursor().setAmount(event.getCursor().getAmount() + result.getAmount());
                    } else if (event.getCursor().getType() == Material.AIR) {
                        inventory.setItem(resultSlot, null);
                        getArtisanBlockData().getPersistentDataContainer().remove(expSave);
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

    private void savingTask() {
        ItemStack bowlStack = inventory.getItem(bowlSlot);
        if (bowlStack != null) getArtisanBlockData().getPersistentDataContainer().set(bowl, ItemStackDataType.ITEM_STACK, bowlStack.clone());
        else getArtisanBlockData().getPersistentDataContainer().remove(bowl);
        for (int i = 0; i < itemSlot.size(); i++) {
            ItemStack itemStack = inventory.getItem(itemSlot.get(i));
            if (itemStack != null) getArtisanBlockData().getPersistentDataContainer().set(items[i], ItemStackDataType.ITEM_STACK, itemStack);
            else getArtisanBlockData().getPersistentDataContainer().remove(items[i]);
        }
    }

    private Optional<CookingPotRecipe> getInventoryRecipe() {
        ItemStack bowl = inventory.getItem(bowlSlot);
        final ItemStack[] matrix = new ItemStack[] {
                inventory.getItem(itemSlot.get(0)),
                inventory.getItem(itemSlot.get(1)),
                inventory.getItem(itemSlot.get(2)),
                inventory.getItem(itemSlot.get(3)),
                inventory.getItem(itemSlot.get(4)),
                inventory.getItem(itemSlot.get(5)),
                bowl
        };
        final Collection<ArtisanRecipe> cookingRecipes = Registries.RECIPE.getRecipesByType(CookingPotRecipe.TYPE);
        for (ArtisanRecipe recipe : cookingRecipes) {
            if (recipe instanceof CookingPotRecipe cookingPotRecipe) {
                if (cookingPotRecipe.matches(matrix)) {
                    return Optional.of(cookingPotRecipe);
                }
            }
        }
        return Optional.empty();
    }
}
