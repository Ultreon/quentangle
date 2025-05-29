package dev.ultreon.quentangle.terasology.util;

import dev.ultreon.quentangle.api.block.IBlock;
import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import org.terasology.engine.world.block.Block;

public class EmptyBlockState implements IBlockState {
    private final Block block;

    public EmptyBlockState(Block block) {
        this.block = block;
    }

    @Override
    public InteractResult quent$use(IWorld world, IPos pos, IPlayer player, IBlockHit hit) {
        return InteractResult.PASS;
    }

    @Override
    public IBlock quent$getBlock() {
        return (IBlock) (Object) block;
    }
}
