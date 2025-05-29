package dev.ultreon.quentangle.entity;

import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.util.InteractResult;

public interface IEntityFactory {
    InteractResult interact(IPlayerApi player);
    boolean onSpawn();
}
