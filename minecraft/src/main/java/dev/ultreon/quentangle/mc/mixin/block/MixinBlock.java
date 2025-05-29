package dev.ultreon.quentangle.mc.mixin.block;

import dev.ultreon.quentangle.api.block.IBlockApi;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.mc.util.McPos;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class MixinBlock extends BlockBehaviour implements IBlockApi {
    @Shadow public abstract void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool);

    @Shadow public abstract BlockState defaultBlockState();

    public MixinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractResult quent$use(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit) {
        return switch (useWithoutItem((BlockState) state, (Level) world, McPos.blockOf(pos), (Player) player, (BlockHitResult) hit)) {
            case SUCCESS -> InteractResult.SUCCESS;
            case FAIL -> InteractResult.FAIL;
            case CONSUME -> InteractResult.CONSUME;
            case PASS -> InteractResult.PASS;
            default -> InteractResult.UNKNOWN;
        };
    }

    @Override
    public boolean quent$onBreak(IBlockStateApi state, IWorldApi world, IPos pos, IPlayerApi player) {
        BlockPos blockPos = McPos.blockOf(pos);
        Level level = (Level) world;
        playerDestroy(level, (Player) player, blockPos, (BlockState) state, (level).getBlockEntity(blockPos), ((Player) player).getUseItem());
        return true;
    }

    @Override
    public IBlockStateApi quent$onPlace(IBlockStateApi state, IBlockStateApi newState, IWorldApi world, IPos pos, IPlayerApi player, IHitApi hit) {
        BlockPos blockPos = McPos.blockOf(pos);
        onPlace((BlockState) newState, (Level) world, blockPos, (BlockState) state, false);
        return (IBlockStateApi) ((Level) world).getBlockState(blockPos);
    }

    @Override
    public IBlockStateApi quent$defaultState() {
        return (IBlockStateApi) defaultBlockState();
    }
}
