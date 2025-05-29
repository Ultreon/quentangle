package dev.ultreon.quentangle.mc.client.event.screen;

import dev.ultreon.quentangle.mc.client.event.ClientScreenEvent;
import net.minecraft.client.gui.screens.Screen;


public class ClientScreenPreInitEvent implements ClientScreenEvent {
    private final Screen screen;

    public ClientScreenPreInitEvent(Screen screen) {
        this.screen = screen;
    }

    @Override
    public Screen getScreen() {
        return screen;
    }
}
