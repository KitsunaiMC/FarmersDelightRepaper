package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.ConfigUtil;
import io.github.moyusowo.farmersdelightrepaper.config.FoodConfig;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.TranslatableText;
import io.github.moyusowo.neoartisanapi.api.item.ArtisanItem;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import io.papermc.paper.datacomponent.item.FoodProperties;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffect;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public final class ItemRegistrar {
    private ItemRegistrar() {}

    public static void initOnEnable() {
        registerPlantItem();
        registerGUI();
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("foods.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                FoodConfig foodConfig = entry.getValue().get(FoodConfig.class);
                if (foodConfig == null) throw new SerializationException("why the hell is null?");
                final Material material = foodConfig.getRawMaterial();
                final Component displayName = foodConfig.getDisplayName();
                final FoodProperties foodProperties = foodConfig.getFood();
                final String itemModel = foodConfig.getItemModel();
                final Set<String> tags = foodConfig.getTags();
                final float eatTime = foodConfig.getConsumeSeconds();
                final Map<PotionEffect, Float> effects = foodConfig.getEffects();
                final NamespacedKey key;
                if (itemModel == null) key = null;
                else if (itemModel.isEmpty()) key = FarmersDelightRepaper.create(entry.getKey().toString());
                else key = NamespacedKey.fromString(itemModel);
                if (material == null) throw new SerializationException("You should fill material!");
                if (displayName == null) throw new SerializationException("You should fill displayName!");
                if (foodProperties == null) throw new SerializationException("You should fill all the food params!");
                Registries.ITEM.registerItem(
                        ArtisanItem.builder()
                                .registryId(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .rawMaterial(material)
                                .displayName(displayName)
                                .foodProperty(
                                        foodProperties.nutrition(),
                                        foodProperties.saturation(),
                                        foodProperties.canAlwaysEat(),
                                        effects,
                                        eatTime
                                )
                                .itemModel(key)
                                .tags(tags)
                                .build()
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of foods.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Item Configs loaded");
    }

    private static void registerPlantItem() {
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.onion)
                        .displayName(TranslatableText.onion)
                        .rawMaterial(Material.BEETROOT)
                        .foodProperty(
                                2,
                                1.6f,
                                true
                        )
                        .itemModel(Keys.onion)
                        .blockId(Keys.onion)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.tomato)
                        .displayName(TranslatableText.tomato)
                        .rawMaterial(Material.BEETROOT)
                        .foodProperty(
                                1,
                                0.6f,
                                false
                        )
                        .itemModel(Keys.tomato)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.rice)
                        .displayName(TranslatableText.rice)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.rice)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cabbage)
                        .displayName(TranslatableText.cabbage)
                        .rawMaterial(Material.BEETROOT)
                        .foodProperty(
                                2,
                                1.6f,
                                false
                        )
                        .itemModel(Keys.cabbage)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.rice_panicle)
                        .displayName(TranslatableText.rice_panicle)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.rice_panicle)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cabbage_leaf)
                        .displayName(TranslatableText.cabbage_leaf)
                        .rawMaterial(Material.BEETROOT)
                        .foodProperty(
                                1,
                                0.8f,
                                false
                        )
                        .itemModel(Keys.cabbage_leaf)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.tomato_seed)
                        .displayName(TranslatableText.tomato_seed)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.tomato_seed)
                        .blockId(Keys.tomato)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cabbage_seed)
                        .displayName(TranslatableText.cabbage_seed)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.cabbage_seed)
                        .blockId(Keys.cabbage)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cooking_pot)
                        .rawMaterial(Material.PAPER)
                        .displayName(TranslatableText.cooking_pot)
                        .blockId(Keys.cooking_pot)
                        .itemModel(Keys.cooking_pot)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cutting_board)
                        .rawMaterial(Material.PAPER)
                        .displayName(TranslatableText.cutting_board)
                        .blockId(Keys.cutting_board)
                        .itemModel(Keys.cutting_board)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.flint_knife)
                        .rawMaterial(Material.STONE_SWORD)
                        .displayName(TranslatableText.flint_knife)
                        .itemModel(Keys.flint_knife)
                        .maxDurability(131)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.iron_knife)
                        .rawMaterial(Material.IRON_SWORD)
                        .displayName(TranslatableText.iron_knife)
                        .itemModel(Keys.iron_knife)
                        .maxDurability(250)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.golden_knife)
                        .rawMaterial(Material.GOLDEN_SWORD)
                        .displayName(TranslatableText.gold_knife)
                        .itemModel(Keys.golden_knife)
                        .maxDurability(32)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.diamond_knife)
                        .rawMaterial(Material.DIAMOND_SWORD)
                        .displayName(TranslatableText.diamond_knife)
                        .itemModel(Keys.diamond_knife)
                        .maxDurability(1561)
                        .build()
        );
        Registries.ITEM.registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.netherite_knife)
                        .rawMaterial(Material.NETHERITE_SWORD)
                        .displayName(TranslatableText.netherite_knife)
                        .itemModel(Keys.netherite_knife)
                        .maxDurability(2031)
                        .build()
        );
    }

    private static void registerGUI() {
        for (NamespacedKey key : Keys.cooking_pot_gui_progress) {
            Registries.ITEM.registerItem(
                    ArtisanItem.builder()
                            .registryId(key)
                            .rawMaterial(Material.PAPER)
                            .displayName("")
                            .itemModel(key)
                            .internalUse()
                            .build()
            );
        }
        for (NamespacedKey key : Keys.cooking_pot_gui_fire_progress) {
            Registries.ITEM.registerItem(
                    ArtisanItem.builder()
                            .registryId(key)
                            .rawMaterial(Material.PAPER)
                            .displayName("")
                            .itemModel(key)
                            .internalUse()
                            .build()
            );
        }
    }

}
