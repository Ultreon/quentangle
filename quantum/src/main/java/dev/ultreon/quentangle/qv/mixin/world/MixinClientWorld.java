package dev.ultreon.quentangle.qv.mixin.world;

import dev.ultreon.quantum.client.world.ClientWorld;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quentangle.api.world.IClientWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld extends World implements IClientWorld {
    // TODO
}
