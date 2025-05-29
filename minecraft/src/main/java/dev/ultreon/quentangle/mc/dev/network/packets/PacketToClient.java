package dev.ultreon.quentangle.mc.dev.network.packets;

import dev.ultreon.quentangle.mc.network.Networker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;

public record PacketToClient(
    String message
) implements dev.ultreon.quentangle.mc.network.packet.PacketToClient<PacketToClient> {

    public static PacketToClient read(RegistryFriendlyByteBuf buf) {
        Thread.dumpStack();
        return new PacketToClient(buf.readUtf());
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeUtf(message);
    }

    @Override
    public void handle(Networker networker) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal(message), true);
        System.out.println(message);
    }
}
