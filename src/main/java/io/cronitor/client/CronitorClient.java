package io.cronitor.client;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class CronitorClient {

    private Logger logger = Logger.getLogger(CronitorClient.class.getName());

    private CronitorPinger cronitorPinger = new CronitorPinger();

    private String apiKey;
    private Boolean useHttps;
    private String env;

    public CronitorClient() {

        this.apiKey = null;
        this.useHttps = true;
        this.env = null;
    }

    public CronitorClient(String apiKey) {

        this.apiKey = apiKey;
        this.useHttps = true;
        this.env = null;
    }

    public CronitorClient(String apiKey, String env) {

        this.apiKey = apiKey;
        this.useHttps = true;
        this.env = env;
    }

    public CronitorClient(Boolean useHttps) {
        this.useHttps = useHttps;
        this.apiKey = null;
        this.env = null;
    }

    public static CronitorClient withoutHttps() {
        return new CronitorClient(false);
    }

    public void run(String monitorKey) throws IOException {
        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.RUN.getValue(), monitorKey, apiKey, env, null, null, useHttps);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void run(String monitorKey, String message) throws IOException {
        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.RUN.getValue(), monitorKey, apiKey, env, message, null, useHttps);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void run(String monitorKey, String message, Map<String, Integer> metrics) throws IOException {
        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.RUN.getValue(), monitorKey, apiKey, env, message, metrics, useHttps);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorKey) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.COMPLETE.getValue(), monitorKey, apiKey, env, null, null, useHttps);
        } else {
            logger.warning("We can't ping /run on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorKey, String message) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.COMPLETE.getValue(), monitorKey, apiKey, env, message, null, useHttps);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void complete(String monitorKey, String message, Map<String, Integer> metrics) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.COMPLETE.getValue(), monitorKey, apiKey, env, message, metrics, useHttps);
        } else {
            logger.warning("We can't ping /complete on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorKey) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.FAIL.getValue(), monitorKey, apiKey, env, null, null, useHttps);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorKey, String message) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.FAIL.getValue(), monitorKey, apiKey, env, message, null, useHttps);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void fail(String monitorKey, String message, Map<String, Integer> metrics) throws IOException {

        if (StringUtils.isNotBlank(monitorKey)) {
            cronitorPinger.ping(Command.FAIL.getValue(), monitorKey, apiKey, env, message, metrics, useHttps);
        } else {
            logger.warning("We can't ping /fail on cronitor because the monitor code is null or empty");
        }
    }

    public void pause(String monitorKey, int hours) throws IOException {
        if (StringUtils.isNotBlank(apiKey)) {
            if (StringUtils.isNotBlank(monitorKey)) {
                cronitorPinger.pause(monitorKey, hours, apiKey);
            } else {
                logger.warning("Monitor key cannot be blank.");
            }
        } else {
            logger.warning("Set an API key to call pause.");
        }
    }

    public void unpause(String monitorKey) throws IOException {

        if (StringUtils.isNotBlank(apiKey)) {
            if (StringUtils.isNotBlank(monitorKey)) {
                cronitorPinger.pause(monitorKey, 0, apiKey);
            } else {
                logger.warning("Monitor key cannot be blank.");
            }
        } else {
            logger.warning("Set an API key to call unpause.");
        }
    }
}
