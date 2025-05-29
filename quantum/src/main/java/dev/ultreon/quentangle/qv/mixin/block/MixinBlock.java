package dev.ultreon.quentangle.qv.mixin.block;

import dev.ultreon.quantum.block.Block;
import dev.ultreon.quantum.block.state.BlockState;
import dev.ultreon.quantum.entity.player.Player;
import dev.ultreon.quantum.item.Item;
import dev.ultreon.quantum.item.UseItemContext;
import dev.ultreon.quantum.util.BlockHit;
import dev.ultreon.quantum.util.Hit;
import dev.ultreon.quantum.world.UseResult;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quantum.world.WorldAccess;
import dev.ultreon.quantum.world.vec.BlockVec;
import dev.ultreon.quentangle.api.block.IBlock;
import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class MixinBlock implements IBlock {

    @Shadow
    public abstract UseResult use(@NotNull WorldAccess world, @NotNull Player player, @NotNull Item item, @NotNull BlockVec pos);

    @Shadow public abstract void onDestroy(@NotNull World world, @NotNull BlockVec breaking, @NotNull BlockState blockState, @Nullable Player breaker);

    @Shadow public abstract BlockState onPlacedBy(BlockState blockMeta, BlockVec at, UseItemContext context);

    @Override
    public InteractResult quent$use(IBlockState state, IWorld world, IPos pos, IPlayer player, IBlockHit hit) {
        return switch (use((WorldAccess) world, (Player) player, ((Player)player).getSelectedItem().getItem(), ((BlockHit)hit).getBlockVec())) {
            case ALLOW -> InteractResult.SUCCESS;
            case DENY -> InteractResult.FAIL;
            case SKIP -> InteractResult.PASS;
        };
    }

    @Override
    public boolean quent$onBreak(IBlockState state, IWorld world, IPos pos, IPlayer player) {
        onDestroy((World) world, new BlockVec(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), (BlockState) state, (Player) player);
        return true;
    }

    @Override
    public IBlockState quent$onPlace(IBlockState state, IBlockState newState, IWorld world, IPos pos, IPlayer player, IHit hit) {
        BlockState blockState = onPlacedBy((BlockState) state, new BlockVec(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), new UseItemContext(
                (World) world,
                (Player) player,
                (Hit) hit,
                ((Player) player).getSelectedItem(),
                1f
        ));
        return (IBlockState) blockState;
    }

    @Override
    public IBlockState quent$defaultState() {
        return null;
    }
}
