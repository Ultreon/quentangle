package dev.ultreon.quentangle.api.world;

import dev.ultreon.quentangle.api.block.IBlockState;
import org.jetbrains.annotations.Nullable;

public interface IWorld {
    @Nullable IServerWorld quent$asServerWorld();

    @Nullable IClientWorld quent$asClientWorld();

    IBlockState quent$getBlock(int x, int y, int z);

    void quent$setBlock(int x, int y, int z, IBlockState block);

    void quent$removeBlock(int x, int y, int z);
}
