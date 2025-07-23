package io.github.moyusowo.farmersdelightrepaper;

import io.github.moyusowo.farmersdelightrepaper.board.CuttingBoardBehavior;
import io.github.moyusowo.farmersdelightrepaper.registrar.BlockRegistrar;
import io.github.moyusowo.farmersdelightrepaper.registrar.ItemRegistrar;
import io.github.moyusowo.farmersdelightrepaper.registrar.RecipeRegistrar;
import io.github.moyusowo.farmersdelightrepaper.wild.GeneratorListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
                getLogger().severe("internal error on create data folder. plugin disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
        File recipesFolder = new File(getDataFolder(), "recipes");
        if (!recipesFolder.exists()) {
            if (!recipesFolder.mkdirs()) {
                getLogger().severe("internal error on create recipe folder. plugin disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CuttingBoardBehavior.initOnEnable();
        ItemRegistrar.initOnEnable();
        RecipeRegistrar.initOnEnable();
        BlockRegistrar.initOnEnable();
        GeneratorListener.initOnEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
