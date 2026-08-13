package io.github.mxiwbr.offerly.registries;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.mxiwbr.offerly.Offerly;
import io.github.mxiwbr.offerly.commands.CommandActions;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.entity.Player;

public class CommandRegistry {

    /**
     * Registers all commands by the plugin
     */
    public static void registerCommands() {

        Offerly.INSTANCE.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {

            // Root Command /offerly
            LiteralArgumentBuilder<CommandSourceStack> rootCommand = Commands.literal("offerly");

            rootCommand.then(Commands.literal("disable")
                    // require operator permission
                    .requires(src -> src.getSender() instanceof Player player && player.isOp())
                    .executes(ctx -> {

                        CommandActions.commandDisable((Player) ctx.getSource().getSender());
                        return 1;

                    }));

            rootCommand.then(Commands.literal("enable")
                    // require operator permission
                    .requires(src -> src.getSender() instanceof Player player && player.isOp())
                    .executes(ctx -> {

                        CommandActions.commandEnable((Player) ctx.getSource().getSender());
                        return 1;

                    }));

            rootCommand.then(Commands.literal("help")
                    .executes(ctx -> {

                        Player player = (Player) ctx.getSource().getSender();
                        CommandActions.commandHelp(player);
                        return 1;

                    }));

            // Lets a user get the current plugin version and informs about updates
            rootCommand.then(Commands.literal("version")
                    // require operator permission
                    .requires(src -> src.getSender() instanceof Player player && player.isOp())
                    .executes(ctx -> {

                        Player player = (Player) ctx.getSource().getSender();
                        CommandActions.commandVersion(player);
                        return 1;

                    }));

            // Reloads the plugin's config
            rootCommand.then(Commands.literal("reloadconfig")
                    // require operator permission
                    .requires(src -> src.getSender() instanceof Player player && player.isOp())
                    .executes(ctx -> {

                        Player player = (Player) ctx.getSource().getSender();
                        CommandActions.commandReloadConfig(player);
                        return 1;

                    }));

            rootCommand.then(Commands.literal("resetconfig")
                    // require operator permission
                    .requires(src -> src.getSender() instanceof Player player && player.isOp())
                    .executes(ctx -> {

                        CommandActions.commandResetConfig((Player) ctx.getSource().getSender(), false);
                        return 1;
                    })

                    .then(Commands.literal("confirm")
                            .executes(ctx -> {

                                CommandActions.commandResetConfig((Player) ctx.getSource().getSender(), true);
                                return 1;
                            })
                    )
            );

            rootCommand.then(Commands.literal("marketplace")
                    .executes(ctx -> {

                        Player player = (Player) ctx.getSource().getSender();
                        CommandActions.commandMarketplace(player);
                        return 1;

                    }));

            // single /marketplace command without having to use the /offerly root command
            LiteralArgumentBuilder<CommandSourceStack> marketplaceCommand = Commands.literal("marketplace")
                    .executes(ctx -> {

                        if (!(ctx.getSource().getSender() instanceof Player player)) {
                            return 0;
                        }

                        CommandActions.commandMarketplace(player);
                        return 1;

                    });

            // register commands
            event.registrar().register(rootCommand.build());
            event.registrar().register(marketplaceCommand.build());

        });

    }

}
