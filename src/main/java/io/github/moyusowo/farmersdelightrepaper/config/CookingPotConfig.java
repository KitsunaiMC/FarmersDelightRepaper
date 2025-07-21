package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class CookingPotConfig {
    @Setting(value = "need_bowl")
    public final boolean needBowl;

    @Setting(value = "time")
    private final int time;

    @Setting(value = "items")
    private final List<String> items;

    @Setting(value = "result")
    private final Result result;

    @Setting(value = "exp")
    private final float exp;

    public CookingPotConfig() {
        items = new ArrayList<>();
        needBowl = false;
        time = -Integer.MAX_VALUE;
        result = new Result();
        exp = -Float.MAX_VALUE;
    }

    @ConfigSerializable
    static class Result {
        @Setting(value = "id")
        private final String id;

        @Setting(value = "amount")
        private final int amount;

        public Result() {
            id = "";
            amount = Integer.MIN_VALUE;
        }
    }

    public @Nullable NamespacedKey getItemGeneratorId() {
        if (result.id.isEmpty()) return null;
        return NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
    }

    public @Nullable Float getExp() {
        if (exp < 0) return null;
        return exp;
    }

    public @Nullable Integer getAmount() {
        if (result.amount <= 0) return null;
        return result.amount;
    }

    public @Nullable List<NamespacedKey> getKeys() {
        if (items.isEmpty()) return null;
        List<NamespacedKey> keys = new ArrayList<>();
        items.forEach(
                item -> keys.add(NamespacedKey.fromString(item, FarmersDelightRepaper.getInstance()))
        );
        return keys;
    }

    public @Nullable Integer getTime() {
        if (time <= 0) return null;
        return time;
    }
}
