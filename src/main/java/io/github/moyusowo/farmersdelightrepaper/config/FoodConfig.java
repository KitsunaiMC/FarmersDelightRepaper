package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.papermc.paper.datacomponent.item.FoodProperties;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.*;

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

    @Setting(value = "tags")
    private final Set<String> tags;

    @Setting(value = "eat_seconds")
    private final float consumeSeconds;

    @Setting(value = "effects")
    private final List<EffectConfig> effects;

    @ConfigSerializable
    public static class EffectConfig {
        @Setting(value = "type")
        public final String type;

        @Setting(value = "duration")
        public final int duration;

        @Setting(value = "amplifier")
        public final int amplifier;

        @Setting(value = "chance")
        public final float chance;

        public EffectConfig() {
            type = "";
            duration = -Integer.MAX_VALUE;
            amplifier = -Integer.MAX_VALUE;
            chance = -Float.MAX_VALUE;
        }
    }

    public FoodConfig() {
        displayName = "";
        translatableText = "";
        rawMaterial = null;
        nutrition = -Integer.MAX_VALUE;
        saturation = -Float.MAX_VALUE;
        consumeSeconds = 1.6f;
        canAlwaysEat = false;
        itemModel = "false";
        tags = new HashSet<>();
        effects = new ArrayList<>();
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

    public @NotNull Set<String> getTags() {
        return tags;
    }

    public float getConsumeSeconds() {
        return consumeSeconds;
    }

    public Map<PotionEffect, Float> getEffects() {
        final Map<PotionEffect, Float> potionEffects = new HashMap<>();
        effects.forEach(
                effect -> {
                    FarmersDelightRepaper.getInstance().getLogger().info(effect.type.toLowerCase());
                    PotionEffectType type = Registry.EFFECT.get(NamespacedKey.minecraft(effect.type.toLowerCase()));
                    if (type == null) {
                        FarmersDelightRepaper.getInstance().getLogger().severe("no such potion type: " + effect.type.toLowerCase());
                        return;
                    }
                    potionEffects.put(
                            type.createEffect(effect.duration, effect.amplifier),
                            effect.chance
                    );
                }
        );
        return potionEffects;
    }
}
