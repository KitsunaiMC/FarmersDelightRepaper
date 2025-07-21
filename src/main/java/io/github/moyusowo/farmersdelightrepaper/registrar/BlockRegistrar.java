package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.pot.CookingPotGUI;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.block.ArtisanCropBlock;
import io.github.moyusowo.neoartisanapi.api.block.block.ArtisanSimpleBlock;
import io.github.moyusowo.neoartisanapi.api.block.state.ArtisanCropState;
import io.github.moyusowo.neoartisanapi.api.block.state.ArtisanLeavesState;
import io.github.moyusowo.neoartisanapi.api.block.state.ArtisanThinState;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.LeavesAppearance;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.ThinAppearance;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.crop.SugarCaneAppearance;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.crop.TripwireAppearance;
import io.github.moyusowo.neoartisanapi.api.block.util.SoundProperty;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.List;

public final class BlockRegistrar {

    private BlockRegistrar() {}

    public static void initOnEnable() {
        block();
        wildCrop();
        crop();
    }

    private static void block() {
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.cooking_pot)
                        .placeSound(
                                SoundProperty.of(Sound.BLOCK_STONE_PLACE)
                        )
                        .guiCreator(CookingPotGUI::new)
                        .state(
                                ArtisanLeavesState.builder()
                                        .appearance(
                                                new LeavesAppearance(
                                                        LeavesAppearance.LeavesMaterial.OAK_LEAVES,
                                                        1,
                                                        false,
                                                        false
                                                )
                                        )
                                        .generators(
                                                new ItemGenerator[]{
                                                        ItemGenerator.simpleGenerator(
                                                                Keys.cooking_pot,
                                                                1
                                                        )
                                                }
                                        )
                                        .hardness(2.0f)
                                        .build()
                        )
                        .blockEntity()
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.cutting_board)
                        .state(
                                ArtisanThinState.builder()
                                        .appearance(
                                                new ThinAppearance(
                                                        ThinAppearance.ThinMaterial.LIGHT_WEIGHTED_PRESSURE_PLATE,
                                                        2
                                                )
                                        )
                                        .generators(
                                                new ItemGenerator[]{
                                                        ItemGenerator.simpleGenerator(
                                                                Keys.cutting_board,
                                                                1
                                                        )
                                                }
                                        )
                                        .build()
                        )
                        .placeSound(
                                SoundProperty.of(Sound.BLOCK_WOOD_PLACE)
                        )
                        .build()
        );
    }

    private static void crop() {
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanCropBlock.builder()
                        .blockId(Keys.onion)
                        .placeSound(
                                SoundProperty.of(Sound.ITEM_CROP_PLANT)
                        )
                        .states(
                                List.of(
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.onion, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.onion, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.onion, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.rangedGenerator(Keys.onion, 2, 5)
                                                        }
                                                )
                                                .build()
                                )
                        )
                        .boneMealMinGrowth(0)
                        .boneMealMaxGrowth(1)
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanCropBlock.builder()
                        .blockId(Keys.cabbage)
                        .placeSound(
                                SoundProperty.of(Sound.ITEM_CROP_PLANT)
                        )
                        .states(
                                List.of(
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        true
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.cabbage_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false,
                                                        true
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.cabbage_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.cabbage_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        true,
                                                        false,
                                                        false,
                                                        true,
                                                        true,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.rangedGenerator(Keys.onion, 2, 5)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        true
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.rangedGenerator(Keys.onion, 2, 5)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false,
                                                        true
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.rangedGenerator(Keys.onion, 2, 5)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        false,
                                                        true,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.cabbage_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new TripwireAppearance(
                                                        false,
                                                        false,
                                                        false,
                                                        true,
                                                        true,
                                                        false,
                                                        false
                                                ))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.cabbage, 1),
                                                                ItemGenerator.rangedGenerator(Keys.cabbage_seed, 1, 3)
                                                        }
                                                )
                                                .build()
                                )
                        )
                        .boneMealMinGrowth(1)
                        .boneMealMaxGrowth(3)
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanCropBlock.builder()
                        .blockId(Keys.tomato)
                        .placeSound(SoundProperty.of(Sound.ITEM_CROP_PLANT))
                        .boneMealMinGrowth(0)
                        .boneMealMaxGrowth(1)
                        .states(
                                List.of(
                                        ArtisanCropState.builder()
                                                .appearance(new SugarCaneAppearance(5))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.tomato_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new SugarCaneAppearance(6))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.tomato_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new SugarCaneAppearance(7))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.tomato_seed, 1)
                                                        }
                                                )
                                                .build(),
                                        ArtisanCropState.builder()
                                                .appearance(new SugarCaneAppearance(8))
                                                .generators(
                                                        new ItemGenerator[] {
                                                                ItemGenerator.simpleGenerator(Keys.tomato, 1),
                                                                ItemGenerator.rangedGenerator(Keys.tomato_seed, 1, 3)
                                                        }
                                                )
                                                .build()
                                )
                        )
                        .build()
        );
    }

    private static void wildCrop() {
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.wild_cabbages)
                        .state(
                                ArtisanCropState.builder()
                                        .appearance(new SugarCaneAppearance(1))
                                        .generators(new ItemGenerator[]{
                                                ItemGenerator.simpleGenerator(Keys.cabbage_seed, 1),
                                                ItemGenerator.chanceGenerator(Keys.cabbage, 1, 0.05)
                                        })
                                        .build()
                        )
                        .placeSound(SoundProperty.of(Sound.ITEM_CROP_PLANT))
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.wild_onions)
                        .state(
                                ArtisanCropState.builder()
                                        .appearance(new SugarCaneAppearance(2))
                                        .generators(new ItemGenerator[]{
                                                ItemGenerator.simpleGenerator(Keys.onion, 1),
                                                ItemGenerator.simpleGenerator(Material.ALLIUM.getKey(), 1)
                                        })
                                        .build()
                        )
                        .placeSound(SoundProperty.of(Sound.ITEM_CROP_PLANT))
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.wild_rice)
                        .state(
                                ArtisanCropState.builder()
                                        .appearance(new SugarCaneAppearance(3))
                                        .generators(new ItemGenerator[]{
                                                ItemGenerator.simpleGenerator(Keys.rice_panicle, 1),
                                                ItemGenerator.chanceGenerator(Keys.rice, 1, 0.05)
                                        })
                                        .build()
                        )
                        .placeSound(SoundProperty.of(Sound.ITEM_CROP_PLANT))
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanSimpleBlock.builder()
                        .blockId(Keys.wild_tomatoes)
                        .state(
                                ArtisanCropState.builder()
                                        .appearance(new SugarCaneAppearance(4))
                                        .generators(new ItemGenerator[]{
                                                ItemGenerator.simpleGenerator(Keys.tomato_seed, 1),
                                                ItemGenerator.chanceGenerator(Keys.tomato, 1, 0.05)
                                        })
                                        .build()
                        )
                        .placeSound(SoundProperty.of(Sound.ITEM_CROP_PLANT))
                        .build()
        );
    }

}
