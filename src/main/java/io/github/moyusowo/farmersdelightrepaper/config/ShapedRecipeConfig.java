package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class ShapedRecipeConfig {
    @Setting("matrix")
    private List<String> matrix = new ArrayList<>();

    @Setting("set")
    private Map<String, String> ingredientMap = new HashMap<>();

    @Setting("result")
    private Result result = new Result();

    public ShapedRecipeConfig() {}

    public @Nullable String[] getMatrix() {
        if (matrix == null || matrix.isEmpty()) return null;
        if (matrix.size() != 3) return null;
        return new String[] {
                matrix.get(0), matrix.get(1), matrix.get(2)
        };
    }

    public @Nullable Map<Character, NamespacedKey> getIngredientKeys() {
        if (ingredientMap == null || ingredientMap.isEmpty()) return null;
        Map<Character, NamespacedKey> keys = new HashMap<>();
        ingredientMap.forEach((symbol, itemId) -> {
            if (symbol.length() == 1) {
                NamespacedKey key = NamespacedKey.fromString(
                        itemId,
                        FarmersDelightRepaper.getInstance()
                );
                if (key != null) keys.put(symbol.charAt(0), key);
            }
        });
        if (keys.isEmpty()) return null;
        return keys;
    }

    public @Nullable ItemGenerator getItemGenerator() {
        if (result.id.isEmpty()) return null;
        final NamespacedKey key = NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
        if (key == null) return null;
        if (result.amount == Integer.MIN_VALUE) return null;
        return ItemGenerator.simpleGenerator(key, result.amount);
    }

    @ConfigSerializable
    public static class Result {
        @Setting("id")
        String id = "";

        @Setting("amount")
        int amount = Integer.MIN_VALUE;

        public Result() {}
    }

}
