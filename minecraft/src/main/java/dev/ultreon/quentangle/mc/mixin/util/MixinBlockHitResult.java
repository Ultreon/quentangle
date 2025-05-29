package dev.ultreon.quentangle.mc.mixin.util;

import dev.ultreon.quentangle.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockHitResult.class)
public abstract class MixinBlockHitResult extends HitResult implements IBlockHitApi {
    @Shadow public abstract BlockPos getBlockPos();

    @Shadow @Final private net.minecraft.core.Direction direction;

    protected MixinBlockHitResult(Vec3 location) {
        super(location);
    }

    @Override
    public BlockVec quent$blockVec(BlockVec pos) {
        BlockPos blockPos = getBlockPos();
        return new BlockVec(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    @Override
    public Direction quent$direction() {
        return switch (direction) {
            case DOWN -> Direction.DOWN;
            case UP -> Direction.UP;
            case EAST -> Direction.EAST;
            case WEST -> Direction.WEST;
            case NORTH -> Direction.NORTH;
            case SOUTH -> Direction.SOUTH;
        };
    }
}
