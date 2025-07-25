package io.github.moyusowo.farmersdelightrepaper.wild;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.block.data.ArtisanBlockData;
import io.github.moyusowo.neoartisanapi.api.block.storage.Storages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class GeneratorListener implements Listener {
    private GeneratorListener() {}

    public static void initOnEnable() {
        Bukkit.getPluginManager().registerEvents(new GeneratorListener(), FarmersDelightRepaper.getInstance());
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        World world = Bukkit.getWorld("world");
        if (world != null) {
            world.getPopulators().add(new WildOnionsPopulator());
            world.getPopulators().add(new WildCabbagesPopulator());
            world.getPopulators().add(new WildTomatoesPopulator());
            world.getPopulators().add(new WildRicePopulator());
            FarmersDelightRepaper.getInstance().getLogger().info("successfully register Block Populator");
        } else {
            FarmersDelightRepaper.getInstance().getLogger().severe("Fail to register Block Populator");
        }
    }

    private static final class WildCabbagesPopulator extends BlockPopulator {
        @Override
        public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
            final int size = random.nextInt(0, 3);
            label: for (int i = 0; i < size; i++) {
                final int x = (chunkX << 4) + random.nextInt(0, 16), z = (chunkZ << 4) + random.nextInt(0, 16);
                int y = limitedRegion.getHighestBlockYAt(x, z);
                while (limitedRegion.getBlockState(x, y, z).getType() == Material.AIR) {
                    if (y > -64) y--;
                    else continue label;
                }
                if (limitedRegion.getBiome(x, y, z) == Biome.BEACH && limitedRegion.getBlockState(x, y, z).getType() == Material.SAND) {
                    limitedRegion.setBlockData(x, y + 1, z, Material.DEAD_BUSH.createBlockData());
                    Storages.BLOCK.setArtisanBlockData(
                            worldInfo.getUID(),
                            x,
                            y + 1,
                            z,
                            ArtisanBlockData.builder()
                                    .blockId(Keys.wild_cabbages)
                                    .location(new Location(Bukkit.getWorld(worldInfo.getUID()), x, y + 1, z))
                                    .stage(0)
                                    .build()
                    );
                }
            }
        }
    }

    private static final class WildOnionsPopulator extends BlockPopulator {
        @Override
        public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
            final int size = random.nextInt(0, 3);
            label: for (int i = 0; i < size; i++) {
                final int x = (chunkX << 4) + random.nextInt(0, 16), z = (chunkZ << 4) + random.nextInt(0, 16);
                int y = limitedRegion.getHighestBlockYAt(x, z);
                while (limitedRegion.getBlockState(x, y, z).getType() == Material.AIR) {
                    if (y > -64) y--;
                    else continue label;
                }
                if (BiomeTemperature.warmBiomes.contains(limitedRegion.getBiome(x, y, z)) && limitedRegion.getBlockState(x, y, z).getType() == Material.GRASS_BLOCK) {
                    limitedRegion.setBlockData(x, y + 1, z, Material.DEAD_BUSH.createBlockData());
                    Storages.BLOCK.setArtisanBlockData(
                            worldInfo.getUID(),
                            x,
                            y + 1,
                            z,
                            ArtisanBlockData.builder()
                                    .blockId(Keys.wild_onions)
                                    .location(new Location(Bukkit.getWorld(worldInfo.getUID()), x, y + 1, z))
                                    .stage(0)
                                    .build()
                    );
                }
            }
        }
    }

    private static final class WildTomatoesPopulator extends BlockPopulator {
        @Override
        public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
            final int size = random.nextInt(0, 3);
            label: for (int i = 0; i < size; i++) {
                final int x = (chunkX << 4) + random.nextInt(0, 16), z = (chunkZ << 4) + random.nextInt(0, 16);
                int y = limitedRegion.getHighestBlockYAt(x, z);
                while (limitedRegion.getBlockState(x, y, z).getType() == Material.AIR) {
                    if (y > -64) y--;
                    else continue label;
                }
                if (BiomeTemperature.hotBiomes.contains(limitedRegion.getBiome(x, y, z)) && limitedRegion.getBlockState(x, y, z).getType() == Material.GRASS_BLOCK) {
                    limitedRegion.setBlockData(x, y + 1, z, Material.DEAD_BUSH.createBlockData());
                    Storages.BLOCK.setArtisanBlockData(
                            worldInfo.getUID(),
                            x,
                            y + 1,
                            z,
                            ArtisanBlockData.builder()
                                    .blockId(Keys.wild_tomatoes)
                                    .location(new Location(Bukkit.getWorld(worldInfo.getUID()), x, y + 1, z))
                                    .stage(0)
                                    .build()
                    );
                }
            }
        }
    }

    private static final class WildRicePopulator extends BlockPopulator {
        @Override
        public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
            final int size = random.nextInt(0, 2);
            label: for (int i = 0; i < size; i++) {
                final int x = (chunkX << 4) + random.nextInt(0, 16), z = (chunkZ << 4) + random.nextInt(0, 16);
                int y = limitedRegion.getHighestBlockYAt(x, z);
                while (limitedRegion.getBlockState(x, y, z).getType() == Material.AIR) {
                    if (y > -64) y--;
                    else continue label;
                }
                if ((limitedRegion.getBiome(x, y, z) == Biome.JUNGLE || limitedRegion.getBiome(x, y, z) == Biome.SWAMP) && limitedRegion.getBlockState(x, y, z).getType() == Material.GRASS_BLOCK) {
                    limitedRegion.setBlockData(x, y + 1, z, Material.DEAD_BUSH.createBlockData());
                    Storages.BLOCK.setArtisanBlockData(
                            worldInfo.getUID(),
                            x,
                            y + 1,
                            z,
                            ArtisanBlockData.builder()
                                    .blockId(Keys.wild_rice)
                                    .location(new Location(Bukkit.getWorld(worldInfo.getUID()), x, y + 1, z))
                                    .stage(0)
                                    .build()
                    );
                } else if (limitedRegion.getBlockState(x, y, z).getType() == Material.GRASS_BLOCK) {
                    final int[] xOffsets = new int[] { -1, 1, 0, 0 };
                    final int[] zOffsets = new int[] { 0, 0, -1, 1 };
                    for (int o = 0; o < 4; o++) {
                        if (!limitedRegion.isInRegion(x + xOffsets[i], y, z + zOffsets[i]) || limitedRegion.getBlockState(x + xOffsets[i], y, z + zOffsets[i]).getType() != Material.WATER) {
                            continue label;
                        }
                    }
                    limitedRegion.setBlockData(x, y + 1, z, Material.DEAD_BUSH.createBlockData());
                    Storages.BLOCK.setArtisanBlockData(
                            worldInfo.getUID(),
                            x,
                            y + 1,
                            z,
                            ArtisanBlockData.builder()
                                    .blockId(Keys.wild_rice)
                                    .location(new Location(Bukkit.getWorld(worldInfo.getUID()), x, y + 1, z))
                                    .stage(0)
                                    .build()
                    );
                }
            }
        }
    }
}
