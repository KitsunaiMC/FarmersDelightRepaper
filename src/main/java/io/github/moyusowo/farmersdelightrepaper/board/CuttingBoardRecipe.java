package io.github.moyusowo.farmersdelightrepaper.board;

import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.Choice;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuttingBoardRecipe implements ArtisanRecipe {
    public static final NamespacedKey TYPE = new NamespacedKey("farmersdelightrepaper", "cutting_board");

    private final NamespacedKey key;
    private final Choice choice;
    private final List<ItemGenerator> itemGenerators;

    public CuttingBoardRecipe(NamespacedKey key, Choice choice, List<ItemGenerator> itemGenerators) {
        this.key = key;
        this.choice = choice;
        this.itemGenerators = new ArrayList<>(itemGenerators);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }

    @Override
    public @NotNull NamespacedKey getType() {
        return TYPE;
    }

    @Override
    public @Unmodifiable @NotNull List<Choice> getInputs() {
        return List.of(choice);
    }

    @NotNull
    public Choice getInput() {
        return choice;
    }

    @Override
    public @Unmodifiable @NotNull List<ItemGenerator> getResultGenerators() {
        return Collections.unmodifiableList(itemGenerators);
    }

    @Override
    public boolean matches(ItemStack @NotNull [] itemStacks) {
        if (itemStacks.length != 1) return false;
        return choice.matches(itemStacks[0]);
    }

    public boolean matches(@NotNull ItemStack itemStack) {
        return matches(new ItemStack[] { itemStack });
    }
}
