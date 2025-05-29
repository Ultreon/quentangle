package dev.ultreon.quentangle.mc.client.event;

import net.minecraft.client.player.LocalPlayer;

public class LocalPlayerJoinEvent implements LocalPlayerEvent {
    private final LocalPlayer localPlayer;

    public LocalPlayerJoinEvent(LocalPlayer localPlayer) {
        this.localPlayer = localPlayer;
    }

    @Override
    public LocalPlayer getPlayer() {
        return localPlayer;
    }
}
