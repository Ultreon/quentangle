package dev.ultreon.quentangle.terasology.mixin.world;

import dev.ultreon.quantum.world.ServerWorld;
import dev.ultreon.quentangle.api.world.IServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld implements IServerWorld {
    // TODO
}
