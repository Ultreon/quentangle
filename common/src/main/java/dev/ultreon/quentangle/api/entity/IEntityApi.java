package dev.ultreon.quentangle.api.entity;

public interface IEntityApi {
    double quent$getX();
    double quent$getY();
    double quent$getZ();

    void quent$setX(double x);
    void quent$setY(double y);
    void quent$setZ(double z);

    void quent$setPos(double x, double y, double z);
}
