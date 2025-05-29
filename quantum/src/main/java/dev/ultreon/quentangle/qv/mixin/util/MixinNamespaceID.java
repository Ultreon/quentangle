package dev.ultreon.quentangle.qv.mixin.util;

import dev.ultreon.quantum.util.NamespaceID;
import dev.ultreon.quentangle.registry.INamespaceID;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NamespaceID.class)
public class MixinNamespaceID implements INamespaceID {

    @Shadow @Final private @NotNull String domain;

    @Shadow @Final private @NotNull String path;

    @Override
    public String quent$domain() {
        return domain;
    }

    @Override
    public String quent$path() {
        return path;
    }
}
