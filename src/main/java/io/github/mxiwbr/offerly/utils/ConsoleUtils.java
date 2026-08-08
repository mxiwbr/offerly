package io.github.mxiwbr.offerly.utils;

import io.github.mxiwbr.offerly.Offerly;

public class ConsoleUtils {

    /**
     * The types of console logging messages:
     * INFO, ADDITIONAL_INFO, WARNING, SEVERE
     */
    public enum LogType {

        INFO,

        ADDITIONAL_INFO,
        WARNING,
        SEVERE,

    }

    /**
     * Logs something in the console if console logging is enabled in the config.yml
     * @param message the message which should be logged in the server console
     * @param type the type of the log (INFO, ADDITIONAL_INFO, WARNING, SEVERE)
     */
    public static void log(String message, LogType type) {

        if (Offerly.CONFIG.isEnableConsoleLogging()) {

            switch (type) {

                case INFO:
                    Offerly.LOGGER.info(message);
                    break;
                case ADDITIONAL_INFO:
                    if (Offerly.CONFIG.isEnableAdditionalConsoleLogging()) {
                        Offerly.LOGGER.info(message);
                    }
                    break;
                case WARNING:
                    Offerly.LOGGER.warning(message);
                    break;
                case SEVERE:
                    Offerly.LOGGER.severe(message);
                    break;

            }

        }

    }

}
