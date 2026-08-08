package io.github.mxiwbr.offerly.config;

import io.github.mxiwbr.offerly.Offerly;
import io.github.mxiwbr.offerly.exceptions.ConfigLoadingException;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class Config {

    @Setter
    // enabled status
    private boolean pluginEnabled;
    private boolean enableConsoleLogging;
    private boolean enableAdditionalConsoleLogging;
    private boolean bstatsEnabled;

    // Is set to true if the config couldn't be loaded and the default values where used
    private boolean loadFailed;

    /**
     * Contains all configuration settings as private variables with getters. The constructor gets all values from the config.yml file or uses defaults, if none could be found.
     */
    public Config() {

        final FileConfiguration config = Offerly.INSTANCE.getConfig();

        try {

            // enabled status
            this.pluginEnabled = config.getBoolean("enabled");

            if (!config.isBoolean("console.enable-logging")) {
                throw new ConfigLoadingException("Error when loading console.enable-logging. Has to be either true or false.");
            }
            this.enableConsoleLogging = config.getBoolean("console.enable-logging");

            if (!config.isBoolean("console.enable-additional-logging")) {
                throw new ConfigLoadingException("Error when loading console.enable-additional-logging. Has to be either true or false.");
            }
            this.enableAdditionalConsoleLogging = config.getBoolean("console.enable-additional-logging");

            if (!config.isBoolean("bstats.enabled")) {
                throw new ConfigLoadingException("Error when loading bstats.enabled. Has to be either true or false.");
            }
            this.bstatsEnabled = config.getBoolean("bstats.enabled");

            this.loadFailed = false;
            Offerly.INSTANCE.getLogger().info("Successfully loaded config.yml.");

        // Set to defaults if config couldn't be loaded
        } catch (Exception e) {

            Offerly.INSTANCE.getLogger().severe("Failed to load config.yml, using default config: " + e.getMessage());
            Offerly.INSTANCE.getLogger().severe("If you think that this is a bug, please create an issue: https://github.com/mxiwbr/capture-bioms/issues");

            this.pluginEnabled = true;
            this.enableConsoleLogging = true;
            this.enableAdditionalConsoleLogging = false;
            this.bstatsEnabled = true;

            this.loadFailed = true;

        }

    }

    /**
     * Resets the config.yml to default and automatically applies the new values
     */
    public static void resetConfigFile() {

        Offerly.CONFIG.pluginEnabled = true;
        Offerly.CONFIG.enableConsoleLogging = true;
        Offerly.CONFIG.enableAdditionalConsoleLogging = false;
        Offerly.CONFIG.bstatsEnabled = true;
        Offerly.CONFIG.loadFailed = false;

        Offerly.INSTANCE.getConfig().set("enabled", true);
        Offerly.INSTANCE.getConfig().set("console.enable-logging", true);
        Offerly.INSTANCE.getConfig().set("console.enable-additional-logging", false);
        Offerly.INSTANCE.getConfig().set("bstats.enabled", true);

        Offerly.INSTANCE.saveConfig();

    }

}
