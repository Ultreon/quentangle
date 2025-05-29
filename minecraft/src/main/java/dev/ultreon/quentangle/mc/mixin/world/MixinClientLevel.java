package dev.ultreon.quentangle.mc.mixin.world;

import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.world.IClientWorldApi;
import dev.ultreon.quentangle.api.world.IServerWorldApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientLevel.class)
public abstract class MixinClientLevel implements IClientWorldApi {

}
