package dev.ultreon.quentangle.qv;

import dev.ultreon.quentangle.CommonQuentangle;
import dev.ultreon.quentangle.CommonConstants;
import net.fabricmc.api.ModInitializer;

public class QuentangleQuantum implements ModInitializer {

    @Override
    public void onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        CommonConstants.LOG.info("Hello Quantum world!");
        CommonQuentangle.init();
    }
}
