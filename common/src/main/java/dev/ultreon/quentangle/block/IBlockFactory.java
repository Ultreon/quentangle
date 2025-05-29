package dev.ultreon.quentangle.block;

import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.util.InteractResult;

public interface IBlockFactory {

    InteractResult use(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit);

    boolean onBreak(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player);
    boolean onPlace(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player);
}
