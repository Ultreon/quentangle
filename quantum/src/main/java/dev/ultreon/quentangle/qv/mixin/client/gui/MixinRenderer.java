package dev.ultreon.quentangle.qv.mixin.client.gui;

import dev.ultreon.quantum.client.QuantumClient;
import dev.ultreon.quantum.client.gui.Renderer;
import dev.ultreon.quantum.client.gui.Screen;
import dev.ultreon.quantum.util.Color;
import dev.ultreon.quantum.util.NamespaceID;
import dev.ultreon.quantum.util.RgbColor;
import dev.ultreon.quentangle.api.client.gui.IGuiRendererApi;
import dev.ultreon.quentangle.qv.mixin.accessor.ScreenAccessor;
import dev.ultreon.quentangle.registry.INamespaceID;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Renderer.class)
public abstract class MixinRenderer implements IGuiRendererApi {
    @Unique
    private com.badlogic.gdx.graphics.Color tmpColor;

    @Shadow public abstract Renderer blit(NamespaceID id, float x, float y, float width, float height, float u, float v, float uWidth, float vHeight, float texWidth, float texHeight);

    @Shadow public abstract Renderer textLeft(@NotNull String text, float scale, int x, int y, Color color, boolean shadow);

    @Shadow public abstract Renderer box(int x, int y, int width, int height, com.badlogic.gdx.graphics.Color rgb);

    @Shadow public abstract Renderer fill(int x, int y, int width, int height, com.badlogic.gdx.graphics.Color rgb);

    @Shadow public abstract Renderer translate(float x, float y);

    @Shadow public abstract Renderer scale(double sx, double sy);

    @Shadow public abstract Renderer pushMatrix();

    @Shadow public abstract Renderer popMatrix();

    @Shadow public abstract int textWidth(@NotNull String text);

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height) {
        blit((NamespaceID) (Object) texture, x, y, width, height, 0, 0, width, height, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v) {
        blit((NamespaceID) (Object) texture, x, y, width, height, u, v, width, height, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight) {
        blit((NamespaceID) (Object) texture, x, y, width, height, u, v, uWidth, vHeight, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        blit((NamespaceID) (Object) texture, x, y, width, height, u, v, uWidth, vHeight, textureWidth, textureHeight);
    }

    @Override
    public void quent$drawString(String text, int scale, int x, int y, int color, boolean shadow) {
        textLeft(text, scale, x, y, RgbColor.rgb(color), shadow);
    }

    @Override
    public void quent$drawBox(int x, int y, int width, int height, int color) {
        box(x, y, width, height, tmpColor.set(color << 8 | color >> 24 & 0xff));
    }

    @Override
    public void quent$fillBox(int x, int y, int width, int height, int color) {
        fill(x, y, width, height, tmpColor.set(color << 8 | color >> 24 & 0xff));
    }

    @Override
    public void quent$translate(float x, float y) {
        translate(x, y);
    }

    @Override
    public void quent$scale(float x, float y) {
        scale(x, y);
    }

    @Override
    public void quent$pushMatrix() {
        pushMatrix();
    }

    @Override
    public void quent$popMatrix() {
        popMatrix();
    }

    @Override
    public int quent$getTextHeight(String text, int scale) {
        return 11;
    }

    @Override
    public int quent$getTextWidth(String text, int scale) {
        return textWidth(text) * scale;
    }

    @Override
    public void quent$drawBackground(IGuiRendererApi renderer, int mouseY, int y, float delta) {
        Screen screen = QuantumClient.get().screen;
        if (screen == null) throw new IllegalStateException("Cannot draw background without a screen!");

        ((ScreenAccessor)screen).quent$renderBackground((Renderer) renderer);
    }
}
