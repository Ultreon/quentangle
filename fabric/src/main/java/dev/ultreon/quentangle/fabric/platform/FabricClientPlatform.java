package dev.ultreon.quentangle.fabric.platform;

import dev.ultreon.quentangle.fabric.platform.client.FabricEntityRendererRegistry;
import dev.ultreon.quentangle.mc.platform.services.IClientPlatform;
import dev.ultreon.quentangle.mc.platform.services.EntityRendererRegistry;

public class FabricClientPlatform implements IClientPlatform {
    private final EntityRendererRegistry entityRenderers = new FabricEntityRendererRegistry();

    @Override
    public EntityRendererRegistry entityRenderers() {
        return entityRenderers;
    }

}
