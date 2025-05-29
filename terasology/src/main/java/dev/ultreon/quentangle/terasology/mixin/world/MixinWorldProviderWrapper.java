package dev.ultreon.quentangle.terasology.mixin.world;

import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.world.IClientWorld;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.terasology.util.EmptyBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.internal.AbstractWorldProviderDecorator;
import org.terasology.engine.world.internal.WorldProviderCore;
import org.terasology.engine.world.internal.WorldProviderWrapper;

@Mixin(AbstractWorldProviderDecorator.class)
public abstract class MixinWorldProviderWrapper implements IWorld, WorldProviderCore {
    @Shadow public abstract Block getBlock(int x, int y, int z);

    @Override
    public IBlockState quent$getBlock(int x, int y, int z) {
        return new EmptyBlockState(getBlock(x, y, z));
    }
}
