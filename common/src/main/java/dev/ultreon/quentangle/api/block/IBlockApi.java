package dev.ultreon.quentangle.api.block;

import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.util.InteractResult;

public interface IBlockApi {
    InteractResult quent$use(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit);

    boolean quent$onBreak(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player);
    IBlockStateApi quent$onPlace(IBlockStateApi state, IBlockStateApi newState, IWorldApi world, IPos pos, IPlayerApi player, IHitApi hit);

    IBlockStateApi quent$defaultState();
}
