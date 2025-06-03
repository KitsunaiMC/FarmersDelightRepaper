package io.github.moyusowo.farmersdelightrepaper;

import io.github.moyusowo.farmersdelightrepaper.board.CuttingBoardBehavior;
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
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CuttingBoardBehavior.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
