package dev.ultreon.quentangle.entity;

import dev.ultreon.quentangle.api.entity.IEntity;

public interface ILivingEntityFactory extends IEntityFactory {
    boolean onHurt(IEntity player, float damage);
    boolean onDeath();
}
