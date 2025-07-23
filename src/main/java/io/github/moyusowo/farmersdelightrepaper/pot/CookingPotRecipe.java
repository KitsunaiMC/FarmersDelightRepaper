package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import io.github.moyusowo.neoartisanapi.api.recipe.ArtisanRecipe;
import io.github.moyusowo.neoartisanapi.api.recipe.choice.Choice;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CookingPotRecipe implements ArtisanRecipe {
    public static final NamespacedKey TYPE = new NamespacedKey("farmersdelightrepaper", "cooking_pot");

    private final NamespacedKey key;
    private final List<Choice> inputs;
    private final boolean needBowl;
    private final ItemGenerator itemGenerator;
    private final int time;
    private final float exp;

    public CookingPotRecipe(NamespacedKey key, List<Choice> inputs, boolean needBowl, ItemGenerator itemGenerator, int time, float exp) {
        this.key = key;
        this.inputs = new ArrayList<>(inputs);
        this.needBowl = needBowl;
        this.itemGenerator = itemGenerator;
        this.time = time;
        this.exp = exp;
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
        return Collections.unmodifiableList(inputs);
    }

    @Override
    public @Unmodifiable @NotNull List<ItemGenerator> getResultGenerators() {
        return List.of(itemGenerator);
    }

    @NotNull
    public ItemGenerator getResultGenerator() {
        return itemGenerator;
    }

    public boolean needBowl() {
        return needBowl;
    }

    public int getTime() {
        return time;
    }

    public float getExp() {
        return exp;
    }

    @Override
    public boolean matches(ItemStack @NotNull [] originalItemStacks) {
        if (originalItemStacks.length != 7) return false;
        if (NeoArtisanAPI.getItemRegistry().getRegistryId(originalItemStacks[6]).equals(Material.BOWL.getKey()) && !needBowl) return false;
        if (!NeoArtisanAPI.getItemRegistry().getRegistryId(originalItemStacks[6]).equals(Material.BOWL.getKey()) && needBowl) return false;
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (originalItemStacks[i] != null && !originalItemStacks[i].isEmpty()) {
                itemStacks.add(originalItemStacks[i]);
            }
        }
        if (itemStacks.size() != inputs.size()) return false;
        int size = itemStacks.size();
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
        for (int itemId = 0; itemId < size; itemId++) {
            final ItemStack itemStack = itemStacks.get(itemId);
            for (int slotId = 0; slotId < size; slotId++) {
                final Choice choice = inputs.get(slotId);
                if (choice.matches(itemStack)) {
                    graph.get(itemId).add(slotId);
                }
            }
        }
        final int[] slotMatches = new int[size];
        Arrays.fill(slotMatches, -1);
        final int[] itemMatches = new int[size];
        Arrays.fill(itemMatches, -1);
        boolean[] visited;
        for (int slotId = 0; slotId < size; slotId++) {
            visited = new boolean[size];
            if (!findMatchForSlot(slotId, graph, slotMatches, itemMatches, visited)) {
                return false;
            }
        }
        return true;
    }

    private static boolean findMatchForSlot(int slotId, final List<List<Integer>> graph, final int[] slotMatches, final int[] itemMatches, final boolean[] visited) {
        for (int itemId = 0; itemId < graph.size(); itemId++) {
            if (graph.get(itemId).contains(slotId) && !visited[itemId]) {
                visited[itemId] = true;
                if (itemMatches[itemId] == -1 || findMatchForSlot(itemMatches[itemId], graph, slotMatches, itemMatches, visited)) {
                    slotMatches[slotId] = itemId;
                    itemMatches[itemId] = slotId;
                    return true;
                }
            }
        }
        return false;
    }
}
