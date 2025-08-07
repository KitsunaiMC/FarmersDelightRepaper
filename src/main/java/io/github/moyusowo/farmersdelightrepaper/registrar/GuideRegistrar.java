package io.github.moyusowo.farmersdelightrepaper.registrar;

import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.farmersdelightrepaper.board.CuttingBoardRecipe;
import io.github.moyusowo.farmersdelightrepaper.guide.CookingGuideGenerator;
import io.github.moyusowo.farmersdelightrepaper.guide.CuttingGuideGenerator;
import io.github.moyusowo.farmersdelightrepaper.pot.CookingPotRecipe;
import io.github.moyusowo.farmersdelightrepaper.resource.Keys;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public final class GuideRegistrar {
    private GuideRegistrar() {}

    public static void initOnEnable() {
        registerCategory();
        registerGuideGenerator();
        registerGuideBook();
    }

    private static void registerCategory() {
        Registries.GUIDE.registerCategory(Keys.category_food, () -> {
            final ItemStack icon = Registries.ITEM.getItemStack(Keys.tomato);
            icon.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<yellow>农夫乐事 - 食物</yellow>"));
            return icon;
        });
        Registries.GUIDE.registerCategory(Keys.category_tool, () -> {
            final ItemStack icon = Registries.ITEM.getItemStack(Keys.diamond_knife);
            icon.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<yellow>农夫乐事 - 工具</yellow>"));
            return icon;
        });
    }

    private static void registerGuideGenerator() {
        Registries.GUIDE.setGuideGenerator(CookingPotRecipe.TYPE, new CookingGuideGenerator());
        Registries.GUIDE.setGuideGenerator(CuttingBoardRecipe.TYPE, new CuttingGuideGenerator());
    }

    private static void registerGuideBook() {
        Registries.GUIDE.registerGuideBook(
                FarmersDelightRepaper.getInstance(),
                () -> {
                    final ItemStack itemStack = new ItemStack(Material.BOOK);
                    itemStack.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<yellow>农夫乐事配方书</yellow>"));
                    itemStack.setData(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);
                    return itemStack;
                },
                Set.of(
                        Keys.category_food,
                        Keys.category_tool
                )stop


        );
    }
}
