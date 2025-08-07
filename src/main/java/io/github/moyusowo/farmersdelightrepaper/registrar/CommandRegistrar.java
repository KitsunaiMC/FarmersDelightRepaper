package io.github.moyusowo.farmersdelightrepaper.registrar;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.moyusowo.farmersdelightrepaper.FarmersDelightRepaper;
import io.github.moyusowo.neoartisanapi.api.registry.Registries;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class CommandRegistrar {
    private CommandRegistrar() {}

    private static final LiteralArgumentBuilder<CommandSourceStack> command =
            Commands.literal("farmersdelightrepaper").then(
                    Commands.literal("guide").executes(
                            ctx -> {
                                if (ctx.getSource().getSender() instanceof Player player) {
                                    player.give(Registries.GUIDE.getGuideBook(FarmersDelightRepaper.getInstance()));
                                } else {
                                    ctx.getSource().getSender().sendMessage(
                                            Component.text("你必须是一名玩家！").color(TextColor.color(255, 0, 0))
                                    );
                                }
                                return 1;
                            }
                    )
            );

    private static final LiteralCommandNode<CommandSourceStack> buildCommand = command.build();

    public static void initOnEnable() {
        FarmersDelightRepaper.getInstance().getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands ->commands.registrar().register(buildCommand, List.of("fd"))
        );
    }
}
