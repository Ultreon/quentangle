package dev.ultreon.quentangle.mc.mixin.world;

import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.world.IClientWorld;
import dev.ultreon.quentangle.api.world.IServerWorld;
import dev.ultreon.quentangle.api.world.IWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Level.class)
public abstract class MixinLevel implements IWorld {

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Shadow public abstract boolean setBlock(BlockPos pos, BlockState newState, int flags);

    @Shadow public abstract boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity, int recursionLeft);

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
        return (IBlockState) getBlockState(new BlockPos(x, y, z));
    }

    @Override
    public void quent$setBlock(int x, int y, int z, IBlockState block) {
        setBlock(new BlockPos(x, y, z), (BlockState) block, 3);
    }

    @Override
    public void quent$removeBlock(int x, int y, int z) {
        destroyBlock(new BlockPos(x, y, z), true, null, 1);
    }
}
