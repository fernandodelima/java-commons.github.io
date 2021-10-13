package com.commons.logger;

import java.util.logging.Logger;

public class JulLoggingProvider implements LoggingProvider {
    public JulLoggingProvider() {
    }

    public void info(Object source, String message) {
        getLogger(source).info(message);
    }

    public void error(Object source, String message) {
        getLogger(source).severe(message);
    }

    public void debug(Object source, String message) {
        getLogger(source).fine(message);
    }

    public void trace(Object source, String message) {
        getLogger(source).finest(message);
    }

    private static java.util.logging.Logger getLogger(Object source) {
        return Logger.getLogger(source.getClass().getName());
    }
}
