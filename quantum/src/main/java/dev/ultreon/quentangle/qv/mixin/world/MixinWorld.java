package dev.ultreon.quentangle.qv.mixin.world;

import dev.ultreon.quantum.block.Blocks;
import dev.ultreon.quantum.block.state.BlockState;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.world.IClientWorld;
import dev.ultreon.quentangle.api.world.IServerWorld;
import dev.ultreon.quentangle.api.world.IWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class MixinWorld implements IWorld {
    @Shadow public abstract @NotNull BlockState get(int x, int y, int z);

    @Shadow public abstract boolean set(int x, int y, int z, BlockState block);

    @Override
    public @Nullable IServerWorld quent$asServerWorld() {
        return this instanceof IServerWorld ? (IServerWorld) this : null;
    }

    @Override
    public @Nullable IClientWorld quent$asClientWorld() {
        return this instanceof IClientWorld ? (IClientWorld) this : null;
    }

    @Override
    public IBlockState quent$getBlock(int x, int y, int z) {
        return (IBlockState) get(x, y, z);
    }

    @Override
    public void quent$setBlock(int x, int y, int z, IBlockState block) {
        set(x, y, z, (BlockState) block);
    }

    @Override
    public void quent$removeBlock(int x, int y, int z) {
        set(x, y, z, Blocks.AIR.getDefaultState());
    }
}
