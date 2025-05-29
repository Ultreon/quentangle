package dev.ultreon.quentangle.mc.mixin.util;

import dev.ultreon.quentangle.registry.INamespaceID;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceLocation.class)
public class MixinResourceLocation implements INamespaceID {

    @Shadow @Final private String namespace;

    @Shadow @Final private String path;

    @Override
    public String quent$domain() {
        return namespace;
    }

    @Override
    public String quent$path() {
        return path;
    }
}
