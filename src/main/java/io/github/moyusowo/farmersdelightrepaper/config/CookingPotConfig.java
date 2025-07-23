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
    private final List<List<String>> items;

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
            amount = -Integer.MAX_VALUE;
        }
    }

    public @Nullable ItemGenerator getItemGenerator() {
        if (result.id.isEmpty()) return null;
        final NamespacedKey key = NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
        if (key == null) return null;
        if (result.amount <= 0) return null;
        return ItemGenerator.simpleGenerator(key, result.amount);
    }

    public @Nullable Float getExp() {
        if (exp < 0) return null;
        return exp;
    }

    public @Nullable List<List<NamespacedKey>> getKeys() {
        if (items.isEmpty()) return null;
        List<List<NamespacedKey>> keys = new ArrayList<>();
        for (List<String> choices : items) {
            final List<NamespacedKey> k = new ArrayList<>();
            for (String choice : choices) {
                k.add(NamespacedKey.fromString(choice, FarmersDelightRepaper.getInstance()));
            }
            keys.add(k);
        }
        return keys;
    }

    public @Nullable Integer getTime() {
        if (time <= 0) return null;
        return time;
    }
}
