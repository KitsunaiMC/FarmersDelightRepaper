package io.github.moyusowo.farmersdelightrepaper.config;

import io.papermc.paper.datacomponent.item.FoodProperties;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class FoodConfig {
    @Setting(value = "display_name")
    private final String displayName;

    @Setting(value = "translatable_text")
    private final String translatableText;

    @Setting(value = "raw_material")
    private final Material rawMaterial;

    @Setting(value = "nutrition")
    private final int nutrition;

    @Setting(value = "saturation")
    private final float saturation;

    @Setting(value = "can_always_eat")
    private final boolean canAlwaysEat;

    @Setting(value = "item_model")
    private final String itemModel;

    public FoodConfig() {
        displayName = "";
        translatableText = "";
        rawMaterial = null;
        nutrition = Integer.MIN_VALUE;
        saturation = Float.MIN_VALUE;
        canAlwaysEat = false;
        itemModel = "false";
    }

    private @Nullable TranslatableComponent getTranslatable() {
        if (translatableText.isEmpty()) return null;
        if (displayName.isEmpty()) return Component.translatable(translatableText);
        return Component.translatable(translatableText, displayName);
    }

    public @Nullable Component getDisplayName() {
        if (getTranslatable() != null) return getTranslatable();
        if (displayName.isEmpty()) return null;
        return MiniMessage.miniMessage().deserialize(displayName);
    }

    public @Nullable Material getRawMaterial() {
        return rawMaterial;
    }

    @SuppressWarnings("UnstableApiUsage")
    public @Nullable FoodProperties getFood() {
        if (nutrition == Integer.MIN_VALUE || saturation == Float.MIN_VALUE) return null;
        return FoodProperties.food().nutrition(nutrition).canAlwaysEat(canAlwaysEat).saturation(saturation).build();
    }

    public @Nullable String getItemModel() {
        if (itemModel.equals("true")) return "";
        else if (itemModel.equals("false")) return null;
        return itemModel;
    }

}
