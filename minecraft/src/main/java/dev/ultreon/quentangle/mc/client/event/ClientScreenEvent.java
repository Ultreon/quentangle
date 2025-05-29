package dev.ultreon.quentangle.mc.client.event;

import dev.ultreon.quentangle.client.gui.IScreenApi;
import net.minecraft.client.Minecraft;

public interface ClientScreenEvent extends ClientEvent {
    IScreenApi getScreen();

    @Override
    default Minecraft getClient() {
        return Minecraft.getInstance();
    }
}
