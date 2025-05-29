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
import dev.ultreon.quentangle.api.block.IBlockApi;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class MixinBlock implements IBlockApi {

    @Shadow
    public abstract UseResult use(@NotNull WorldAccess world, @NotNull Player player, @NotNull Item item, @NotNull BlockVec pos);

    @Shadow public abstract void onDestroy(@NotNull World world, @NotNull BlockVec breaking, @NotNull BlockState blockState, @Nullable Player breaker);

    @Shadow public abstract BlockState onPlacedBy(BlockState blockMeta, BlockVec at, UseItemContext context);

    @Override
    public InteractResult quent$use(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit) {
        return switch (use((WorldAccess) world, (Player) player, ((Player)player).getSelectedItem().getItem(), ((BlockHit)hit).getBlockVec())) {
            case ALLOW -> InteractResult.SUCCESS;
            case DENY -> InteractResult.FAIL;
            case SKIP -> InteractResult.PASS;
        };
    }

    @Override
    public boolean quent$onBreak(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player) {
        onDestroy((World) world, new BlockVec(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), (BlockState) state, (Player) player);
        return true;
    }

    @Override
    public IBlockStateApi quent$onPlace(IBlockStateApi state, IBlockStateApi newState, IWorldApi world, IPos pos, IPlayerApi player, IHitApi hit) {
        BlockState blockState = onPlacedBy((BlockState) state, new BlockVec(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), new UseItemContext(
                (World) world,
                (Player) player,
                (Hit) hit,
                ((Player) player).getSelectedItem(),
                1f
        ));
        return (IBlockStateApi) blockState;
    }

    @Override
    public IBlockStateApi quent$defaultState() {
        return null;
    }
}
