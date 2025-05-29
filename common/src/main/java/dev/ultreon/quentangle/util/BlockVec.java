package dev.ultreon.quentangle.util;

import dev.ultreon.quentangle.platform.Services;

public class BlockVec implements IPos {
    public int x;
    public int y;
    public int z;

    public BlockVec(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockVec(IPos pos) {
        this.x = pos.getBlockX();
        this.y = pos.getBlockY();
        this.z = pos.getBlockZ();
    }

    @Override
    public int getBlockX() {
        return x;
    }

    @Override
    public int getBlockY() {
        return y;
    }

    @Override
    public int getBlockZ() {
        return z;
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
        return new BlockVec(x, y, z);
    }

    @Override
    public int getChunkX() {
        return x / Services.PLATFORM.getChunkWidth();
    }

    @Override
    public int getChunkY() {
        return y / Services.PLATFORM.getChunkHeight();
    }

    @Override
    public int getChunkZ() {
        return z / Services.PLATFORM.getChunkWidth();
    }

    public BlockVec set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public BlockVec set(BlockVec pos) {
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        return this;
    }
}
