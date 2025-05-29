package dev.ultreon.quentangle.fabric.registrar;

import dev.ultreon.quentangle.mc.registrar.Registrar;
import dev.ultreon.quentangle.mc.registrar.RegistrarManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FabricRegistrarManager implements RegistrarManager {
    private final String modId;

    public FabricRegistrarManager(String modId) {
        this.modId = modId;
    }

    @Override
    public <T> Registrar<T> getRegistrar(ResourceKey<Registry<T>> key) {
        return new FabricRegistrar<>(key, modId).retrieve();
    }

    @Override
    public <T> Registrar<T> createRegistrar(ResourceKey<Registry<T>> key, Class<T> clazz) {
        return new FabricRegistrar<>(key, modId).create();
    }
}
