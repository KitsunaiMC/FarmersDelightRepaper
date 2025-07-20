package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.ConfigUtil;
import io.github.moyusowo.farmersdelightrepaper.config.CookingPotConfig;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.item.ArtisanItem;
import org.bukkit.NamespacedKey;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.*;

public final class CookingPotRecipe {

    private static final Map<CookingPotRecipe, CookingPotGenerator> recipe = new HashMap<>();

    public static void initOnLoad() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("recipes/cooking_pot.yml");
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                CookingPotConfig config = entry.getValue().get(CookingPotConfig.class);
                if (config == null) throw new SerializationException("why the hell is null?");
                final List<NamespacedKey> key = config.getKeys();
                final NamespacedKey itemGenerator = config.getItemGeneratorId();
                final Integer amount = config.getAmount();
                final Integer time = config.getTime();
                final boolean needBowl = config.needBowl;
                if (key == null) throw new SerializationException("You should correctly fill item ids!");
                if (itemGenerator == null || amount == null) throw new SerializationException("You should correctly fill all result params!");
                if (time == null) throw new SerializationException("You should fill a valid time value!");
                recipe.put(
                        new CookingPotRecipe(
                                needBowl,
                                key.toArray(new NamespacedKey[0])
                        ),
                        new CookingPotGenerator(
                                amount, itemGenerator, time
                        )
                );
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of cooking_pot.yml, " + e);
            }
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom CookingPot Recipes loaded");
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
