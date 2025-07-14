package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.item.ArtisanItem;
import org.bukkit.NamespacedKey;

import java.util.*;

public final class CookingPotRecipe {

    private static final Map<CookingPotRecipe, CookingPotGenerator> recipe = new HashMap<>();

    public static void initOnLoad() {

    }

    static {
        recipe.put(
                new CookingPotRecipe(
                        true,
                        Keys.cabbage, Keys.tomato
                ),
                new CookingPotGenerator(2, Keys.tomato_sauce, 20 * 10)
        );
    }

    private final Set<NamespacedKey> items;
    private final boolean needBowl;

    CookingPotRecipe(boolean needBowl, NamespacedKey... items) {
        this.needBowl = needBowl;
        this.items = new HashSet<>();
        this.items.addAll(Arrays.stream(items).filter(item -> (!(item == null || item.equals(ArtisanItem.EMPTY)))).toList());
    }

    static CookingPotGenerator matches(
            CookingPotRecipe r
    ) {
        return recipe.getOrDefault(r, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return items.equals(((CookingPotRecipe) o).items) && needBowl == ((CookingPotRecipe) o).needBowl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, needBowl);
    }

    @Override
    public String toString() {
        return "CookingPotRecipe: {items=" + items.toString() + " ,needBowl=" + needBowl + "}";
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

}
