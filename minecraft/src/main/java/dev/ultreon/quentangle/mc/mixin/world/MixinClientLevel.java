package dev.ultreon.quentangle.mc.mixin.world;

import dev.ultreon.quentangle.api.world.IClientWorld;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientLevel.class)
public abstract class MixinClientLevel implements IClientWorld {

}
