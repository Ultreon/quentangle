package dev.ultreon.quentangle.entity;

import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.util.InteractResult;

public interface IEntityFactory {
    InteractResult interact(IPlayer player);
    boolean onSpawn();
}
