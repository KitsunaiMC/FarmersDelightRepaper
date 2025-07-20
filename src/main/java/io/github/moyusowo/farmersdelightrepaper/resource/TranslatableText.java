package io.github.moyusowo.farmersdelightrepaper.resource;

import net.kyori.adventure.text.Component;

public final class TranslatableText {
    private TranslatableText() {}

    public static final Component onion = Component.translatable("item.farmersdelightrepaper.onion", "洋葱"),
        tomato = Component.translatable("item.farmersdelightrepaper.tomato", "番茄"),
        rice = Component.translatable("item.farmersdelightrepaper.rice", "稻米"),
        cabbage = Component.translatable("item.farmersdelightrepaper.cabbage", "卷心菜"),
        rice_panicle = Component.translatable("item.farmersdelightrepaper.rice_panicle", "稻米穗"),
        cabbage_leaf = Component.translatable("item.farmersdelightrepaper.cabbage_leaf", "卷心菜叶"),
        tomato_seed = Component.translatable("item.farmersdelightrepaper.tomato_seeds", "番茄种子"),
        cabbage_seed = Component.translatable("item.farmersdelightrepaper.cabbage_seeds", "卷心菜种子"),
        cooking_pot = Component.translatable("block.farmersdelightrepaper.cooking_pot", "厨锅"),
        cutting_board = Component.translatable("block.farmersdelightrepaper.cutting_board", "砧板"),
        flint_knife = Component.translatable("item.farmersdelightrepaper.flint_knife", "燧石刀"),
        iron_knife = Component.translatable("item.farmersdelightrepaper.iron_knife", "铁刀"),
        gold_knife = Component.translatable("item.farmersdelightrepaper.golden_knife", "金刀"),
        diamond_knife = Component.translatable("item.farmersdelightrepaper.diamond_knife", "钻石刀"),
        netherite_knife = Component.translatable("item.farmersdelightrepaper.netherite_knife", "下界合金刀");

    public static final Component container_cooking_pot = Component.translatable("farmersdelightrepaper.container.cooking_pot", "厨锅");
}

