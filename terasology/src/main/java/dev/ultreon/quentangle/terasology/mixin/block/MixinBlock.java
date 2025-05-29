package dev.ultreon.quentangle.terasology.mixin.block;

import dev.ultreon.quentangle.api.block.IBlock;
import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.terasology.util.EmptyBlockState;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.terasology.engine.world.block.Block;

@Mixin(Block.class)
public abstract class MixinBlock implements IBlock {
    @Unique
    private final IBlockState blockState = new EmptyBlockState((Block) (Object) this);

    @Override
    public InteractResult quent$use(IBlockState state, IWorld world, IPos pos, IPlayer player, IBlockHit hit) {
        return InteractResult.PASS;
    }

    @Override
    public boolean quent$onBreak(IBlockState state, IWorld world, IPos pos, IPlayer player) {
        return false;
    }

    @Override
    public IBlockState quent$onPlace(IBlockState state, IBlockState newState, IWorld world, IPos pos, IPlayer player, IHit hit) {
        return null;
    }

    @Override
    public IBlockState quent$defaultState() {
        return blockState;
    }
}
