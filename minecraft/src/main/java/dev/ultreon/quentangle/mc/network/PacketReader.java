package dev.ultreon.quentangle.mc.network;

import dev.ultreon.quentangle.mc.network.packet.Packet;
import net.minecraft.network.RegistryFriendlyByteBuf;

@FunctionalInterface
public interface PacketReader<T extends Packet<?>> {
    T read(RegistryFriendlyByteBuf buffer);
}
