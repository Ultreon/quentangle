package dev.ultreon.quentangle.terasology.mixin.text;

import dev.ultreon.quantum.text.MutableText;
import dev.ultreon.quantum.text.TextObject;
import dev.ultreon.quentangle.text.IMutableTextObject;
import dev.ultreon.quentangle.text.ITextObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MutableText.class)
public abstract class MixinMutableText implements IMutableTextObject {
    @Shadow public abstract MutableText append(String text);

    @Shadow public abstract MutableText append(TextObject append);

    @Override
    public void quent$append(String text) {
        append(text);
    }

    @Override
    public void quent$append(ITextObject text) {
        append((TextObject) text);
    }
}
