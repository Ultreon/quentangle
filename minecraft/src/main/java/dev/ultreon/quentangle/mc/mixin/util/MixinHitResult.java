package dev.ultreon.quentangle.mc.mixin.util;

import dev.ultreon.quentangle.util.*;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HitResult.class)
public abstract class MixinHitResult implements IHit {
    @Shadow @Final protected Vec3 location;

    @Override
    public IPos quent$hitVec(IPos pos) {
        return pos.set(location.x, location.y, location.z);
    }
}
