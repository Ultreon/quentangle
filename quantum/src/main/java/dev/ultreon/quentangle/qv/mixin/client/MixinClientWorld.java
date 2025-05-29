package dev.ultreon.quentangle.qv.mixin.client;

import dev.ultreon.quantum.client.QuantumClient;
import dev.ultreon.quantum.client.world.ClientWorld;
import dev.ultreon.quentangle.CommonConstants;
import dev.ultreon.quentangle.api.world.IClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld implements IClientWorld {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        CommonConstants.LOG.info("This line is printed by the Quentangle quantum voxel mixin!");
        CommonConstants.LOG.info("Quantum Voxel Version: {}", QuantumClient.getGameVersion());
    }
}
