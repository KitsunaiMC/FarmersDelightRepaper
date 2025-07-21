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
public final class CuttingBoardConfig {
    @Setting(value = "item")
    private final String id;

    @Setting(value = "results")
    private final List<Result> results;

    public CuttingBoardConfig() {
        id = "";
        results = new ArrayList<>();
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

    public @Nullable List<ItemGenerator> getItemGenerator() {
        if (results.isEmpty()) return null;
        final List<ItemGenerator> generators = new ArrayList<>();
        for (Result result : results) {
            if (result.id.isEmpty()) return null;
            final NamespacedKey key = NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
            if (key == null) return null;
            if (result.amount == Integer.MIN_VALUE) return null;
            generators.add(ItemGenerator.simpleGenerator(key, result.amount));
        }
        return generators;
    }

    public @Nullable NamespacedKey getKey() {
        if (id.isEmpty()) return null;
        return NamespacedKey.fromString(id, FarmersDelightRepaper.getInstance());
    }
}
