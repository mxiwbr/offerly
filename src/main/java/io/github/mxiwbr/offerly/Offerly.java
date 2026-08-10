package io.github.mxiwbr.offerly;

import io.github.mxiwbr.offerly.config.Config;
import io.github.mxiwbr.offerly.registries.CommandRegistry;
import io.github.mxiwbr.offerly.services.UpdateService;
import io.github.mxiwbr.offerly.utils.ConsoleUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static io.github.mxiwbr.offerly.utils.ConsoleUtils.log;

public final class Offerly extends JavaPlugin {

    // Console logger
    public static Logger LOGGER;

    public static Offerly INSTANCE;
    public static Config CONFIG;
    public static Boolean newVersionAvailable;

    @Override
    public void onEnable() {

        // Global plugin instance object
        INSTANCE = this;

        this.getLogger().info("Loading config.yml...");
        // creates a default config.yml if there is none
        this.saveDefaultConfig();
        // Loads config defaults from plugin resource
        getConfig().options().copyDefaults(true);
        // writes missing config options
        saveConfig();
        // Creates a config object to get config values
        CONFIG = new Config();

        // Set logger object to log from other classes
        LOGGER = getLogger();
        log("Enabled!", ConsoleUtils.LogType.INFO);

        // bStats - only if enabled in config (default)
        if (CONFIG.isBstatsEnabled()) {

            try {

                final int bStatsPluginId = 33282;
                Metrics bStatsMetrics = new Metrics(this, bStatsPluginId);

            }
            catch (Exception e) {

                log("An error occurred while trying to establish bStats connection: " + e.getMessage(), ConsoleUtils.LogType.WARNING);

            }
        }

        newVersionAvailable = UpdateService.checkForUpdates();

        CommandRegistry.registerCommands();

    }
}
