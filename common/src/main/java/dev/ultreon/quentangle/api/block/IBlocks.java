package dev.ultreon.quentangle.api.block;

import dev.ultreon.quentangle.block.IBlockFactory;

public interface IBlocks {
    IBlock getAir();
    IBlock getStone();
    IBlock getDirt();
    IBlock getGrass();

    IBlock create(IBlockFactory factory);
}
