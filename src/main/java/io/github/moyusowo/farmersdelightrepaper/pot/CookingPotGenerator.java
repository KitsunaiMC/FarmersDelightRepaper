package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CookingPotGenerator implements ItemGenerator {

    private final int amount;
    private final NamespacedKey registryId;
    private final int time;

    public CookingPotGenerator(int amount, NamespacedKey registryId, int time) {
        this.amount = amount;
        this.registryId = registryId;
        this.time = time;
    }

    public int time() {
        return time;
    }

    @Override
    public @NotNull NamespacedKey registryId() {
        return registryId;
    }

    @Override
    public @NotNull ItemStack generate() {
        return NeoArtisanAPI.getItemRegistry().getItemStack(registryId, amount);
    }
}
