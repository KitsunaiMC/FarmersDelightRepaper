package io.github.moyusowo.farmersdelightrepaper.registrar;

import com.google.common.collect.Multimap;
import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.board.CuttingBoardRecipe;
import io.github.moyusowo.farmersdelightrepaper.config.*;
import io.github.moyusowo.farmersdelightrepaper.pot.CookingPotRecipe;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.*;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.Choice;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.ItemChoice;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.MultiChoice;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.TagChoice;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.Collection;
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
        onCutting();
        onCooking();
    }

    private static void onShapeless() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/shapeless.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                ShapelessConfig config = entry.getValue().get(ShapelessConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final List<List<NamespacedKey>> keys = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                if (keys == null) throw new SerializationException("You should correctly fill item ids");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                ArtisanShapelessRecipe.Builder builder = ArtisanShapelessRecipe.builder()
                        .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                        .resultGenerator(itemGenerator);
                for (List<NamespacedKey> choices : keys) {
                    final List<Choice> c = new ArrayList<>();
                    for (NamespacedKey choice : choices) {
                        if (choice.getNamespace().equals("tag")) {
                            c.add(new TagChoice(choice.getKey()));
                        } else {
                            c.add(new ItemChoice(choice));
                        }
                    }
                    builder.add(new MultiChoice(c));
                }
                Registries.RECIPE.register(
                        builder.build()
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
                final List<NamespacedKey> keys = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (keys == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                final List<Choice> choices = new ArrayList<>();
                for (NamespacedKey key : keys) {
                    if (key.getNamespace().equals("tag")) {
                        choices.add(new TagChoice(key.getKey()));
                    } else {
                        choices.add(new ItemChoice(key));
                    }
                }
                Registries.RECIPE.register(
                        ArtisanFurnaceRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .input(new MultiChoice(choices))
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
                final List<NamespacedKey> keys = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (keys == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                final List<Choice> choices = new ArrayList<>();
                for (NamespacedKey key : keys) {
                    if (key.getNamespace().equals("tag")) {
                        choices.add(new TagChoice(key.getKey()));
                    } else {
                        choices.add(new ItemChoice(key));
                    }
                }
                Registries.RECIPE.register(
                        ArtisanCampfireRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .input(new MultiChoice(choices))
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
                final List<NamespacedKey> keys = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                if (keys == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                final List<Choice> choices = new ArrayList<>();
                for (NamespacedKey key : keys) {
                    if (key.getNamespace().equals("tag")) {
                        choices.add(new TagChoice(key.getKey()));
                    } else {
                        choices.add(new ItemChoice(key));
                    }
                }
                Registries.RECIPE.register(
                        ArtisanSmokingRecipe.builder()
                                .key(FarmersDelightRepaper.create(entry.getKey().toString()))
                                .resultGenerator(itemGenerator)
                                .exp(exp)
                                .cookTime(time)
                                .input(new MultiChoice(choices))
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
                final Multimap<Character, NamespacedKey> ingredients = config.getIngredientKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                if (matrix == null) throw new SerializationException("You should correctly fill matrix!");
                if (ingredients == null) throw new SerializationException("You should correctly fill ingredients!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                ArtisanShapedRecipe.Builder builder = ArtisanShapedRecipe.builder();
                builder.key(FarmersDelightRepaper.create(entry.getKey().toString())).set(matrix[0], matrix[1], matrix[2]).resultGenerator(itemGenerator);
                for (Character c : ingredients.keySet()) {
                    final List<Choice> choices = new ArrayList<>();
                    final Collection<NamespacedKey> keys = ingredients.get(c);
                    for (NamespacedKey key : keys) {
                        if (key.namespace().equals("tag")) {
                            choices.add(new TagChoice(key.getKey()));
                        } else {
                            choices.add(new ItemChoice(key));
                        }
                    }
                    builder.add(c, new MultiChoice(choices));
                }
                Registries.RECIPE.register(builder.build());
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of smoker.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Smoker Recipes loaded");
    }

    private static void onCutting() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/cutting_board.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                CuttingBoardConfig config = entry.getValue().get(CuttingBoardConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final List<NamespacedKey> keys = config.getKeys();
                final List<ItemGenerator> itemGenerators = config.getItemGenerators();
                if (keys == null) throw new SerializationException("You should correctly fill item id!");
                if (itemGenerators == null) throw new SerializationException("You should correctly fill all result params!");
                final List<Choice> choices = new ArrayList<>();
                keys.forEach(
                        key -> {
                            if (key.getNamespace().equals("tag")) {
                                choices.add(new TagChoice(key.getKey()));
                            } else {
                                choices.add(new ItemChoice(key));
                            }
                        }
                );
                Registries.RECIPE.register(
                        new CuttingBoardRecipe(
                                NamespacedKey.fromString(entry.getKey().toString(), FarmersDelightRepaper.getInstance()),
                                new MultiChoice(choices),
                                itemGenerators
                        )
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of cutting_board.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom CuttingBoard Recipes loaded");
    }

    private static void onCooking() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/cooking_pot.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                CookingPotConfig config = entry.getValue().get(CookingPotConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final List<List<NamespacedKey>> keys = config.getKeys();
                final ItemGenerator itemGenerator = config.getItemGenerator();
                final Integer time = config.getTime();
                final Float exp = config.getExp();
                final boolean needBowl = config.needBowl;
                if (keys == null) throw new SerializationException("You should correctly fill item ids!");
                if (itemGenerator == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                if (exp == null) throw new SerializationException("You should fill a valid exp value!");
                final List<Choice> choices = new ArrayList<>();
                for (final List<NamespacedKey> choicesKey : keys) {
                    final List<Choice> c = new ArrayList<>();
                    for (final NamespacedKey choice : choicesKey) {
                        if (choice.getNamespace().equals("tag")) {
                            c.add(new TagChoice(choice.getKey()));
                        } else {
                            c.add(new ItemChoice(choice));
                        }
                    }
                    choices.add(new MultiChoice(c));
                }
                Registries.RECIPE.register(
                        new CookingPotRecipe(
                                FarmersDelightRepaper.create(entry.getKey().toString()),
                                choices,
                                needBowl,
                                itemGenerator,
                                time,
                                exp
                        )
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of cooking_pot.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom CookingPot Recipes loaded");
    }

    private static void plantDefault() {
        Registries.RECIPE.register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.tomato_seed)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.tomato_seed,
                                        1
                                )
                        )
                        .add(new ItemChoice(Keys.tomato))
                        .build()
        );
        Registries.RECIPE.register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.rice)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.rice,
                                        1
                                )
                        )
                        .add(new ItemChoice(Keys.rice_panicle))
                        .build()
        );
    }

    private static void knifeDefault() {
        Registries.RECIPE.register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.flint_knife)
                        .set(" A ", " B ")
                        .add('A', new ItemChoice(Material.FLINT.getKey()))
                        .add('B', new ItemChoice(Material.STICK.getKey()))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.flint_knife,
                                        1
                                )
                        )
                        .build()
        );
        Registries.RECIPE.register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.iron_knife)
                        .set(" A ", " B ")
                        .add('A', new ItemChoice(Material.IRON_INGOT.getKey()))
                        .add('B', new ItemChoice(Material.STICK.getKey()))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.iron_knife,
                                        1
                                )
                        )
                        .build()
        );
        Registries.RECIPE.register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.golden_knife)
                        .set(" A ", " B ")
                        .add('A', new ItemChoice(Material.GOLD_INGOT.getKey()))
                        .add('B', new ItemChoice(Material.STICK.getKey()))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.golden_knife,
                                        1
                                )
                        )
                        .build()
        );
        Registries.RECIPE.register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.diamond_knife)
                        .set(" A ", " B ")
                        .add('A', new ItemChoice(Material.DIAMOND.getKey()))
                        .add('B', new ItemChoice(Material.STICK.getKey()))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.diamond_knife,
                                        1
                                )
                        )
                        .build()
        );
    }

    private static void cookingPotDefault() {
        Registries.RECIPE.register(
                ArtisanShapedRecipe.builder()
                        .key(Keys.cooking_pot)
                        .set("ABA", "CDC", "CCC")
                        .add('A', new ItemChoice(Material.BRICK.getKey()))
                        .add('B', new ItemChoice(Material.WOODEN_SHOVEL.getKey()))
                        .add('C', new ItemChoice(Material.IRON_INGOT.getKey()))
                        .add('D', new ItemChoice(Material.WATER_BUCKET.getKey()))
                        .resultGenerator(ItemGenerator.simpleGenerator(Keys.cooking_pot, 1))
                        .build()
        );
    }

    private static void cuttingBoardDefault() {
        final ArtisanShapedRecipe.Builder builder = ArtisanShapedRecipe.builder()
                .key(FarmersDelightRepaper.create(Keys.cutting_board.getKey()))
                .set("ABB", "ABB")
                .add('A', new ItemChoice(Material.STICK.getKey()))
                .resultGenerator(ItemGenerator.simpleGenerator(Keys.cutting_board, 1));
        final List<Choice> choices = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.name().endsWith("_PLANKS")) {
                choices.add(new ItemChoice(material.getKey()));
            }
        }
        builder.add('B', new MultiChoice(choices));
        Registries.RECIPE.register(builder.build());
    }
}
