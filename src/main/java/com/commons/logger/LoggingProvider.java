package com.commons.logger;

public interface LoggingProvider {
    void info(Object context, String message);

    void error(Object context, String message);

    void debug(Object context, String message);

    void trace(Object context, String message);
}
