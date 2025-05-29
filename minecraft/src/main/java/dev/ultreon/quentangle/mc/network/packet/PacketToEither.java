package dev.ultreon.quentangle.mc.network.packet;

import dev.ultreon.quentangle.mc.network.endpoint.ClientEndpoint;
import dev.ultreon.quentangle.mc.network.endpoint.ServerEndpoint;

/// Represents a packet sent from the server to either the client or the server
///
/// @param <T> The type of the packet
public interface PacketToEither<T extends Packet<T>> extends Packet<T>, ServerEndpoint, ClientEndpoint {

}
