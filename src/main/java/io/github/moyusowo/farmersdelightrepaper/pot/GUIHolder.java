package io.github.moyusowo.farmersdelightrepaper.pot;

import io.github.moyusowo.neoartisanapi.api.block.gui.ArtisanBlockGUI;
import io.github.moyusowo.neoartisanapi.api.block.gui.GUICreator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class GUIHolder implements GUICreator {

    public GUIHolder() {}

    @Override
    public @NotNull ArtisanBlockGUI create(Location location) {
        return new CookingPotGUI(location);
    }
}
