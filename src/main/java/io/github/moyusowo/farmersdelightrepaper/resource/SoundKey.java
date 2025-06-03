package io.github.moyusowo.farmersdelightrepaper.resource;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import org.bukkit.NamespacedKey;

public final class SoundKey {

    private SoundKey() {}

    public static final NamespacedKey cooking_pot_boil = FarmersDelightRepaper.create("block.cooking_pot.boil"),
        cutting_board_knife_cut = FarmersDelightRepaper.create("block.cutting_board.knife"),
        cooking_pot_boil_soup = FarmersDelightRepaper.create("block.cooking_pot.boil_soup");
}
