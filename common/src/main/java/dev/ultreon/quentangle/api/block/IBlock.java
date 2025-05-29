package dev.ultreon.quentangle.api.block;

import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.util.InteractResult;
import org.jetbrains.annotations.Nullable;

public interface IBlock {
    InteractResult quent$use(IBlockState state, IWorld world, IPos pos, IPlayer player, IBlockHit hit);

    boolean quent$onBreak(IBlockState state, IWorld world, IPos pos, IPlayer player);
    @Nullable IBlockState quent$onPlace(IBlockState state, IBlockState newState, IWorld world, IPos pos, IPlayer player, IHit hit);

    IBlockState quent$defaultState();
}
