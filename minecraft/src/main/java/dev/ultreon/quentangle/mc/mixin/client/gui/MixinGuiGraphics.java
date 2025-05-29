package dev.ultreon.quentangle.mc.mixin.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.ultreon.quentangle.api.client.gui.IGuiRenderer;
import dev.ultreon.quentangle.registry.INamespaceID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiGraphics.class)
public abstract class MixinGuiGraphics implements IGuiRenderer {

    @Shadow public abstract void blit(ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight);

    @Shadow public abstract void blit(ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight);

    @Shadow public abstract void blit(ResourceLocation atlasLocation, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight);

    @Shadow public abstract void blit(ResourceLocation atlasLocation, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight);

    @Shadow abstract void blit(ResourceLocation atlasLocation, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight);

    @Shadow public abstract int drawString(Font font, @Nullable String text, int x, int y, int color);

    @Shadow public abstract void renderOutline(int x, int y, int width, int height, int color);

    @Shadow public abstract void fill(int minX, int minY, int maxX, int maxY, int color);

    @Shadow public abstract PoseStack pose();

    @Shadow public abstract int drawString(Font font, @Nullable String text, int x, int y, int color, boolean dropShadow);

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height) {
        blit((ResourceLocation) (Object) texture, x, y, 0, 0, width, height);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v) {
        blit((ResourceLocation) (Object) texture, x, y, u, v, width, height);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight) {
        blit((ResourceLocation) (Object) texture, x, y, width, height, u, v, uWidth, vHeight);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        blit((ResourceLocation) (Object) texture, x, y, u, v, width, height, uWidth, vHeight, textureWidth, textureHeight);
    }

    @Override
    public void quent$drawString(String text, int scale, int x, int y, int color, boolean shadow) {
        if (scale == 1) {
            drawString(Minecraft.getInstance().font, text == null ? "" : text, x, y, color, shadow);
            return;
        }
        pose().pushPose();
        pose().scale(scale, scale, 1);
        drawString(Minecraft.getInstance().font, text == null ? "" : text, x / scale, y / scale, color, shadow);
        pose().popPose();
    }

    @Override
    public void quent$drawBox(int x, int y, int width, int height, int color) {
        renderOutline(x, y, width, height, 0xffffffff);
    }

    @Override
    public void quent$fillBox(int x, int y, int width, int height, int color) {
        fill(x, y, width, height, 0xffffffff);
    }

    @Override
    public void quent$translate(float x, float y) {
        pose().translate(x, y, 0);
    }

    @Override
    public void quent$scale(float x, float y) {
        pose().scale(x, y, 1);
    }

    @Override
    public void quent$pushMatrix() {
        pose().pushPose();
    }

    @Override
    public void quent$popMatrix() {
        pose().popPose();
    }

    @Override
    public int quent$getTextHeight(String text, int scale) {
        int size = text.lines().toList().size();
        return Minecraft.getInstance().font.lineHeight * size * scale;
    }

    @Override
    public int quent$getTextWidth(String text, int scale) {
        int size = text.lines().toList().size();
        int width = Minecraft.getInstance().font.width(text) * scale;
        for (int i = 0; i < size; i++) {
            int line = Minecraft.getInstance().font.width(text.lines().toList().get(i));
            if (line > width) {
                width = line;
            }
        }
        Minecraft.getInstance().font.width(text);
        return size;
    }

    @Override
    public void quent$drawBackground(IGuiRenderer renderer, int mouseX, int mouseY, float delta) {
        Screen screen = Minecraft.getInstance().screen;
        if (screen == null) throw new IllegalStateException("Cannot draw background without a screen!");

        screen.renderBackground((GuiGraphics) renderer, mouseX, mouseY, delta);
    }
}
