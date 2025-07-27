package io.github.moyusowo.farmersdelightrepaper.config;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ConfigSerializable
public class WildGeneratorConfig {
    @Setting("worlds")
    private final List<String> worlds;

    @Setting("wild_cabbages")
    public final Range wildCabbages;

    @Setting("wild_onions")
    public final Range wildOnions;

    @Setting("wild_tomatoes")
    public final Range wildTomatoes;

    @Setting("wild_rice")
    public final Range wildRice;

    @ConfigSerializable
    public static class Range {
        @Setting(value = "min")
        public final int min;

        @Setting(value = "max")
        public final int max;

        public Range() {
            min = -Integer.MAX_VALUE;
            max = -Integer.MAX_VALUE;
        }

        public boolean isValid() {
            return min >= 0 && max > min;
        }
    }

    public WildGeneratorConfig() {
        worlds = new ArrayList<>();
        wildCabbages = new Range();
        wildOnions = new Range();
        wildTomatoes = new Range();
        wildRice = new Range();
    }

    @NotNull
    public List<World> getWorlds() {
        return worlds.stream().map(Bukkit::getWorld).filter(Objects::nonNull).toList();
    }

    public boolean isAllValid() {
        return wildCabbages.isValid() && wildOnions.isValid() && wildTomatoes.isValid() && wildRice.isValid();
    }
}
