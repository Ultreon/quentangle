package dev.ultreon.quentangle.mc.client.event;

import dev.ultreon.quentangle.mc.event.player.PlayerEvent;
import net.minecraft.client.player.LocalPlayer;

public interface LocalPlayerEvent extends PlayerEvent {
    LocalPlayer getPlayer();
}
