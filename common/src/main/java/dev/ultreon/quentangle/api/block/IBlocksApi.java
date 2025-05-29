package dev.ultreon.quentangle.api.block;

import dev.ultreon.quentangle.block.IBlockFactory;

public interface IBlocksApi {
    IBlockApi getAir();
    IBlockApi getStone();
    IBlockApi getDirt();
    IBlockApi getGrass();

    IBlockApi create(IBlockFactory factory);
}
