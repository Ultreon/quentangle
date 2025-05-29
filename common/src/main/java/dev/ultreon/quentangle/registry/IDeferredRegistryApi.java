package dev.ultreon.quentangle.registry;

import java.util.function.Supplier;

public interface IDeferredRegistryApi {
    IRegistrySupplierApi<?> register(String name, Supplier<?> supplier);

    IRegistrySupplierApi<?> get(String name);

    void registerAll();
}
