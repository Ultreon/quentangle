package dev.ultreon.quentangle.terasology.mixin.entity;

import dev.ultreon.quantum.entity.player.Player;
import dev.ultreon.quantum.menu.Inventory;
import dev.ultreon.quentangle.api.player.IInventory;
import dev.ultreon.quentangle.api.player.IPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class MixinPlayer implements IPlayer {
    @Shadow
    public Inventory inventory;

    @Override
    public IInventory quent$getInventory() {
        return (IInventory) inventory;
    }
}
