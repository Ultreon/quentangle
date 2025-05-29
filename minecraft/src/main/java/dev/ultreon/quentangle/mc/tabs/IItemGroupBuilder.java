package dev.ultreon.quentangle.mc.tabs;

import dev.ultreon.quentangle.api.item.IItemGroup;
import dev.ultreon.quentangle.api.item.IItemGroupContent;
import dev.ultreon.quentangle.api.item.IItemStack;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.text.ITextObject;

import java.util.function.Supplier;

public interface IItemGroupBuilder {

    IItemGroupBuilder title(ITextObject name);

    IItemGroupBuilder icon(Supplier<IItemStack> icon);

    IItemGroupBuilder background(INamespaceID background);

    IItemGroupBuilder noScrollBar();

    IItemGroupBuilder displayItems(IGeneratorFunc generator);

    IItemGroup build();

    interface IGeneratorFunc {
        void genItems(IItemGroupContent content);
    }
}
