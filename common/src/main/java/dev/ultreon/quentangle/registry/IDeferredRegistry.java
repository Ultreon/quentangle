package dev.ultreon.quentangle.registry;

import java.util.function.Supplier;

public interface IDeferredRegistry<T> {
    <C extends T> IRegistrySupplier<C> register(String name, Supplier<C> supplier);

    IRegistrySupplier<? extends T> get(String name);

    void registerAll();
}
