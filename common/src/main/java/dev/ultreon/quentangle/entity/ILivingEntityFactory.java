package dev.ultreon.quentangle.entity;

import dev.ultreon.quentangle.api.entity.IEntityApi;

public interface ILivingEntityFactory extends IEntityFactory {
    boolean onHurt(IEntityApi player, float damage);
    boolean onDeath();
}
