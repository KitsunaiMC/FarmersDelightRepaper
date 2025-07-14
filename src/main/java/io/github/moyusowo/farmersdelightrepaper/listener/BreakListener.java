package io.github.moyusowo.farmersdelightrepaper.listener;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.config.DropConfig;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class BreakListener implements Listener {

    private static final Multimap<Material, DropConfig.ItemGeneratorWithChance> drops = ArrayListMultimap.create();

    private BreakListener() {}

    public static void initOnLoad() {
        File configFile = new File(FarmersDelightRepaper.getInstance().getDataFolder(), "break.yml");
        if (!configFile.exists()) FarmersDelightRepaper.getInstance().saveResource("break.yml", false);
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder().file(configFile).indent(2).build();
        final CommentedConfigurationNode topNode;
        try {
            topNode = loader.load();
        } catch (ConfigurateException e) {
            FarmersDelightRepaper.getInstance().getLogger().severe("error on loading break.yml, " + e);
            Bukkit.getPluginManager().disablePlugin(FarmersDelightRepaper.getInstance());
            return;
        }
        for (Map.Entry<Object, CommentedConfigurationNode> entry : topNode.childrenMap().entrySet()) {
            try {
                DropConfig dropConfig = entry.getValue().get(DropConfig.class);
                if (dropConfig == null) throw new SerializationException("why the hell is null?");
                final Material material = dropConfig.getMaterial();
                final DropConfig.ItemGeneratorWithChance itemGeneratorWithChance = dropConfig.getItemGenerator();
                if (material == null || itemGeneratorWithChance == null) throw new SerializationException("You should fill material and result.");
                drops.put(material, itemGeneratorWithChance);
            } catch (SerializationException e) {
                FarmersDelightRepaper.getInstance().getLogger().severe("error on reading " + entry.getKey().toString() + " of break.yml, " + e);
            }
        }
    }

    public static void initOnEnable() {
        Bukkit.getPluginManager().registerEvents(new BreakListener(), FarmersDelightRepaper.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        if (!NeoArtisanAPI.getBlockProtection().canBreak(event.getPlayer(), event.getBlock().getLocation())) return;
        if (NeoArtisanAPI.getArtisanBlockStorage().isArtisanBlock(event.getBlock())) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (drops.containsKey(event.getBlock().getType())) {
            for (DropConfig.ItemGeneratorWithChance itemGenerator : drops.get(event.getBlock().getType())) {
                if (ThreadLocalRandom.current().nextDouble() < itemGenerator.chance()) {
                    event.getPlayer().getWorld().dropItemNaturally(
                            event.getBlock().getLocation().add(0.5, 0.5, 0.5),
                            itemGenerator.generate()
                    );
                }
            }
        }
    }
}
