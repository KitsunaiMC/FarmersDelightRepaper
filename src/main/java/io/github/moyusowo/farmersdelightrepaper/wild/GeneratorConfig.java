package io.github.moyusowo.farmersdelightrepaper.wild;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.ConfigUtil;
import io.github.moyusowo.farmersdelightrepaper.config.WildGeneratorConfig;
import org.bukkit.World;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneratorConfig {
    public static final List<World> worlds = new ArrayList<>();
    public static volatile WildGeneratorConfig.Range wildCabbages, wildOnions, wildTomatoes, wildRice;

    public static void initOnEnable() {
        final CommentedConfigurationNode topNode = ConfigUtil.readYml("wilds.yml");
        try {
            WildGeneratorConfig config = topNode.get(WildGeneratorConfig.class);
            if (config == null) throw new SerializationException("why the hell is null?");
            worlds.addAll(config.getWorlds());
            if (!config.isAllValid()) throw new SerializationException("You should fill valid params!");
            wildCabbages = config.wildCabbages;
            wildOnions = config.wildOnions;
            wildTomatoes = config.wildTomatoes;
            wildRice = config.wildRice;
        } catch (SerializationException e) {
            FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + " of wilds.yml, " + e);
        }
        FarmersDelightRepaper.getInstance().getLogger().info("Custom Wild Generator Configs loaded");
    }
}
