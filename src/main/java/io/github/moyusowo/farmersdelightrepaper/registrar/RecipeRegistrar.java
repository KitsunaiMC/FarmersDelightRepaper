package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanFurnaceRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanShapedRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanShapelessRecipe;
import net.minecraft.world.item.Item;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class RecipeRegistrar {

    @NeoArtisanAPI.Register
    public static void shapeless() {
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
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.raw_pasta)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.raw_pasta,
                                        2
                                )
                        )
                        .add(
                                Material.WATER_BUCKET.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey()
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(FarmersDelightRepaper.create(Keys.raw_pasta.getKey() + "_1"))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.raw_pasta,
                                        1
                                )
                        )
                        .add(
                                Material.WATER_BUCKET.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey()
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(FarmersDelightRepaper.create(Keys.raw_pasta.getKey() + "_2"))
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.raw_pasta,
                                        1
                                )
                        )
                        .add(
                                Material.EGG.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey()
                        )
                        .build()
        );
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanShapelessRecipe.builder()
                        .key(Keys.wheat_dough)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Keys.wheat_dough,
                                        3
                                )
                        )
                        .add(
                                Material.WATER_BUCKET.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey(),
                                Material.WHEAT.getKey()
                        )
                        .build()
        );
    }

    @NeoArtisanAPI.Register
    public static void shaped() {
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

    @NeoArtisanAPI.Register
    public static void furnace() {
        NeoArtisanAPI.getRecipeRegistry().register(
                ArtisanFurnaceRecipe.builder()
                        .key(Keys.raw_chicken_cuts)
                        .inputItemId(Keys.raw_chicken_cuts)
                        .exp(1)
                        .cookTime(100)
                        .resultGenerator(
                                ItemGenerator.simpleGenerator(
                                        Material.COOKED_CHICKEN.getKey(),
                                        1
                                )
                        )
                        .build()
        );
    }
}
