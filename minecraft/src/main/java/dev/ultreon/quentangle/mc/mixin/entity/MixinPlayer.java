package dev.ultreon.quentangle.mc.mixin.entity;

import dev.ultreon.quentangle.api.player.IInventory;
import dev.ultreon.quentangle.api.player.IPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class MixinPlayer implements IPlayer {
    @Shadow @Final
    Inventory inventory;

    @Override
    public IInventory quent$getInventory() {
        return (IInventory) inventory;
    }
}
