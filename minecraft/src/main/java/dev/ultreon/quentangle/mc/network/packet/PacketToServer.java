package dev.ultreon.quentangle.mc.network.packet;

import dev.ultreon.quentangle.mc.network.endpoint.ServerEndpoint;

/// Represents a packet sent from the client to the server
///
/// @param <T> The type of the packet
public interface PacketToServer<T extends Packet<T>> extends Packet<T>, ServerEndpoint {

}
