package io.github.moyusowo.farmersdelightrepaper.board;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.SoundKey;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.base.ArtisanBlockData;
import io.github.moyusowo.neoartisanapi.api.block.event.ArtisanBlockBreakEvent;
import io.github.moyusowo.neoartisanapi.api.block.event.ArtisanBlockLoseSupportEvent;
import io.github.moyusowo.neoartisanapi.api.block.event.ArtisanBlockPlaceEvent;
import io.github.moyusowo.neoartisanapi.api.block.storage.ArtisanBlockStorage;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public final class CuttingBoardBehavior implements Listener {

    private static final Map<NamespacedKey, ItemGenerator> cuttingBoardRecipe = new HashMap<>();
    private static final String entityTag = "cutting_board_display";
    private static final Vector offset = new Vector(0.5, 0.0625, 0.5);

    private CuttingBoardBehavior() {}

    private static ItemDisplay getItemDisplay(Block block) {
        for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation().add(offset), 0.01, 0.01, 0.01)) {
            if (entity.getType() == EntityType.ITEM_DISPLAY && entity.getScoreboardTags().contains(entityTag)) {
                return (ItemDisplay) entity;
            }
        }
        return null;
    }

    public static void init() {
        Bukkit.getPluginManager().registerEvents(new CuttingBoardBehavior(), FarmersDelightRepaper.getInstance());
        cuttingBoardRecipe.put(Keys.cabbage, ItemGenerator.simpleGenerator(Keys.cabbage_leaf, 2));
    }

    @EventHandler
    public static void onPlace(ArtisanBlockPlaceEvent event) {
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
    }

    /*
    * 左键：没东西就破坏，有东西就把东西打掉
    * 空手+右键：无
    * 非空手+右键：没东西就放东西，有东西就切
    * */
    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.DENY) return;
        if (event.useItemInHand() == Event.Result.DENY) return;
        if (event.getClickedBlock() == null) return;
        if (!NeoArtisanAPI.getArtisanBlockStorage().isArtisanBlock(event.getClickedBlock())) return;
        ArtisanBlockData artisanBlockData = NeoArtisanAPI.getArtisanBlockStorage().getArtisanBlockData(event.getClickedBlock());
        if (!artisanBlockData.getArtisanBlock().getBlockId().equals(Keys.cutting_board)) return;
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
        } else if (event.getItem() != null && event.getAction().isRightClick()) {
            if (itemDisplay.getItemStack().isEmpty()) {
                event.setCancelled(true);
                ItemStack itemStack = event.getItem().clone();
                itemStack.setAmount(1);
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                }
                itemDisplay.setItemStack(itemStack);
                event.getClickedBlock().getWorld().playSound(
                        event.getClickedBlock().getLocation(),
                        org.bukkit.Sound.BLOCK_WOOD_PLACE,
                        1.0f,
                        1.0f
                );
            } else if (Keys.knife.contains(NeoArtisanAPI.getItemRegistry().getRegistryId(event.getItem()))) {
                if (cuttingBoardRecipe.containsKey(NeoArtisanAPI.getItemRegistry().getRegistryId(itemDisplay.getItemStack()))) {
                    event.setCancelled(true);
                    event.getClickedBlock().getWorld().dropItemNaturally(
                            event.getClickedBlock().getLocation().toBlockLocation(),
                            cuttingBoardRecipe.get(NeoArtisanAPI.getItemRegistry().getRegistryId(itemDisplay.getItemStack())).generate()
                    );
                    itemDisplay.setItemStack(null);
                    if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                        Integer damage = event.getItem().getData(DataComponentTypes.DAMAGE);
                        if (damage == null) {
                            event.getItem().setData(DataComponentTypes.DAMAGE, 1);
                        } else {
                            event.getItem().setData(DataComponentTypes.DAMAGE, damage + 1);
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

    @EventHandler
    public static void onBreak(ArtisanBlockBreakEvent event) {
        if (!event.getArtisanBlock().getBlockId().equals(Keys.cutting_board)) return;
        ItemDisplay itemDisplay = getItemDisplay(event.getBlock());
        if (itemDisplay == null) return;
        itemDisplay.remove();
    }

    @EventHandler
    public static void onBelowBreak(ArtisanBlockLoseSupportEvent event) {
        if (!event.getArtisanBlock().getBlockId().equals(Keys.cutting_board)) return;
        ItemDisplay itemDisplay = getItemDisplay(event.getBlock());
        if (itemDisplay == null) return;
        itemDisplay.remove();
    }

    @EventHandler
    public static void onBlockBreakCuttingBoard(BlockBreakBlockEvent event) {
        if (!NeoArtisanAPI.getArtisanBlockStorage().isArtisanBlock(event.getBlock())) return;
        if (!NeoArtisanAPI.getArtisanBlockStorage().getArtisanBlockData(event.getBlock()).getArtisanBlock().getBlockId().equals(Keys.cutting_board)) return;
        ItemDisplay itemDisplay = getItemDisplay(event.getBlock());
        if (itemDisplay == null) return;
        itemDisplay.remove();
    }
}
