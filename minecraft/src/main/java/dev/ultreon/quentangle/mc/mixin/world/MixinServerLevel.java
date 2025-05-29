package dev.ultreon.quentangle.mc.mixin.world;

import dev.ultreon.quentangle.api.world.IClientWorld;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerLevel.class)
public abstract class MixinServerLevel implements IClientWorld {

}
