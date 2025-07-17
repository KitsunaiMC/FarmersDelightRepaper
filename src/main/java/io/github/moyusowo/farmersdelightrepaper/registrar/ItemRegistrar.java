package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.ConfigUtil;
import io.github.moyusowo.farmersdelightrepaper.config.FoodConfig;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.resource.TranslatableText;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ArtisanItem;
import io.papermc.paper.datacomponent.item.FoodProperties;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
public final class ItemRegistrar {

    private static final Map<NamespacedKey, FoodConfig> configs = new HashMap<>();

    private ItemRegistrar() {}

    public static void initOnLoad() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("foods.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                FoodConfig foodConfig = entry.getValue().get(FoodConfig.class);
                if (foodConfig == null) throw new SerializationException("why the hell is null?");
                final Material material = foodConfig.getRawMaterial();
                final TranslatableComponent translatable = foodConfig.getTranslatable();
                final Component displayName = foodConfig.getDisplayName();
                final FoodProperties foodProperties = foodConfig.getFood();
                if (material == null) throw new SerializationException("You should fill material!");
                if (translatable == null && displayName == null) throw new SerializationException("You should fill translatable or displayName!");
                if (foodProperties == null) throw new SerializationException("You should fill all the food params!");
                configs.put(FarmersDelightRepaper.create(entry.getKey().toString()), foodConfig);
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of foods.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Item Configs loaded");
    }

    @NeoArtisanAPI.Register
    public static void register() {
        for (Map.Entry<NamespacedKey, FoodConfig> entry : configs.entrySet()) {
            final NamespacedKey key = entry.getKey();
            final FoodConfig config = entry.getValue();
            final ArtisanItem.Builder builder = ArtisanItem.builder();
            assert config.getRawMaterial() != null && config.getFood() != null;
            builder.registryId(key).rawMaterial(config.getRawMaterial()).foodProperty(config.getFood().nutrition(), config.getFood().saturation(), config.getFood().canAlwaysEat());
            if (config.getTranslatable() != null) builder.displayName(config.getTranslatable());
            else {
                assert config.getDisplayName() != null;
                builder.displayName(config.getDisplayName());
            }
            if (config.getItemModel() != null) {
                if (config.getItemModel().isEmpty()) builder.itemModel(key);
                else builder.itemModel(NamespacedKey.fromString(config.getItemModel()));
            }
            NeoArtisanAPI.getItemRegistry().registerItem(builder.build());
        }
        NeoArtisanAPI.getItemRegistry().registerItem(
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
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
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
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.rice)
                        .displayName(TranslatableText.rice)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.rice)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
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
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.rice_panicle)
                        .displayName(TranslatableText.rice_panicle)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.rice_panicle)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.tomato_sauce)
                        .displayName(TranslatableText.tomato_sauce)
                        .rawMaterial(Material.MUSHROOM_STEW)
                        .foodProperty(
                                2,
                                3.2f,
                                false
                        )
                        .itemModel(Keys.tomato_sauce)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.raw_pasta)
                        .displayName(TranslatableText.raw_pasta)
                        .rawMaterial(Material.CHICKEN)
                        .foodProperty(
                                3,
                                1.2f,
                                false
                        )
                        .itemModel(Keys.raw_pasta)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.pumpkin_slice)
                        .displayName(TranslatableText.pumpkin_slice)
                        .rawMaterial(Material.PUMPKIN_PIE)
                        .foodProperty(
                                3,
                                1.8f,
                                false
                        )
                        .itemModel(Keys.pumpkin_slice)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
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
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.tomato_seed)
                        .displayName(TranslatableText.tomato_seed)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.tomato_seed)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cabbage_seed)
                        .displayName(TranslatableText.cabbage_seed)
                        .rawMaterial(Material.WHEAT)
                        .itemModel(Keys.cabbage_seed)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.minced_beef)
                        .displayName(TranslatableText.minced_beef)
                        .rawMaterial(Material.BEEF)
                        .foodProperty(
                                2,
                                1.2f,
                                false
                        )
                        .itemModel(Keys.minced_beef)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.raw_chicken_cuts)
                        .displayName(TranslatableText.raw_chicken_cuts)
                        .rawMaterial(Material.CHICKEN)
                        .foodProperty(
                                1,
                                0.6f,
                                false
                        )
                        .itemModel(Keys.raw_chicken_cuts)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.raw_cod_slice)
                        .displayName(TranslatableText.raw_cod_slice)
                        .rawMaterial(Material.COD)
                        .foodProperty(
                                1,
                                0.2f,
                                false
                        )
                        .itemModel(Keys.raw_cod_slice)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.raw_salmon_slice)
                        .displayName("生鲑鱼片")
                        .rawMaterial(Material.COD)
                        .foodProperty(
                                1,
                                0.2f,
                                false
                        )
                        .itemModel(Keys.raw_salmon_slice)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.wheat_dough)
                        .displayName(TranslatableText.raw_salmon_slice)
                        .rawMaterial(Material.CHICKEN)
                        .foodProperty(
                                2,
                                1.2f,
                                false
                        )
                        .itemModel(Keys.wheat_dough)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.raw_bacon)
                        .displayName(TranslatableText.raw_bacon)
                        .rawMaterial(Material.PORKCHOP)
                        .foodProperty(
                                2,
                                1.2f,
                                false
                        )
                        .itemModel(Keys.raw_bacon)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cooking_pot)
                        .rawMaterial(Material.PAPER)
                        .displayName(TranslatableText.cooking_pot)
                        .blockId(Keys.cooking_pot)
                        .itemModel(Keys.cooking_pot)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.cutting_board)
                        .rawMaterial(Material.PAPER)
                        .displayName(TranslatableText.cutting_board)
                        .blockId(Keys.cutting_board)
                        .itemModel(Keys.cutting_board)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.flint_knife)
                        .rawMaterial(Material.STONE_SWORD)
                        .displayName(TranslatableText.flint_knife)
                        .itemModel(Keys.flint_knife)
                        .maxDurability(131)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.iron_knife)
                        .rawMaterial(Material.IRON_SWORD)
                        .displayName(TranslatableText.iron_knife)
                        .itemModel(Keys.iron_knife)
                        .maxDurability(250)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.golden_knife)
                        .rawMaterial(Material.GOLDEN_SWORD)
                        .displayName(TranslatableText.gold_knife)
                        .itemModel(Keys.golden_knife)
                        .maxDurability(32)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.diamond_knife)
                        .rawMaterial(Material.DIAMOND_SWORD)
                        .displayName(TranslatableText.diamond_knife)
                        .itemModel(Keys.diamond_knife)
                        .maxDurability(1561)
                        .build()
        );
        NeoArtisanAPI.getItemRegistry().registerItem(
                ArtisanItem.builder()
                        .registryId(Keys.netherite_knife)
                        .rawMaterial(Material.NETHERITE_SWORD)
                        .displayName(TranslatableText.netherite_knife)
                        .itemModel(Keys.netherite_knife)
                        .maxDurability(2031)
                        .build()
        );
    }

    @NeoArtisanAPI.Register
    public static void guiItemRegister() {
        for (NamespacedKey key : Keys.cooking_pot_gui_progress) {
            NeoArtisanAPI.getItemRegistry().registerItem(
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
            NeoArtisanAPI.getItemRegistry().registerItem(
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
