package dev.ultreon.quentangle.mc.network.packet;

import dev.ultreon.quentangle.mc.network.endpoint.ClientEndpoint;

/// Represents a packet sent from the server to the client
///
/// @param <T> The type of the packet
public interface PacketToClient<T extends Packet<T>> extends Packet<T>, ClientEndpoint {

}
