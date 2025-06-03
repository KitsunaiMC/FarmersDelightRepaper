package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.farmersdelightrepaper.pot.GUIHolder;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.base.sound.SoundProperty;
import io.github.moyusowo.neoartisanapi.api.block.thin.ArtisanThinBlock;
import io.github.moyusowo.neoartisanapi.api.block.thin.ArtisanThinBlockState;
import io.github.moyusowo.neoartisanapi.api.block.thin.ThinBlockAppearance;
import io.github.moyusowo.neoartisanapi.api.block.transparent.ArtisanTransparentBlock;
import io.github.moyusowo.neoartisanapi.api.block.transparent.ArtisanTransparentBlockState;
import io.github.moyusowo.neoartisanapi.api.block.transparent.TransparentAppearance;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Sound;

import java.util.List;

public final class BlockRegistrar {

    private BlockRegistrar() {}

    @NeoArtisanAPI.Register
    public static void register() {
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanTransparentBlock.builder()
                        .blockId(Keys.cooking_pot)
                        .canBurn(false)
                        .placeSound(
                                SoundProperty.of(Sound.BLOCK_STONE_PLACE)
                        )
                        .guiCreator(new GUIHolder())
                        .states(
                                List.of(
                                        ArtisanTransparentBlockState.builder()
                                                .appearanceState(
                                                        new TransparentAppearance(
                                                                TransparentAppearance.LeavesAppearance.OAK_LEAVES,
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
                        )
                        .build()
        );
        NeoArtisanAPI.getBlockRegistry().register(
                ArtisanThinBlock.builder()
                        .blockId(Keys.cutting_board)
                        .states(
                                List.of(
                                        ArtisanThinBlockState.builder()
                                                .appearanceState(
                                                        new ThinBlockAppearance(
                                                                ThinBlockAppearance.PressurePlateAppearance.LIGHT_WEIGHTED_PRESSURE_PLATE,
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
                        )
                        .placeSound(
                                SoundProperty.of(Sound.BLOCK_WOOD_PLACE)
                        )
                        .build()
        );
    }

}
