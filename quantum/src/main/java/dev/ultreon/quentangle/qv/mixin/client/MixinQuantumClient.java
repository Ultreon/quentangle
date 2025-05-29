package dev.ultreon.quentangle.qv.mixin.client;

import dev.ultreon.quantum.client.QuantumClient;
import dev.ultreon.quantum.client.world.ClientWorld;
import dev.ultreon.quentangle.CommonConstants;
import dev.ultreon.quentangle.api.game.IClientGameApi;
import dev.ultreon.quentangle.api.world.IClientWorldApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(QuantumClient.class)
public class MixinQuantumClient implements IClientGameApi {
    @Shadow
    public ClientWorld world;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        CommonConstants.LOG.info("This line is printed by the Quentangle quantum voxel mixin!");
        CommonConstants.LOG.info("Quantum Voxel Version: {}", QuantumClient.getGameVersion());
    }

    @Override
    public IClientWorldApi getClientWorld() {
        return (IClientWorldApi) (Object) world;
    }
}
