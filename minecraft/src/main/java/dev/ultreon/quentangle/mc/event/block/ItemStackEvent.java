package dev.ultreon.quentangle.mc.event.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ItemStackEvent extends ItemEvent {
    ItemStack getStack();

    default Item getItem() {
        return getStack().getItem();
    }
}
