package dev.ultreon.quentangle.mc.mixin.item;

import dev.ultreon.quentangle.api.item.IItemStack;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements IItemStack {
    @Shadow public abstract void setCount(int count);

    @Shadow public abstract int getCount();

    @Override
    public int quent$getAmount() {
        return getCount();
    }

    @Override
    public void quent$setAmount(int amount) {
        setCount(amount);
    }
}
