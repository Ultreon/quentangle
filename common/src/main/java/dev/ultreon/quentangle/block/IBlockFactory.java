package dev.ultreon.quentangle.block;

import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.util.InteractResult;

public interface IBlockFactory {

    InteractResult use(IBlockState state, IWorld world, IPos pos, IPlayer player, IBlockHit hit);

    boolean onBreak(IBlockState state, IWorld world, IPos pos, IPlayer player);
    boolean onPlace(IBlockState state, IWorld world, IPos pos, IPlayer player);
}
