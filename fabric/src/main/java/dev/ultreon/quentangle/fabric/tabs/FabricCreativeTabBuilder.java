package dev.ultreon.quentangle.fabric.tabs;

import dev.ultreon.quentangle.api.item.IItemGroup;
import dev.ultreon.quentangle.api.item.IItemGroupContent;
import dev.ultreon.quentangle.api.item.IItemStack;
import dev.ultreon.quentangle.mc.tabs.IItemGroupBuilder;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.text.ITextObject;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class FabricCreativeTabBuilder implements IItemGroupBuilder {
    private final CreativeModeTab.Builder builder;

    public FabricCreativeTabBuilder() {
        this.builder = FabricItemGroup.builder();
    }

    @Override
    public IItemGroupBuilder title(ITextObject name) {
        builder.title((Component) name);
        return this;
    }

    @Override
    public IItemGroupBuilder icon(Supplier<IItemStack> icon) {
        builder.icon(() -> (ItemStack) (Object) icon.get());
        return this;
    }

    @Override
    public IItemGroupBuilder background(INamespaceID background) {
        builder.backgroundTexture((ResourceLocation) (Object) background);
        return this;
    }

    @Override
    public IItemGroupBuilder noScrollBar() {
        builder.noScrollBar();
        return this;
    }

    @Override
    public IItemGroupBuilder displayItems(IGeneratorFunc generator) {
        builder.displayItems((itemDisplayParameters, output) -> {
            generator.genItems((IItemGroupContent) output);
        });
        return this;
    }

    @Override
    public IItemGroup build() {
        return (IItemGroup) builder.build();
    }
}
