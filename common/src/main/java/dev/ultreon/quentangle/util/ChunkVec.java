package dev.ultreon.quentangle.util;

import dev.ultreon.quentangle.platform.Services;

public class ChunkVec implements IPos {
    public int x;
    public int y;
    public int z;

    public ChunkVec(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ChunkVec(IPos pos) {
        this.x = pos.getBlockX();
        this.y = pos.getBlockY();
        this.z = pos.getBlockZ();
    }

    @Override
    public int getBlockX() {
        return x * Services.PLATFORM.getChunkWidth();
    }

    @Override
    public int getBlockY() {
        return y * Services.PLATFORM.getChunkHeight();
    }

    @Override
    public int getBlockZ() {
        return z * Services.PLATFORM.getChunkWidth();
    }

    @Override
    public void setX(double x) {
        this.x = (int) x;
    }

    @Override
    public void setY(double y) {
        this.y = (int) y;
    }

    @Override
    public void setZ(double z) {
        this.z = (int) z;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public IPos copy() {
        return new ChunkVec(x, y, z);
    }

    @Override
    public int getChunkX() {
        return x;
    }

    @Override
    public int getChunkY() {
        return y;
    }

    @Override
    public int getChunkZ() {
        return z;
    }
}
