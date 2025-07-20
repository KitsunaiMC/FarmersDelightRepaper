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
public class ShapelessConfig {
    @Setting(value = "items")
    private final List<String> items;

    @Setting(value = "result")
    private final Result result;

    public ShapelessConfig() {
        items = new ArrayList<>();
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

    public @Nullable List<NamespacedKey> getKeys() {
        if (items.isEmpty()) return null;
        List<NamespacedKey> keys = new ArrayList<>();
        items.forEach(
                item -> keys.add(NamespacedKey.fromString(item, FarmersDelightRepaper.getInstance()))
        );
        return keys;
    }
}
