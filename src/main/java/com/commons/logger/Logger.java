package com.commons.logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Logger {
    private static LoggingProvider provider = new JulLoggingProvider();
    private static ThreadLocal<Logger.LoggerContext> loggerContextThreadLocal = ThreadLocal.withInitial(() -> {
        return new Logger.LoggerContext(UUID.randomUUID().toString(), new LinkedHashMap());
    });

    public Logger() {
    }

    public static void setLoggingProvider(LoggingProvider provider) {
        Logger.provider = provider;
    }

    public static Logger.LoggerContext getLoggerContext() {
        return ((Logger.LoggerContext)loggerContextThreadLocal.get()).cloneContext();
    }

    public static void setLoggerContext(Logger.LoggerContext context) {
        loggerContextThreadLocal.set(context.cloneContext());
    }

    public static String startLogId() {
        String logId = UUID.randomUUID().toString();
        setLogId(logId);
        return logId;
    }

    public static void setLogId(String logId) {
        ((Logger.LoggerContext)loggerContextThreadLocal.get()).logId = logId;
    }

    public static String getLogId() {
        return ((Logger.LoggerContext)loggerContextThreadLocal.get()).logId;
    }

    public static void setElapsedTime(Long elapsedTime) {
        ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags.put("elapsedTime", String.format("%dms", elapsedTime));
    }

    public static void unsetElapsedTime() {
        ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags.remove("elapsedTime");
    }

    public static void setKibanaTag(String key, String value) {
        Map<String, String> tags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        tags.put(key, value);
    }

    public static void setKibanaTags(Map<String, Object> tags) {
        Map<String, String> kibanaTags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        tags.forEach((key, value) -> {
            String var10000 = (String)kibanaTags.put(key, String.valueOf(value));
        });
    }

    public static void removeKibanaTag(String key) {
        Map<String, String> tags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        tags.remove(key);
    }

    public static void removeKibanaTags(List<String> keys) {
        Map<String, String> kibanaTags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        keys.forEach(kibanaTags::remove);
    }

    public static void clearKibanaTags() {
        Map<String, String> kibanaTags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        kibanaTags.clear();
    }

    private static String getTagsString() {
        StringBuilder sb = new StringBuilder();
        Map<String, String> tags = ((Logger.LoggerContext)loggerContextThreadLocal.get()).kibanaTags;
        tags.forEach((key, value) -> {
            sb.append(String.format("[%s:%s]", key, value));
        });
        return sb.toString();
    }

    public static void error(Object source, String text) {
        provider.error(source, getText(text));
    }

    public static void error(Object source, String text, Throwable ex) {
        provider.error(source, String.format("%s | Exception: %s", getText(text), String.valueOf(ex)));
    }

    public static void info(Object source, String text) {
        provider.info(source, getText(text));
    }

    public static void trace(Object source, String text) {
        provider.trace(source, getText(text));
    }

    public static void debug(Object source, String text) {
        provider.debug(source, getText(text));
    }

    private static String getText(String text) {
        return String.format("[logId:%s]%s %s", getLogId(), getTagsString(), text);
    }

    static class LoggerContext {
        String logId;
        Map<String, String> kibanaTags;

        LoggerContext(String logId, Map<String, String> kibanaTags) {
            this.logId = logId;
            this.kibanaTags = new LinkedHashMap(kibanaTags);
        }

        Logger.LoggerContext cloneContext() {
            return new Logger.LoggerContext(this.logId, this.kibanaTags);
        }
    }
}
