package dev.ultreon.quentangle.mc.nbt;

import dev.ultreon.quentangle.mc.Constants;
import net.minecraft.world.entity.Entity;

public class DataKeys {
    public static final DataKey<Entity> COMPONENTS = DataKey.of(Constants.MOD_ID, "components");
}
