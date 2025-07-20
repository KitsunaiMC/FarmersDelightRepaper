package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.ConfigUtil;
import io.github.moyusowo.farmersdelightrepaper.config.FurnaceLikeConfig;
import io.github.moyusowo.farmersdelightrepaper.config.ShapedRecipeConfig;
import io.github.moyusowo.farmersdelightrepaper.config.ShapelessConfig;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.List;
import java.util.Map;

public final class RecipeRegistrar {

    private RecipeRegistrar() {}

    public static void initOnEnable() {
        knifeDefault();
        plantDefault();
        cookingPotDefault();
        cuttingBoardDefault();
        onShaped();
        onFurnace();
        onCampfire();
        onSmoker();
        onShapeless();
    }

    private static void onShapeless() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/shapeless.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                ShapelessConfig config = entry.getValue().get(ShapelessConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final List<NamespacedKey> key = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                if (key == null) throw new SerializationException("You should correctly fill item ids");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                NeoArtisanAPI.getRecipeRegistry().register(
                        ArtisanShapelessRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .add(key.toArray(new NamespacedKey[0]))
                                .resultGenerator(itemGenerator)
                                .build()
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of shapeless.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Shapeless Recipes loaded");
    }

    private static void onFurnace() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/furnace.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                FurnaceLikeConfig config = entry.getValue().get(FurnaceLikeConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final NamespacedKey key = config.getKey();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (key == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                NeoArtisanAPI.getRecipeRegistry().register(
                        ArtisanFurnaceRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .inputItemId(key)
                                .build()
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of furnace.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Furnace Recipes loaded");
    }

    private static void onCampfire() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/campfire.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                FurnaceLikeConfig config = entry.getValue().get(FurnaceLikeConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final NamespacedKey key = config.getKey();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (key == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                NeoArtisanAPI.getRecipeRegistry().register(
                        ArtisanCampfireRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .inputItemId(key)
                                .build()
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of campfire.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Campfire Recipes loaded");
    }

    private static void onSmoker() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/smoker.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                FurnaceLikeConfig config = entry.getValue().get(FurnaceLikeConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final NamespacedKey key = config.getKey();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (key == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                NeoArtisanAPI.getRecipeRegistry().register(
                        ArtisanSmokingRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .inputItemId(key)
                                .build()
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of smoker.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Smoker Recipes loaded");
    }

    private static void onShaped() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/shaped.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                ShapedRecipeConfig config = entry.getValue().get(ShapedRecipeConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final String[] matrix = config.getMatrix();
                final Map<Character, NamespacedKey> ingredients = config.getIngredientKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                if (matrix == null) throw new SerializationException("You should correctly fill matrix!");
                if (ingredients == null) throw new SerializationException("You should correctly fill ingredients!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                ArtisanShapedRecipe.Builder builder = ArtisanShapedRecipe.builder();
                builder.key(FarmersDelightRepaper.create(entry.getKey().toString())).set(matrix[0], matrix[1], matrix[2]).resultGenerator(itemGenerator);
                ingredients.forEach(builder::add);
                NeoArtisanAPI.getRecipeRegistry().register(builder.build());
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of smoker.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Smoker Recipes loaded");
    }

    private static void plantDefault() {
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.tomato_seed)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.tomato_seed,
                                        1
                                )
                        )
                        .add(Keys.tomato)
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.rice)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.rice,
                                        1
                                )
                        )
                        .add(Keys.rice_panicle)
                        .build()
        );
    }

    private static void knifeDefault() {
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.flint_knife)
                        .set(" A ", " B ")
                        .add('A', Material.FLINT.getKey())
                        .add('B', Material.STICK.getKey())
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.flint_knife,
                                        1
                                )
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.iron_knife)
                        .set(" A ", " B ")
                        .add('A', Material.IRON_INGOT.getKey())
                        .add('B', Material.STICK.getKey())
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.iron_knife,
                                        1
                                )
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.golden_knife)
                        .set(" A ", " B ")
                        .add('A', Material.GOLD_INGOT.getKey())
                        .add('B', Material.STICK.getKey())
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.golden_knife,
                                        1
                                )
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.diamond_knife)
                        .set(" A ", " B ")
                        .add('A', Material.DIAMOND.getKey())
                        .add('B', Material.STICK.getKey())
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.golden_knife,
                                        1
                                )
                        )
                        .build()
        );
    }

    private static void cookingPotDefault() {
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.cooking_pot)
                        .set("ABA", "CDC", "CCC")
                        .add('A', Material.BRICK.getKey())
                        .add('B', Material.WOODEN_SHOVEL.getKey())
                        .add('C', Material.IRON_INGOT.getKey())
                        .add('D', Material.WATER_BUCKET.getKey())
                        .resultGenerator(ItemGenerator.simpleGenerator(Keys.cooking_pot, 1))
                        .build()
        );
    }

    private static void cuttingBoardDefault() {
        int i = 0;
        for (Material material : Material.values()) {
            if (material.name().endsWith("_PLANKS")) {
                NeoArtisanAPI.getRecipeRegistry().register(
                        ArtisanShapedRecipe.builder()
                                .key(FarmersDelightRepaper.create(Keys.cutting_board.getKey() + "_" + i))
                                .set("ABB", "ABB")
                                .add('A', Material.STICK.getKey())
                                .add('B', material.getKey())
                                .resultGenerator(ItemGenerator.simpleGenerator(Keys.cutting_board, 1))
                                .build()
                );
            }
        }
    }
}
