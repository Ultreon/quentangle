package dev.ultreon.quentangle.mc.event.system;

public interface Cancelable {
    boolean isCanceled();

    void cancel();
}
