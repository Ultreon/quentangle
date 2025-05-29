package dev.ultreon.quentangle.qv.mixin.item;

import dev.ultreon.quantum.entity.Entity;
import dev.ultreon.quantum.menu.ContainerMenu;
import dev.ultreon.quantum.menu.Inventory;
import dev.ultreon.quantum.menu.MenuType;
import dev.ultreon.quantum.world.WorldAccess;
import dev.ultreon.quantum.world.container.Container;
import dev.ultreon.quantum.world.vec.BlockVec;
import dev.ultreon.quentangle.api.player.IInventoryApi;
import dev.ultreon.quentangle.api.item.IItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Inventory.class)
public abstract class MixinInventory extends ContainerMenu implements IInventoryApi {
    @Shadow public abstract void clear();

    protected MixinInventory(@NotNull MenuType<?> type, @NotNull WorldAccess world, @NotNull Entity entity, @Nullable BlockVec pos, int size, @Nullable Container<?> container) {
        super(type, world, entity, pos, size, container);
    }

    @Override
    public int quent$getSize() {
        return slots.length;
    }

    @Override
    public void quentangle$clear() {
clear();
    }

    @Override
    public IItemStack quent$get(int slot) {
        return (IItemStack) getItem(slot);
    }
}
