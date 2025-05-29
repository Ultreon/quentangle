package dev.ultreon.quentangle.mc.mixin.block;

import com.mojang.serialization.MapCodec;
import dev.ultreon.quentangle.api.block.IBlock;
import dev.ultreon.quentangle.api.block.IBlockState;
import dev.ultreon.quentangle.api.player.IPlayer;
import dev.ultreon.quentangle.api.world.IWorld;
import dev.ultreon.quentangle.util.IBlockHit;
import dev.ultreon.quentangle.util.IPos;
import dev.ultreon.quentangle.util.InteractResult;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public abstract class MixinBlockState extends BlockBehaviour.BlockStateBase implements IBlockState {
    protected MixinBlockState(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> values, MapCodec<BlockState> propertiesCodec) {
        super(owner, values, propertiesCodec);
    }

    @Override
    public InteractResult quent$use(IWorld world, IPos pos, IPlayer player, IBlockHit hit) {
        return switch (useWithoutItem((Level) world, (Player) player, (BlockHitResult) hit)) {
            case SUCCESS -> InteractResult.SUCCESS;
            case FAIL -> InteractResult.FAIL;
            case CONSUME -> InteractResult.CONSUME;
            case PASS -> InteractResult.PASS;
            default -> InteractResult.UNKNOWN;
        };
    }

    @Override
    public IBlock quent$getBlock() {
        return (IBlock) getBlock();
    }
}
