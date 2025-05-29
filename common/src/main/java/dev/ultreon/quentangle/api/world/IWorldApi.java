package dev.ultreon.quentangle.api.world;

import dev.ultreon.quentangle.api.block.IBlockStateApi;
import org.jetbrains.annotations.Nullable;

public interface IWorldApi {
    @Nullable IServerWorldApi quent$asServerWorld();

    @Nullable IClientWorldApi quent$asClientWorld();

    IBlockStateApi quent$getBlock(int x, int y, int z);

    void quent$setBlock(int x, int y, int z, IBlockStateApi block);

    void quent$removeBlock(int x, int y, int z);
}
