package org.mesdag.advjs.trigger.registry;

@FunctionalInterface
public interface TriggerMatchCallback {
    boolean match(Object test);
}
