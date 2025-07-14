package io.github.moyusowo.farmersdelightrepaper;

import io.github.moyusowo.farmersdelightrepaper.board.CuttingBoardBehavior;
import io.github.moyusowo.farmersdelightrepaper.listener.BreakListener;
import io.github.moyusowo.farmersdelightrepaper.pot.CookingPotRecipe;
import io.github.moyusowo.farmersdelightrepaper.registrar.ItemRegistrar;
import io.github.moyusowo.farmersdelightrepaper.registrar.RecipeRegistrar;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmersDelightRepaper extends JavaPlugin {

    private static FarmersDelightRepaper instance;

    public static FarmersDelightRepaper getInstance() {
        return instance;
    }

    public static NamespacedKey create(String key) {
        return new NamespacedKey(instance, key);
    }

    public FarmersDelightRepaper() {
        super();
        instance = this;
    }

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdirs()) {
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
        ItemRegistrar.initOnLoad();
        RecipeRegistrar.initOnLoad();
        CuttingBoardBehavior.initOnLoad();
        CookingPotRecipe.initOnLoad();
        BreakListener.initOnLoad();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CuttingBoardBehavior.initOnEnable();
        BreakListener.initOnEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
