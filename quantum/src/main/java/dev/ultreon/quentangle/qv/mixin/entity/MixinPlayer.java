package dev.ultreon.quentangle.qv.mixin.entity;

import dev.ultreon.quantum.entity.player.Player;
import dev.ultreon.quantum.menu.Inventory;
import dev.ultreon.quentangle.api.player.IInventoryApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class MixinPlayer implements IPlayerApi {
    @Shadow
    public Inventory inventory;

    @Override
    public IInventoryApi quent$getInventory() {
        return (IInventoryApi) inventory;
    }
}
