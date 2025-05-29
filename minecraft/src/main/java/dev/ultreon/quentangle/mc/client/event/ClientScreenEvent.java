package dev.ultreon.quentangle.mc.client.event;

import dev.ultreon.quentangle.client.gui.IScreen;
import net.minecraft.client.Minecraft;

public interface ClientScreenEvent extends ClientEvent {
    IScreen getScreen();

    @Override
    default Minecraft getClient() {
        return Minecraft.getInstance();
    }
}
