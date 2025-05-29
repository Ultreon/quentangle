package dev.ultreon.quentangle.mc.mixin.block;

import com.mojang.serialization.MapCodec;
import dev.ultreon.quentangle.api.block.IBlockApi;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.mc.util.McPos;
import dev.ultreon.quentangle.util.IBlockHitApi;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public abstract class MixinBlockState extends BlockBehaviour.BlockStateBase implements IBlockStateApi {
    protected MixinBlockState(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> values, MapCodec<BlockState> propertiesCodec) {
        super(owner, values, propertiesCodec);
    }

    @Override
    public InteractResult quent$use(IWorldApi world, IPos pos, IPlayerApi player, IBlockHitApi hit) {
        return switch (useWithoutItem((Level) world, (Player) player, (BlockHitResult) hit)) {
            case SUCCESS -> InteractResult.SUCCESS;
            case FAIL -> InteractResult.FAIL;
            case CONSUME -> InteractResult.CONSUME;
            case PASS -> InteractResult.PASS;
            default -> InteractResult.UNKNOWN;
        };
    }

    @Override
    public IBlockApi quent$getBlock() {
        return (IBlockApi) getBlock();
    }
}
