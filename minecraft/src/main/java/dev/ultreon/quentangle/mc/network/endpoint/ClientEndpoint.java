package dev.ultreon.quentangle.mc.network.endpoint;

import dev.ultreon.quentangle.mc.network.Networker;

public interface ClientEndpoint {
    void handle(Networker connection);
}
