package dev.ultreon.quentangle.server;

import dev.ultreon.quentangle.api.game.IGameApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.api.world.IServerWorldApi;

public interface IServerGameApi extends IGameApi {
    IServerWorldApi getServerWorld(INamespaceID name);

    Iterable<IServerWorldApi> getServerWorlds();

    Iterable<IPlayerApi> getPlayers();
}
