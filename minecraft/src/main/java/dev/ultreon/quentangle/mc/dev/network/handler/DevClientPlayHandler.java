package dev.ultreon.quentangle.mc.dev.network.handler;

import dev.ultreon.quentangle.mc.dev.network.packets.PacketToClient;

public abstract class DevClientPlayHandler {
    public abstract void handle(PacketToClient packet);
}
