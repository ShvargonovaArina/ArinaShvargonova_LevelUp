package ru.levelp.at.homework7.listener;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static TestContext instance;

    private final Map<String, Object> context;

    private TestContext() {
        this.context = new HashMap<>();
    }

    public void addObject(final String key, final Object o) {
        context.put(key, o);
    }

    public Object getObject(final String key) {
        return context.get(key);
    }

    public static void clear() {
        instance = null;
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
