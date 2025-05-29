package dev.ultreon.quentangle.mc.network.endpoint;

import dev.ultreon.quentangle.mc.network.Networker;
import net.minecraft.server.level.ServerPlayer;

public interface ServerEndpoint {
    void handle(Networker connection, ServerPlayer player);
}
