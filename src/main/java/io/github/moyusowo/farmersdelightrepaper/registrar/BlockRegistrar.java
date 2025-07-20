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
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.crop.TripwireAppearance;
import io.github.moyusowo.neoartisanapi.api.block.util.SoundProperty;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Sound;

import java.util.List;

public final class BlockRegistrar {

    private BlockRegistrar() {}

    public static void initOnEnable() {
        block();
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
                        .boneMealMaxGrowth(2)
                        .build()
        );
    }

}
