package com.github.lc.oss.commons.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRuntimeService {
    private final Logger logger = LoggerFactory.getLogger(AbstractRuntimeService.class);

    protected boolean exec(Map<String, String> env, String... command) {
        try {
            ProcessBuilder pb = this.createBuilder(command);
            if (env != null) {
                env.forEach((k, v) -> pb.environment().put(k, v));
            }
            pb.start();
            return true;
        } catch (Exception ex) {
            this.getLogger().error("Error running command", ex);
            return false;
        }
    }

    protected ProcessBuilder createBuilder(String... command) {
        return new ProcessBuilder(command);
    }

    protected Logger getLogger() {
        return this.logger;
    }
}
