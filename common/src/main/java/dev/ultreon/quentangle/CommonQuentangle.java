package dev.ultreon.quentangle;

import dev.ultreon.quentangle.platform.Services;
import dev.ultreon.quentangle.registry.INamespaceID;

public class CommonQuentangle {
    public static void init() {
        CommonConstants.LOG.info("Hello from Common init on {} from {}! we are currently in a {} environment!", Services.PLATFORM.getModLoader(), Services.PLATFORM.getGame(), Services.PLATFORM.getEnvironmentName());
    }

    public static INamespaceID id(String path) {
        return INamespaceID.quent$of(CommonConstants.MOD_ID, path);
    }
}
