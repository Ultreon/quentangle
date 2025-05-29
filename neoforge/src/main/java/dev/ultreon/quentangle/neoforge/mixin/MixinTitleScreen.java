package dev.ultreon.quentangle.neoforge.mixin;

import dev.ultreon.quentangle.CommonConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        CommonConstants.LOG.info("This line is printed by the Quentangle mixin from NeoForge!");
        CommonConstants.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}
