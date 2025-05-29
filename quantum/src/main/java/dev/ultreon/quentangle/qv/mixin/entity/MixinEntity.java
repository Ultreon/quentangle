package dev.ultreon.quentangle.qv.mixin.entity;

import dev.ultreon.quantum.entity.Entity;
import dev.ultreon.quentangle.api.entity.IEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntity {
    @Shadow public abstract double getX();

    @Shadow public abstract double getY();

    @Shadow public abstract double getZ();

    @Shadow public abstract void setPosition(double x, double y, double z);

    @Override
    public double quent$getX() {
        return getX();
    }

    @Override
    public double quent$getY() {
        return getY();
    }

    @Override
    public double quent$getZ() {
        return getZ();
    }

    @Override
    public void quent$setX(double x) {
        setPosition(x, getY(), getZ());
    }

    @Override
    public void quent$setY(double y) {
        setPosition(getX(), y, getZ());
    }

    @Override
    public void quent$setZ(double z) {
        setPosition(getX(), getY(), z);
    }

    @Override
    public void quent$setPos(double x, double y, double z) {
        setPosition(x, y, z);
    }
}
