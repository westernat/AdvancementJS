package org.mesdag.advjs.trigger.custom;

@FunctionalInterface
public interface TriggerMatchCallback {
    boolean match(Object test);
}
