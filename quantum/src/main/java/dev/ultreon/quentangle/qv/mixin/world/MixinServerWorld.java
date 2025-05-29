package dev.ultreon.quentangle.qv.mixin.world;

import dev.ultreon.quantum.block.Blocks;
import dev.ultreon.quantum.block.state.BlockState;
import dev.ultreon.quantum.world.ServerWorld;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.world.IClientWorldApi;
import dev.ultreon.quentangle.api.world.IServerWorldApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld implements IServerWorldApi {
    // TODO
}
