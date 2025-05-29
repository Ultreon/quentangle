package dev.ultreon.quentangle.util;

import dev.ultreon.quentangle.platform.Services;

public class Vec implements IPos {
    public double x;
    public double y;
    public double z;

    public Vec(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec(IPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
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
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public int getBlockX() {
        return (int) x;
    }

    @Override
    public int getBlockY() {
        return (int) y;
    }

    @Override
    public int getBlockZ() {
        return (int) z;
    }

    @Override
    public IPos copy() {
        return new Vec(x, y, z);
    }

    @Override
    public int getChunkX() {
        return getBlockX() / Services.PLATFORM.getChunkWidth();
    }

    @Override
    public int getChunkY() {
        return getBlockY() / Services.PLATFORM.getChunkHeight();
    }

    @Override
    public int getChunkZ() {
        return getBlockZ() / Services.PLATFORM.getChunkWidth();
    }
}
