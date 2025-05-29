package dev.ultreon.quentangle.terasology.mixin.client;

import dev.ultreon.quantum.client.QuantumClient;
import dev.ultreon.quantum.client.gui.screens.TitleScreen;
import dev.ultreon.quentangle.CommonConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    @Inject(at = @At("HEAD"), method = "build")
    private void init(CallbackInfo info) {
        CommonConstants.LOG.info("This line is printed by the Quentangle mixin from Fabric!");
        CommonConstants.LOG.info("Quantum Voxel Version: {}", QuantumClient.getGameVersion());
    }
}
