package dev.ultreon.quentangle.forge;

import dev.ultreon.quentangle.mc.McCommonClass;
import dev.ultreon.quentangle.CommonConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(CommonConstants.MOD_ID)
public class Quentangle {

    public Quentangle() {
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        CommonConstants.LOG.info("Hello Forge world!");
        McCommonClass.init();

    }
}
