package io.github.lc.oss.commons.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRuntimeService {
    private final Logger logger = LoggerFactory.getLogger(AbstractRuntimeService.class);

    protected boolean execAsync(String... command) {
        return this.execAsync(null, command);
    }

    protected Process exec(String... command) {
        return this.exec(null, command);
    }

    protected boolean execAsync(Map<String, String> env, String... command) {
        Process p = this.exec(env, command);
        return p != null;
    }

    protected Process exec(Map<String, String> env, String... command) {
        try {
            ProcessBuilder pb = this.createBuilder(command);
            if (env != null) {
                env.forEach((k, v) -> pb.environment().put(k, v));
            }
            return pb.start();
        } catch (Exception ex) {
            this.getLogger().error("Error running command", ex);
            return null;
        }
    }

    protected ProcessBuilder createBuilder(String... command) {
        return new ProcessBuilder(command);
    }

    protected Logger getLogger() {
        return this.logger;
    }
}
