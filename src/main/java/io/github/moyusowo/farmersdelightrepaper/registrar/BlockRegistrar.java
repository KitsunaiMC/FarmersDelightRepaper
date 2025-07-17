package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.pot.CookingPotGUI;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.block.ArtisanSimpleBlock;
import io.github.moyusowo.neoartisanapi.api.block.state.ArtisanLeavesState;
import io.github.moyusowo.neoartisanapi.api.block.state.ArtisanThinState;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.LeavesAppearance;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.ThinAppearance;
import io.github.moyusowo.neoartisanapi.api.block.util.SoundProperty;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Sound;

public final class BlockRegistrar {

    private BlockRegistrar() {}

    @NeoArtisanAPI.Register
    public static void register() {
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
                                        .build()
                        )
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

}
