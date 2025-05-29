package dev.ultreon.quentangle.api.player;

import dev.ultreon.quentangle.api.entity.ILivingEntityApi;

public interface IPlayerApi extends ILivingEntityApi {
    IInventoryApi quent$getInventory();
}
