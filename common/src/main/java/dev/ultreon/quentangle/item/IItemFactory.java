package dev.ultreon.quentangle.item;

import dev.ultreon.quentangle.api.item.IItemStack;
import dev.ultreon.quentangle.util.InteractResult;

public interface IItemFactory {
    InteractResult use(IItemStack stack);

    ItemProperties getProperties();
}
