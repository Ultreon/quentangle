package dev.ultreon.quentangle.qv.mixin.text;

import dev.ultreon.quantum.text.TextObject;
import dev.ultreon.quentangle.text.ITextObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextObject.class)
public abstract class MixinTextObject implements ITextObject {
    @Shadow public abstract String getText();

    @Override
    public String quent$getText() {
        return getText();
    }
}
