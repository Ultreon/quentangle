package dev.ultreon.quentangle.api.block;

import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.util.InteractResult;

public interface IBlockStateApi {
    InteractResult quent$use(IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit);

    IBlockApi quent$getBlock();
}
