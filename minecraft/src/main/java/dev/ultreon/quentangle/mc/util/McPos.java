package dev.ultreon.quentangle.mc.util;

import dev.ultreon.quentangle.util.IPos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;

public class McPos {
    public static BlockPos blockOf(IPos pos) {
        return new BlockPos(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }
    public static ChunkPos chunkOf(IPos pos) {
        if (pos.getChunkY() != 0) throw new IllegalArgumentException("Chunk positions in Minecraft must have Y set to 0");
        return new ChunkPos(pos.getChunkX(), pos.getChunkZ());
    }

    public static Vec3 vecOf(IPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }
}
