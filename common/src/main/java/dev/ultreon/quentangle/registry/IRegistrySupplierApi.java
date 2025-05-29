package dev.ultreon.quentangle.registry;

public interface IRegistrySupplierApi<T> {
    T get();

    IRegistryApi<?> getRegistry();
}
