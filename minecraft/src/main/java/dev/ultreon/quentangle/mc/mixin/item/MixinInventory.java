package dev.ultreon.quentangle.mc.mixin.item;

import dev.ultreon.quentangle.api.player.IInventoryApi;
import dev.ultreon.quentangle.api.item.IItemStack;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Inventory.class)
public abstract class MixinInventory implements IInventoryApi {
    @Shadow public abstract int getContainerSize();

    @Shadow public abstract void clearContent();

    @Shadow public abstract ItemStack getItem(int slot);

    @Override
    public int quent$getSize() {
        return getContainerSize();
    }

    @Override
    public void quentangle$clear() {
        clearContent();
    }

    @Override
    public IItemStack quent$get(int slot) {
        return (IItemStack) (Object) getItem(slot);
    }
}
