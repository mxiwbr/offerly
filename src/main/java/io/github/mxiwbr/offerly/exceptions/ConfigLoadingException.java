package io.github.mxiwbr.offerly.exceptions;

/**
 * Gets thrown if a config value could not be set or found
 */
public class ConfigLoadingException extends RuntimeException {
    public ConfigLoadingException(String message) {
        super(message);
    }
}
