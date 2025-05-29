package dev.ultreon.quentangle.qv.platform;

import dev.ultreon.quentangle.util.*;

public class EmptyBlockHitApi implements IBlockHitApi {
    private static final BlockVec BLOCK_VEC = new BlockVec(0, 0, 0);
    private static final Vec VEC = new Vec(0, 0, 0);

    @Override
    public BlockVec quent$blockVec(BlockVec pos) {
        return BLOCK_VEC;
    }

    @Override
    public Direction quent$direction() {
        return Direction.UP;
    }

    @Override
    public IPos quent$hitVec(IPos pos) {
        return VEC;
    }
}
