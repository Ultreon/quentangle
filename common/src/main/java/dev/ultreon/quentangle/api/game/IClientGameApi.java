package dev.ultreon.quentangle.api.game;

import dev.ultreon.quentangle.api.world.IClientWorldApi;

public interface IClientGameApi {
    IClientWorldApi getClientWorld();
}
