package io.github.moyusowo.farmersdelightrepaper.wild;

import org.bukkit.block.Biome;

import java.util.Set;

public final class BiomeTemperature {
    private BiomeTemperature() {}

    public static final Set<Biome> warmBiomes = Set.of(
            Biome.PALE_GARDEN,
            Biome.MEADOW,
            Biome.JUNGLE,
            Biome.BAMBOO_JUNGLE,
            Biome.DARK_FOREST,
            Biome.MANGROVE_SWAMP,
            Biome.BIRCH_FOREST,
            Biome.OLD_GROWTH_BIRCH_FOREST,
            Biome.MUSHROOM_FIELDS,
            Biome.PLAINS,
            Biome.BEACH,
            Biome.SUNFLOWER_PLAINS,
            Biome.FOREST,
            Biome.FLOWER_FOREST,
            Biome.SPARSE_JUNGLE,
            Biome.CHERRY_GROVE,
            Biome.OLD_GROWTH_PINE_TAIGA,
            Biome.SWAMP
    ), hotBiomes = Set.of(
            Biome.BADLANDS,
            Biome.ERODED_BADLANDS,
            Biome.WOODED_BADLANDS,
            Biome.SAVANNA,
            Biome.SAVANNA_PLATEAU,
            Biome.WINDSWEPT_SAVANNA,
            Biome.DESERT
    );

}
