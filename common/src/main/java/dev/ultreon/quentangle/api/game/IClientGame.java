package dev.ultreon.quentangle.api.game;

import dev.ultreon.quentangle.api.world.IClientWorld;

public interface IClientGame extends IGame {
    IClientWorld getClientWorld();
}
