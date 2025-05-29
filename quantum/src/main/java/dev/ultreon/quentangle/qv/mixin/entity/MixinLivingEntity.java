package dev.ultreon.quentangle.qv.mixin.entity;

import dev.ultreon.quantum.entity.LivingEntity;
import dev.ultreon.quentangle.api.entity.ILivingEntityApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements ILivingEntityApi {
    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract float getHealth();

    public void quent$setHealth(float health) {
        setHealth(health);
    }

    public float quent$getHealth() {
        return getHealth();
    }
}
