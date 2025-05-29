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

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld extends World implements IClientWorldApi {
    // TODO
}
