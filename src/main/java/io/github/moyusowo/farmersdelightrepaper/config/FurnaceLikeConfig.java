package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class FurnaceLikeConfig {
    @Setting(value = "item")
    private final String item;

    @Setting(value = "exp")
    private final float exp;

    @Setting(value = "time")
    private final int time;

    @Setting(value = "result")
    private final Result result;

    public FurnaceLikeConfig() {
        item = "";
        exp = Float.MIN_VALUE;
        time = Integer.MIN_VALUE;
        result = new Result();
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

    public @Nullable ItemGenerator getItemGenerator() {
        if (result.id.isEmpty()) return null;
        final NamespacedKey key = NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
        if (key == null) return null;
        if (result.amount == Integer.MIN_VALUE) return null;
        return ItemGenerator.simpleGenerator(key, result.amount);
    }

    public @Nullable NamespacedKey getKey() {
        if (item.isEmpty()) return null;
        return NamespacedKey.fromString(item, FarmersDelightRepaper.getInstance());
    }

    public @Nullable Integer getTime() {
        if (time <= 0) return null;
        return time;
    }

    public @Nullable Float getExp() {
        if (exp < 0) return null;
        return exp;
    }
}
