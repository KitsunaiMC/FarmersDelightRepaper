package io.github.moyusowo.farmersdelightrepaper.board;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.SoundKey;
import io.github.moyusowo.neoartisanapi.api.block.event.common.ArtisanBlockPlaceEvent;
import io.github.moyusowo.neoartisanapi.api.block.storage.Storages;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanRecipe;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;

public final class CuttingBoardBehavior implements Listener {

    private static final String entityTag = "cutting_board_display";
    private static final Vector offset = new Vector(0.5, 0.0625, 0.5);

    private CuttingBoardBehavior() {}

    private static ItemDisplay getItemDisplay(Block block) {
        for (Entity entity : block.getLocation().add(offset).getNearbyEntities(0.01, 0.01, 0.01)) {
            if (entity.getType() == EntityType.ITEM_DISPLAY && entity.getScoreboardTags().contains(entityTag)) {
                return (ItemDisplay) entity;
            }
        }
        return null;
    }

    public static void initOnEnable() {
        Bukkit.getPluginManager().registerEvents(new CuttingBoardBehavior(), FarmersDelightRepaper.getInstance());
    }

    @EventHandler
    public void onPlace(ArtisanBlockPlaceEvent event) {
        if (!event.getArtisanBlock().getBlockId().equals(Keys.cutting_board)) return;
        ItemDisplay itemDisplay = (ItemDisplay) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation().add(offset), EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(null);
        Transformation transformation = itemDisplay.getTransformation();
        Quaternionf rotation = new Quaternionf().rotateX((float) Math.toRadians(90));
        Vector3f scale = new Vector3f(0.5f, 0.5f, 0.5f);
        transformation.getLeftRotation().set(rotation);
        transformation.getScale().set(scale);
        itemDisplay.setTransformation(transformation);
        itemDisplay.addScoreboardTag(entityTag);
        itemDisplay.setPersistent(true);
        event.getPlacedArtisanBlockData().getLifecycleTaskManager().addTerminateRunnable(itemDisplay::remove);
    }

