package io.github.mxiwbr.offerly.commands;

import io.github.mxiwbr.offerly.Offerly;
import io.github.mxiwbr.offerly.config.Config;
import io.github.mxiwbr.offerly.gui.MainMenu;
import io.github.mxiwbr.offerly.services.UpdateService;
import io.github.mxiwbr.offerly.utils.ConsoleUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import java.io.File;

import static io.github.mxiwbr.offerly.utils.ConsoleUtils.log;

/**
 * The class where all logic of the plugin's commands are located
 */
public class CommandActions {

    /**
     * Actions of the /offerly help command
     * @param player
     */
    public static void commandHelp(Player player) {

        player.sendMessage(Component.text("=== Offerly Commands ===", NamedTextColor.GOLD, TextDecoration.BOLD));
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text("/offerly help", NamedTextColor.GRAY)
                .append(Component.text(" - Writes this help page in the chat", NamedTextColor.WHITE)));

        // OP commands only visible to operators
        if (player.isOp()) {

            player.sendMessage(Component.text("/offerly disable", NamedTextColor.RED)
                    .append(Component.text(" - Disables the plugin", NamedTextColor.WHITE)));
            player.sendMessage(Component.text("/offerly enable", NamedTextColor.GREEN)
                    .append(Component.text(" - Enables the plugin", NamedTextColor.WHITE)));
            player.sendMessage(Component.text("/offerly version", NamedTextColor.AQUA)
                    .append(Component.text(" - Shows the current plugin version in the chat and checks for updates.", NamedTextColor.WHITE)));
            player.sendMessage(Component.text("/offerly reloadconfig", NamedTextColor.BLUE)
                    .append(Component.text(" - Reloads the plugin's config", NamedTextColor.WHITE)));
            player.sendMessage(Component.text("/offerly resetconfig", NamedTextColor.DARK_RED)
                    .append(Component.text(" - Resets the plugin’s config and automatically reloads it", NamedTextColor.WHITE)));

        }

        player.sendMessage(Component.text("/offerly marketplace", NamedTextColor.GOLD)
                .append(Component.text(" - Opens the marketplace menu.", NamedTextColor.WHITE)));

    }

    /**
     * Actions of the /offerly version command
     * @param player
     */
    public static void commandVersion(Player player) {

        try {

            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("You are using version "
                                    + Offerly.INSTANCE.getPluginMeta().getVersion()
                                    + " of this plugin. "
                                    + (UpdateService.checkForUpdates()
                                    ? "You're up-to-date!"
                                    : "There is a new version available: "
                                    + UpdateService.getLatestVersion()), NamedTextColor.GRAY)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

        }
        catch (Exception e) {

            player.sendMessage(Component.text("Something went wrong while executing the command ", NamedTextColor.RED)
                .append(Component.text("/offerly version", NamedTextColor.YELLOW))
                    .append(Component.text(". Please try again later.", NamedTextColor.RED)));

        }

    }

    /**
     * Actions of the /offerly enable command: Enables the plugin
     */
    public static void commandEnable(Player player) {

        Offerly.CONFIG.setPluginEnabled(true);
        Offerly.INSTANCE.getConfig().set("enabled", true);
        Offerly.INSTANCE.saveConfig();

        player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                .append(Component.text("The plugin was enabled.", NamedTextColor.GREEN)
                        .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

        log("The plugin was enabled by " + player.getName(), ConsoleUtils.LogType.ADDITIONAL_INFO);

    }

    /**
     * Actions of the /offerly disable command: Disables the plugin
     */
    public static void commandDisable(Player player) {

        Offerly.CONFIG.setPluginEnabled(false);
        Offerly.INSTANCE.getConfig().set("enabled", false);
        Offerly.INSTANCE.saveConfig();

        player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                .append(Component.text("The plugin was disabled.", NamedTextColor.RED)
                        .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

        log("The plugin was disabled by " + player.getName(), ConsoleUtils.LogType.ADDITIONAL_INFO);

    }

    /**
     * Reload the config: /offerly reload
     * @param player
     */
    public static void commandReloadConfig(Player player) {

        player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                .append(Component.text("Reloading config...", NamedTextColor.GREEN)
                        .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

        log("Reloading config...", ConsoleUtils.LogType.ADDITIONAL_INFO);

        if (!(new File(Offerly.INSTANCE.getDataFolder(), "config.yml").exists())) {

            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("No config.yml file could be found! Please use ", NamedTextColor.RED)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE))
                                     .append(Component.text("/offerly resetconfig", NamedTextColor.YELLOW)
                                             .clickEvent(ClickEvent.suggestCommand("/offerly resetconfig"))
                                             .hoverEvent(Component.text("Click to insert command", NamedTextColor.YELLOW))
                                             .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE))
                                                        .append(Component.text(" to create a new one.", NamedTextColor.RED)
                                                                    .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

            log("An error occurred when reloading the config: No config.yml file could be found!", ConsoleUtils.LogType.SEVERE);

            return;

        }

        Offerly.INSTANCE.reloadConfig();
        Offerly.CONFIG = new Config();

        if (Offerly.CONFIG.isLoadFailed()) {

            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("Reload of config failed! Please check the server log for more information.", NamedTextColor.RED)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));

        }
        else {

            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("Successfully reloaded the config!", NamedTextColor.GREEN)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));
            log("Successfully reloaded the config!", ConsoleUtils.LogType.ADDITIONAL_INFO);

        }

    }

    public static void commandResetConfig(Player player, boolean confirmed) {

        if (!confirmed) {
            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("Warning: This will reset all values in config.yml! Use ", NamedTextColor.RED)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE))
                                    .append(Component.text("/offerly resetconfig confirm", NamedTextColor.YELLOW)
                                            .clickEvent(ClickEvent.suggestCommand("/offerly resetconfig confirm"))
                                            .hoverEvent(Component.text("Click to insert command", NamedTextColor.YELLOW))
                                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE))
                                                    .append(Component.text(" to proceed.", NamedTextColor.RED)
                                                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));
        }
        else {
            Config.resetConfigFile();
            player.sendMessage(Component.text("[Offerly] ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append(Component.text("The config has been reset and reloaded successfully.", NamedTextColor.GREEN)
                            .decorationIfAbsent(TextDecoration.BOLD, TextDecoration.State.FALSE)));
            log("The config has been reset by " + player.getName(), ConsoleUtils.LogType.INFO);
        }

    }

    public static void commandMarketplace(Player player) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.open(player);

    }

}
