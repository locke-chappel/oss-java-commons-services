package io.github.lc.oss.commons.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class AbstractRuntimeServiceTest extends AbstractTest {
    private static class TestService extends AbstractRuntimeService {

    }

    @Test
    public void test_execAsync_java() {
        AbstractRuntimeService service = new TestService();

        boolean result = service.execAsync("java", "-version");
        Assertions.assertTrue(result);
    }

    @Test
    public void test_execAsync_exception() {
        AbstractRuntimeService service = new TestService();

        Map<String, String> env = new HashMap<>();
        env.put("ARG", "value");
        boolean result = service.execAsync(env, "junk-boom!");
        Assertions.assertFalse(result);
    }

    @Test
    public void test_exec_java() {
        AbstractRuntimeService service = new TestService();

        Process result = service.exec("java", "-version");
        Assertions.assertNotNull(result);
    }

    @Test
    public void test_exec_exception() {
        AbstractRuntimeService service = new TestService();

        Map<String, String> env = new HashMap<>();
        env.put("ARG", "value");
        Process result = service.exec(env, "junk-boom!");
        Assertions.assertNull(result);
    }
}
