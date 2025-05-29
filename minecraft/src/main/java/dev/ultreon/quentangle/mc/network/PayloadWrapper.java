package dev.ultreon.quentangle.mc.network;

import dev.ultreon.quentangle.mc.network.packet.Packet;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public class PayloadWrapper<T extends Packet<?>> implements CustomPacketPayload {
    private final Type<?> type;
    public T packet;

    PayloadWrapper(Type<?> type) {
        this.type = type;
    }

    public PayloadWrapper(Type<?> type, T packet) {
        this.type = type;
        this.packet = packet;
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return type;
    }

    public void write(RegistryFriendlyByteBuf object) {
        packet.write(object);
    }
}
