package dev.ultreon.quentangle.mc.dev.network.handler;

import dev.ultreon.quentangle.mc.dev.network.packets.PacketToClient;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class DevClientPlayHandlerImpl extends DevClientPlayHandler {
    @Override
    public void handle(PacketToClient packet) {
        String message = packet.message();
        Minecraft.getInstance().player.displayClientMessage(Component.literal(message), true);
    }
}
