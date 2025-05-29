package dev.ultreon.quentangle.qv.mixin.util;

import dev.ultreon.quantum.util.BlockHit;
import dev.ultreon.quentangle.util.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockHit.class)
public abstract class MixinBlockHit implements IBlockHitApi {
    @Shadow protected dev.ultreon.quantum.world.Direction direction;

    @Shadow protected dev.ultreon.quantum.world.vec.BlockVec vec;


    @Shadow public abstract dev.ultreon.quantum.util.Vec getVec();

    @Override
    public IPos quent$hitVec(IPos pos) {
        return pos.set(getVec().x, getVec().y, getVec().z);
    }

    @Override
    public BlockVec quent$blockVec(BlockVec pos) {
        return pos.set(vec.x, vec.y, vec.z);
    }

    @Override
    public Direction quent$direction() {
        return switch (direction) {
            case DOWN -> Direction.DOWN;
            case UP -> Direction.UP;
            case NORTH -> Direction.NORTH;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
            case EAST -> Direction.EAST;
        };
    }
}
