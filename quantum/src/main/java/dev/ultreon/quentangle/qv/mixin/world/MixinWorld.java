package dev.ultreon.quentangle.qv.mixin.world;

import dev.ultreon.quantum.block.Blocks;
import dev.ultreon.quantum.block.state.BlockState;
import dev.ultreon.quantum.client.QuantumClient;
import dev.ultreon.quantum.client.world.ClientWorld;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quantum.world.vec.BlockVec;
import dev.ultreon.quentangle.CommonConstants;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.world.IClientWorldApi;
import dev.ultreon.quentangle.api.world.IServerWorldApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class MixinWorld implements IWorldApi {
    @Shadow public abstract @NotNull BlockState get(int x, int y, int z);

    @Shadow public abstract boolean set(int x, int y, int z, BlockState block);

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
        return (IBlockStateApi) get(x, y, z);
    }

    @Override
    public void quent$setBlock(int x, int y, int z, IBlockStateApi block) {
        set(x, y, z, (BlockState) block);
    }

    @Override
    public void quent$removeBlock(int x, int y, int z) {
        set(x, y, z, Blocks.AIR.getDefaultState());
    }
}
