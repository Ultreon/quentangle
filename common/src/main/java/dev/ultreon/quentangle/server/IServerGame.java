package dev.ultreon.quentangle.server;

import dev.ultreon.quentangle.api.game.IGame;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.api.world.IServerWorld;

public interface IServerGame extends IGame {
    IServerWorld getServerWorld(INamespaceID name);

    Iterable<IServerWorld> getServerWorlds();

    Iterable<IPlayer> getPlayers();
}
