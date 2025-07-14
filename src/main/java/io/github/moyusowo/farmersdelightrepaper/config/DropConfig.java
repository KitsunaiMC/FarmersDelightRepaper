package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class DropConfig {
    @Setting(value = "block_material")
    private final Material block_material;

    @Setting(value = "result")
    private final Result result;

    @ConfigSerializable
    static class Result {
        @Setting(value = "id")
        private final String id;

        @Setting(value = "amount")
        private final int amount;

        @Setting(value = "chance")
        private final double chance;

        public Result() {
            id = "";
            amount = Integer.MIN_VALUE;
            chance = Double.MIN_VALUE;
        }
    }

    public DropConfig() {
        block_material = null;
        result = new Result();
    }

    public record ItemGeneratorWithChance(NamespacedKey registryId, int amount,
                                          double chance) implements ItemGenerator {

        @Override
        public @NotNull NamespacedKey registryId() {
            return this.registryId;
        }

        @Override
        public @NotNull ItemStack generate() {
            return NeoArtisanAPI.getItemRegistry().getItemStack(this.registryId, this.amount);
        }
    }

    public @Nullable Material getMaterial() {
        return block_material;
    }

    public @Nullable ItemGeneratorWithChance getItemGenerator() {
        if (result.id.isEmpty()) return null;
        final NamespacedKey key = NamespacedKey.fromString(result.id, FarmersDelightRepaper.getInstance());
        if (key == null) return null;
        if (block_material == null) return null;
        if (result.amount <= 0 || result.amount > block_material.getMaxStackSize()) return null;
        if (result.chance <= 0 || result.chance > 1) return null;
        return new ItemGeneratorWithChance(key, result.amount, result.chance);
    }
}
