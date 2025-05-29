package dev.ultreon.quentangle.registry;

public interface IRegistrySupplier<T> {
    T get();

    IRegistry<?> getRegistry();
}
