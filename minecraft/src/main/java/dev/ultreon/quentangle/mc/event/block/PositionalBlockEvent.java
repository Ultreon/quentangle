package dev.ultreon.quentangle.mc.event.block;

import dev.ultreon.quentangle.mc.event.level.LevelEvent;
import net.minecraft.core.BlockPos;

public interface PositionalBlockEvent extends LevelEvent, BlockStateEvent {
    BlockPos getBlockPosition();
}
