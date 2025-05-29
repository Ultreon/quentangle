package dev.ultreon.quentangle.mc.platform;

import dev.ultreon.quentangle.Game;
import dev.ultreon.quentangle.mc.Constants;
import dev.ultreon.quentangle.util.Env;
import dev.ultreon.quentangle.ModLoader;
import dev.ultreon.quentangle.mc.components.ComponentManager;
import dev.ultreon.quentangle.mc.network.NetworkRegistry;
import dev.ultreon.quentangle.mc.network.Networker;
import dev.ultreon.quentangle.mc.platform.services.IClientPlatform;
import dev.ultreon.quentangle.mc.registrar.RegistrarManager;
import dev.ultreon.quentangle.mc.tabs.IItemGroupBuilder;
import dev.ultreon.quentangle.platform.IMod;
import dev.ultreon.quentangle.platform.Services;

import java.nio.file.Path;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Consumer;

/*
 Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
 environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
 is swapped out for the platform specific implementation at runtime.
*/
public class McPlatform {
    private McPlatform() {

    }

    static final IMcPlatform PLATFORM = (IMcPlatform) Services.PLATFORM;

    /*
     This code is used to load a service for the current environment. Your implementation of the service must be defined
     manually by including a text file in META-INF/services named with the fully qualified class name of the service.
     Inside the file you should write the fully qualified class name of the implementation to load for the platform. For
     example our file on Forge points to ForgePlatformHelper while Fabric points to FabricPlatformHelper.
    */
    private static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static RegistrarManager getRegistrarManager(String modId) {
        return PLATFORM.getRegistrarManager(modId);
    }

    public static boolean isModLoaded(String modId) {
        return PLATFORM.isModLoaded(modId);
    }

    public static Optional<IMod> getMod(String modId) {
        return PLATFORM.getMod(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return PLATFORM.isDevelopmentEnvironment();
    }

    public static String getEnvironmentName() {
        return PLATFORM.getEnvironmentName();
    }

    public static ModLoader getModLoader() {
        return PLATFORM.getModLoader();
    }

    public static Game getGame() {
        return PLATFORM.getGame();
    }

    public static ComponentManager getComponentManager(String modId) {
        return PLATFORM.getComponentManager(modId);
    }

    public static IItemGroupBuilder creativeTabBuilder() {
        return PLATFORM.creativeTabBuilder();
    }

    public static Env getEnv() {
        return PLATFORM.getEnv();
    }

    public static Networker createNetworker(String modId, Consumer<NetworkRegistry> registrant) {
        return PLATFORM.createNetworker(modId, registrant);
    }

    public static void registerCommand(ICommandRegistrant registrant) {
        PLATFORM.registerCommand(registrant);
    }

    public static IClientPlatform client() {
        return PLATFORM.client();
    }

    public static Path getConfigDir() {
        return PLATFORM.getConfigDir();
    }
}
