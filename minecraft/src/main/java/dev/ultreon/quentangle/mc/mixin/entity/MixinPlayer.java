package dev.ultreon.quentangle.mc.mixin.entity;

import dev.ultreon.quentangle.api.player.IInventoryApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class MixinPlayer implements IPlayerApi {
    @Shadow @Final
    Inventory inventory;

    @Override
    public IInventoryApi quent$getInventory() {
        return (IInventoryApi) inventory;
    }
}
