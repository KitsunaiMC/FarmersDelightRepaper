package io.github.moyusowo.farmersdelightrepaper.config;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import org.bukkit.Bukkit;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;

public final class ConfigUtil {
    public static CommentedConfigurationNode readYml(String path) {
        File configFile = new File(FarmersDelightRepaper.getInstance().getDataFolder(), path);
        if (!configFile.exists()) FarmersDelightRepaper.getInstance().saveResource(path, false);
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder().file(configFile).indent(2).build();
        try {
            return loader.load();
        } catch (ConfigurateException e) {
            FarmersDelightRepaper.getInstance().getLogger().severe("error on loading " + path + ", " + e);
            Bukkit.getPluginManager().disablePlugin(FarmersDelightRepaper.getInstance());
            return CommentedConfigurationNode.root();
        }
    }
}
