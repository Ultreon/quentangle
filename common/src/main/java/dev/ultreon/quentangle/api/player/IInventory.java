package dev.ultreon.quentangle.api.player;

import dev.ultreon.quentangle.api.item.IItemStack;

public interface IInventory {
    int quent$getSize();
    void quentangle$clear();

    IItemStack quent$get(int slot);
}