    /*
    * 左键：没东西就破坏，有东西就把东西打掉
    * 空手+右键：无
    * 非空手+右键：没东西就放东西，有东西就切
    * */
    @SuppressWarnings("UnstableApiUsage")
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.DENY || event.useItemInHand() == Event.Result.DENY) return;
        if (event.getClickedBlock() == null) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (!Storages.BLOCK.isArtisanBlock(event.getClickedBlock())) return;
        if (!Storages.BLOCK.getArtisanBlockData(event.getClickedBlock()).blockId().equals(Keys.cutting_board)) return;
        ItemDisplay itemDisplay = getItemDisplay(event.getClickedBlock());
        if (itemDisplay == null) return;
        if (event.getAction().isLeftClick()) {
            if (!itemDisplay.getItemStack().isEmpty()) {
                event.setCancelled(true);
                ItemStack itemStack = itemDisplay.getItemStack().clone();
                itemStack.setAmount(1);
                itemDisplay.setItemStack(null);
                event.getClickedBlock().getWorld().dropItemNaturally(
                        event.getClickedBlock().getLocation().toBlockLocation(),
                        itemStack
                );
            }
        } else if (!event.getPlayer().getInventory().getItemInMainHand().isEmpty() && event.getAction().isRightClick()) {
            final ItemStack offHandItem = event.getPlayer().getInventory().getItemInOffHand();
            final ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();
            final Optional<CuttingBoardRecipe> offHandRecipe = findMatch(offHandItem);
            if (Registries.ITEM.getTagsByItemStack(mainHandItem).contains("knife") && offHandRecipe.isPresent()) {
                event.setCancelled(true);
                final int amount;
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    Integer damage = mainHandItem.getDataOrDefault(DataComponentTypes.DAMAGE, 0);
                    Integer maxDamage = mainHandItem.getData(DataComponentTypes.MAX_DAMAGE);
                    if (maxDamage == null) return;
                    else amount = Math.min(maxDamage - damage, offHandItem.getAmount());
                } else {
                    amount = offHandItem.getAmount();
                }
                List<ItemGenerator> itemGenerators = offHandRecipe.get().getResultGenerators();
                for (int i = 0; i < amount; i++) {
                    for (ItemGenerator itemGenerator : itemGenerators) {
                        event.getClickedBlock().getWorld().dropItemNaturally(
                                event.getClickedBlock().getLocation().toBlockLocation(),
                                itemGenerator.generate()
                        );
                    }
                }
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    offHandItem.setAmount(offHandItem.getAmount() - amount);
                    Integer damage = mainHandItem.getDataOrDefault(DataComponentTypes.DAMAGE, 0);
                    mainHandItem.setData(DataComponentTypes.DAMAGE, damage + amount);
                }
                event.getClickedBlock().getWorld().playSound(
                        net.kyori.adventure.sound.Sound.sound().type(SoundKey.cutting_board_knife_cut)
                                .source(Sound.Source.BLOCK)
                                .pitch(1.0f)
                                .volume(1.5f)
                                .build(),
                        event.getClickedBlock().getX(),
                        event.getClickedBlock().getY(),
                        event.getClickedBlock().getZ()
                );
            } else if (itemDisplay.getItemStack().isEmpty()) {
                event.setCancelled(true);
                ItemStack itemStack = mainHandItem.clone();
                itemStack.setAmount(1);
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    mainHandItem.setAmount(mainHandItem.getAmount() - 1);
                }
                itemDisplay.setItemStack(itemStack);
                event.getClickedBlock().getWorld().playSound(
                        event.getClickedBlock().getLocation(),
                        org.bukkit.Sound.BLOCK_WOOD_PLACE,
                        1.0f,
                        1.0f
                );
            } else if (Registries.ITEM.getTagsByItemStack(mainHandItem).contains("knife")) {
                Optional<CuttingBoardRecipe> recipe = findMatch(itemDisplay.getItemStack());
                if (recipe.isPresent()) {
                    event.setCancelled(true);
                    List<ItemGenerator> itemGenerators = recipe.get().getResultGenerators();
                    for (ItemGenerator itemGenerator : itemGenerators) {
                        event.getClickedBlock().getWorld().dropItemNaturally(
                                event.getClickedBlock().getLocation().toBlockLocation(),
                                itemGenerator.generate()
                        );
                    }
                    itemDisplay.setItemStack(null);
                    if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                        Integer damage = mainHandItem.getData(DataComponentTypes.DAMAGE);
                        if (damage == null) {
                            mainHandItem.setData(DataComponentTypes.DAMAGE, 1);
                        } else {
                            mainHandItem.setData(DataComponentTypes.DAMAGE, damage + 1);
                        }
                    }
                    event.getClickedBlock().getWorld().playSound(
                            net.kyori.adventure.sound.Sound.sound().type(SoundKey.cutting_board_knife_cut)
                                    .source(Sound.Source.BLOCK)
                                    .pitch(1.0f)
                                    .volume(1.5f)
                                    .build(),
                            event.getClickedBlock().getX(),
                            event.getClickedBlock().getY(),
                            event.getClickedBlock().getZ()
                    );
                }
            }
        }
    }

    private static Optional<CuttingBoardRecipe> findMatch(@Nullable ItemStack itemStack1) {
        if (itemStack1 == null || itemStack1.isEmpty()) return Optional.empty();
        ItemStack itemStack = itemStack1.clone();
        final Collection<ArtisanRecipe> cuttingRecipes = Registries.RECIPE.getRecipesByType(CuttingBoardRecipe.TYPE);
        for (ArtisanRecipe recipe : cuttingRecipes) {
            if (recipe instanceof CuttingBoardRecipe cuttingBoardRecipe) {
                if (cuttingBoardRecipe.matches(itemStack)) {
                    return Optional.of(cuttingBoardRecipe);
                }
            }
        }
        return Optional.empty();
    }
}
