package dev.ultreon.quentangle.util;

public interface IPos {
    double getX();
    double getY();
    double getZ();

    void setX(double x);
    void setY(double y);
    void setZ(double z);

    int getBlockX();
    int getBlockY();
    int getBlockZ();

    default double distanceSq(IPos pos) {
        return (this.getX() - pos.getX()) * (this.getX() - pos.getX()) + (this.getY() - pos.getY()) * (this.getY() - pos.getY()) + (this.getZ() - pos.getZ()) * (this.getZ() - pos.getZ());
    }

    default double distance(IPos pos) {
        return Math.sqrt(distanceSq(pos));
    }

    default double lengthSq() {
        return this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ();
    }

    default double length() {
        return Math.sqrt(lengthSq());
    }

    default Vec toVec() {
        return new Vec(this.getX(), this.getY(), this.getZ());
    }

    default IPos add(IPos pos) {
        this.setX(this.getX() + pos.getX());
        this.setY(this.getY() + pos.getY());
        this.setZ(this.getZ() + pos.getZ());
        return this;
    }

    default IPos sub(IPos pos) {
        this.setX(this.getX() - pos.getX());
        this.setY(this.getY() - pos.getY());
        this.setZ(this.getZ() - pos.getZ());
        return this;
    }

    default IPos mul(double scalar) {
        this.setX(this.getX() * scalar);
        this.setY(this.getY() * scalar);
        this.setZ(this.getZ() * scalar);
        return this;
    }

    default IPos mul(IPos pos) {
        this.setX(this.getX() * pos.getX());
        this.setY(this.getY() * pos.getY());
        this.setZ(this.getZ() * pos.getZ());
        return this;
    }

    default IPos div(double scalar) {
        this.setX(this.getX() / scalar);
        this.setY(this.getY() / scalar);
        this.setZ(this.getZ() / scalar);
        return this;
    }

    default IPos div(IPos pos) {
        this.setX(this.getX() / pos.getX());
        this.setY(this.getY() / pos.getY());
        this.setZ(this.getZ() / pos.getZ());
        return this;
    }

    default IPos negate() {
        this.setX(-this.getX());
        this.setY(-this.getY());
        this.setZ(-this.getZ());
        return this;
    }

    default IPos normalize() {
        double length = this.length();
        this.setX(this.getX() / length);
        this.setY(this.getY() / length);
        this.setZ(this.getZ() / length);
        return this;
    }

    IPos copy();

    default boolean isZero() {
        return this.getX() == 0 && this.getY() == 0 && this.getZ() == 0;
    }

    default boolean isEqual(IPos pos) {
        return this.getX() == pos.getX() && this.getY() == pos.getY() && this.getZ() == pos.getZ();
    }

    default boolean isNotEqual(IPos pos) {
        return !this.isEqual(pos);
    }

    default boolean isPositive() {
        return this.getX() > 0 && this.getY() > 0 && this.getZ() > 0;
    }

    default boolean isNegative() {
        return this.getX() < 0 && this.getY() < 0 && this.getZ() < 0;
    }

    default boolean isBlockVec() {
        return this instanceof BlockVec;
    }

    default boolean isChunkVec() {
        return this instanceof ChunkVec;
    }

    default boolean isVec() {
        return this instanceof Vec;
    }

    int getChunkX();
    int getChunkY();
    int getChunkZ();

    default IPos set(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        return this;
    }

    default IPos set(IPos pos) {
        this.setX(pos.getX());
        this.setY(pos.getY());
        this.setZ(pos.getZ());
        return this;
    }
}
