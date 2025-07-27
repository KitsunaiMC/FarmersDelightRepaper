package io.github.moyusowo.farmersdelightrepaper.resource;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import org.bukkit.NamespacedKey;

import java.util.List;
import java.util.Set;

public final class Keys {

    public static final NamespacedKey onion = FarmersDelightRepaper.create("onion"),
        tomato = FarmersDelightRepaper.create("tomato"),
        rice = FarmersDelightRepaper.create("rice"),
        cabbage = FarmersDelightRepaper.create("cabbage"),
        rice_panicle = FarmersDelightRepaper.create("rice_panicle"),
        cabbage_leaf = FarmersDelightRepaper.create("cabbage_leaf"),
        tomato_seed = FarmersDelightRepaper.create("tomato_seed"),
        cabbage_seed = FarmersDelightRepaper.create("cabbage_seed");

    public static final NamespacedKey cooking_pot = FarmersDelightRepaper.create("cooking_pot"),
        cutting_board = FarmersDelightRepaper.create("cutting_board");

    public static final NamespacedKey flint_knife = FarmersDelightRepaper.create("flint_knife"),
        iron_knife = FarmersDelightRepaper.create("iron_knife"),
        golden_knife = FarmersDelightRepaper.create("golden_knife"),
        diamond_knife = FarmersDelightRepaper.create("diamond_knife"),
        netherite_knife = FarmersDelightRepaper.create("netherite_knife");

    public static final NamespacedKey wild_cabbages = FarmersDelightRepaper.create("wild_cabbages"),
        wild_onions = FarmersDelightRepaper.create("wild_onions"),
        wild_rice = FarmersDelightRepaper.create("wild_rice"),
        wild_tomatoes = FarmersDelightRepaper.create("wild_tomatoes");

    public static final NamespacedKey category_food = FarmersDelightRepaper.create("category/food"),
        category_tool = FarmersDelightRepaper.create("category/tool");

    public static final NamespacedKey cooking_pot_gui_1 = FarmersDelightRepaper.create("cooking_pot_gui_1"),
            cooking_pot_gui_2 = FarmersDelightRepaper.create("cooking_pot_gui_2"),
            cooking_pot_gui_progress_1 = FarmersDelightRepaper.create("cooking_pot_gui_progress_1"),
            cooking_pot_gui_progress_2 = FarmersDelightRepaper.create("cooking_pot_gui_progress_2"),
            cooking_pot_gui_progress_3 = FarmersDelightRepaper.create("cooking_pot_gui_progress_3"),
            cooking_pot_gui_progress_4 = FarmersDelightRepaper.create("cooking_pot_gui_progress_4"),
            cooking_pot_gui_progress_5 = FarmersDelightRepaper.create("cooking_pot_gui_progress_5"),
            cooking_pot_gui_progress_6 = FarmersDelightRepaper.create("cooking_pot_gui_progress_6"),
            cooking_pot_gui_progress_7 = FarmersDelightRepaper.create("cooking_pot_gui_progress_7"),
            cooking_pot_gui_progress_8 = FarmersDelightRepaper.create("cooking_pot_gui_progress_8"),
            cooking_pot_gui_progress_9 = FarmersDelightRepaper.create("cooking_pot_gui_progress_9"),
            cooking_pot_gui_progress_10 = FarmersDelightRepaper.create("cooking_pot_gui_progress_10"),
            cooking_pot_gui_progress_11 = FarmersDelightRepaper.create("cooking_pot_gui_progress_11"),
            cooking_pot_gui_progress_12 = FarmersDelightRepaper.create("cooking_pot_gui_progress_12"),
            cooking_pot_gui_progress_13 = FarmersDelightRepaper.create("cooking_pot_gui_progress_13"),
            cooking_pot_gui_progress_14 = FarmersDelightRepaper.create("cooking_pot_gui_progress_14"),
            cooking_pot_gui_progress_15 = FarmersDelightRepaper.create("cooking_pot_gui_progress_15"),
            cooking_pot_gui_progress_16 = FarmersDelightRepaper.create("cooking_pot_gui_progress_16"),
            cooking_pot_gui_progress_17 = FarmersDelightRepaper.create("cooking_pot_gui_progress_17"),
            cooking_pot_gui_progress_18 = FarmersDelightRepaper.create("cooking_pot_gui_progress_18"),
            cooking_pot_gui_progress_19 = FarmersDelightRepaper.create("cooking_pot_gui_progress_19"),
            cooking_pot_gui_progress_20 = FarmersDelightRepaper.create("cooking_pot_gui_progress_20"),
            cooking_pot_gui_fire_progress_1 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_1"),
            cooking_pot_gui_fire_progress_2 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_2"),
            cooking_pot_gui_fire_progress_3 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_3"),
            cooking_pot_gui_fire_progress_4 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_4"),
            cooking_pot_gui_fire_progress_5 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_5"),
            cooking_pot_gui_fire_progress_6 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_6"),
            cooking_pot_gui_fire_progress_7 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_7"),
            cooking_pot_gui_fire_progress_8 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_8"),
            cooking_pot_gui_fire_progress_9 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_9"),
            cooking_pot_gui_fire_progress_10 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_10"),
            cooking_pot_gui_fire_progress_11 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_11"),
            cooking_pot_gui_fire_progress_12 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_12"),
            cooking_pot_gui_fire_progress_13 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_13"),
            cooking_pot_gui_fire_progress_14 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_14"),
            cooking_pot_gui_fire_progress_15 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_15"),
            cooking_pot_gui_fire_progress_16 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_16"),
            cooking_pot_gui_fire_progress_17 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_17"),
            cooking_pot_gui_fire_progress_18 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_18"),
            cooking_pot_gui_fire_progress_19 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_19"),
            cooking_pot_gui_fire_progress_20 = FarmersDelightRepaper.create("cooking_pot_gui_fire_progress_20");

