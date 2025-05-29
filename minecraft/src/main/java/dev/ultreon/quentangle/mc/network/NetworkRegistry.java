package dev.ultreon.quentangle.mc.network;

import dev.ultreon.quentangle.mc.network.endpoint.ClientEndpoint;
import dev.ultreon.quentangle.mc.network.endpoint.ServerEndpoint;
import dev.ultreon.quentangle.mc.network.packet.Packet;

public interface NetworkRegistry {
    <T extends Packet<T> & ClientEndpoint> void registerClient(String name, Class<T> clazz, PacketReader<T> reader);

    <T extends Packet<T> & ServerEndpoint> void registerServer(String name, Class<T> clazz, PacketReader<T> reader);

    <T extends Packet<T> & ServerEndpoint & ClientEndpoint> void registerBiDirectional(String name, Class<T> clazz, PacketReader<T> reader);
}
