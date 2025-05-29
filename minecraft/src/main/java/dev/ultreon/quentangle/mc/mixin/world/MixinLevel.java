package dev.ultreon.quentangle.mc.mixin.world;

import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.world.IClientWorldApi;
import dev.ultreon.quentangle.api.world.IServerWorldApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Level.class)
public abstract class MixinLevel implements IWorldApi {

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Shadow public abstract boolean setBlock(BlockPos pos, BlockState newState, int flags);

    @Shadow public abstract boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity, int recursionLeft);

    @Override
    public @Nullable IServerWorldApi quent$asServerWorld() {
        return this instanceof IServerWorldApi ? (IServerWorldApi) this : null;
    }

    @Override
    public @Nullable IClientWorldApi quent$asClientWorld() {
        return this instanceof IClientWorldApi ? (IClientWorldApi) this : null;
    }

    @Override
    public IBlockStateApi quent$getBlock(int x, int y, int z) {
        return (IBlockStateApi) getBlockState(new BlockPos(x, y, z));
    }

    @Override
    public void quent$setBlock(int x, int y, int z, IBlockStateApi block) {
        setBlock(new BlockPos(x, y, z), (BlockState) block, 3);
    }

    @Override
    public void quent$removeBlock(int x, int y, int z) {
        destroyBlock(new BlockPos(x, y, z), true, null, 1);
    }
}
