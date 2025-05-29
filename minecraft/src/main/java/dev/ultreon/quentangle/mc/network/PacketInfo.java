package dev.ultreon.quentangle.mc.network;

import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public record PacketInfo(
        @Nullable ServerPlayer sender
) {
    public PacketInfo() {
        this(null);
    }
}
