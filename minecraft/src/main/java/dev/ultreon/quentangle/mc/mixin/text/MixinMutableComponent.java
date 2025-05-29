package dev.ultreon.quentangle.mc.mixin.text;

import dev.ultreon.quentangle.text.IMutableTextObject;
import dev.ultreon.quentangle.text.ITextObject;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MutableComponent.class)
public abstract class MixinMutableComponent implements IMutableTextObject, Component {
    @Shadow public abstract MutableComponent append(String string);

    @Shadow public abstract MutableComponent append(Component sibling);

    @Override
    public void quent$append(String text) {
        append(text);
    }

    @Override
    public void quent$append(ITextObject text) {
        if (text instanceof Component)
            append((Component) text);
        else append(text.quent$getText());
    }

    @Override
    public String quent$getText() {
        return getString();
    }
}
