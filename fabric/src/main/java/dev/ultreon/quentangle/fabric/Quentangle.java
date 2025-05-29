package dev.ultreon.quentangle.fabric;

import dev.ultreon.quentangle.CommonConstants;
import dev.ultreon.quentangle.mc.McCommonClass;
import net.fabricmc.api.ModInitializer;

public class Quentangle implements ModInitializer {

    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        CommonConstants.LOG.info("Hello Fabric world!");
        McCommonClass.init();
    }
}