    public static final List<NamespacedKey> cooking_pot_gui_progress = List.of(
            cooking_pot_gui_1,
            cooking_pot_gui_progress_1,
            cooking_pot_gui_progress_2,
            cooking_pot_gui_progress_3,
            cooking_pot_gui_progress_4,
            cooking_pot_gui_progress_5,
            cooking_pot_gui_progress_6,
            cooking_pot_gui_progress_7,
            cooking_pot_gui_progress_8,
            cooking_pot_gui_progress_9,
            cooking_pot_gui_progress_10,
            cooking_pot_gui_progress_11,
            cooking_pot_gui_progress_12,
            cooking_pot_gui_progress_13,
            cooking_pot_gui_progress_14,
            cooking_pot_gui_progress_15,
            cooking_pot_gui_progress_16,
            cooking_pot_gui_progress_17,
            cooking_pot_gui_progress_18,
            cooking_pot_gui_progress_19,
            cooking_pot_gui_progress_20
    );

    public static final List<NamespacedKey> cooking_pot_gui_fire_progress = List.of(
            cooking_pot_gui_2,
            cooking_pot_gui_fire_progress_1,
            cooking_pot_gui_fire_progress_2,
            cooking_pot_gui_fire_progress_3,
            cooking_pot_gui_fire_progress_4,
            cooking_pot_gui_fire_progress_5,
            cooking_pot_gui_fire_progress_6,
            cooking_pot_gui_fire_progress_7,
            cooking_pot_gui_fire_progress_8,
            cooking_pot_gui_fire_progress_9,
            cooking_pot_gui_fire_progress_10,
            cooking_pot_gui_fire_progress_11,
            cooking_pot_gui_fire_progress_12,
            cooking_pot_gui_fire_progress_13,
            cooking_pot_gui_fire_progress_14,
            cooking_pot_gui_fire_progress_15,
            cooking_pot_gui_fire_progress_16,
            cooking_pot_gui_fire_progress_17,
            cooking_pot_gui_fire_progress_18,
            cooking_pot_gui_fire_progress_19,
            cooking_pot_gui_fire_progress_20
    );

}
