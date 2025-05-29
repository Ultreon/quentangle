package dev.ultreon.quentangle.terasology.mixin.client.gui;

import dev.ultreon.quentangle.api.client.gui.IGuiRenderer;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.terasology.gui.TextureManager;
import dev.ultreon.quentangle.terasology.util.NamespaceID;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.terasology.engine.world.block.BlockUri;
import org.terasology.splash.glfw.graphics.Color;
import org.terasology.splash.glfw.graphics.Renderer;
import org.terasology.splash.glfw.graphics.Texture;
import org.terasology.splash.glfw.text.Font;

import java.net.URL;

@Mixin(Renderer.class)
public abstract class MixinRenderer implements IGuiRenderer {
    @Shadow public abstract void drawTexture(Texture texture, float x, float y);

    @Shadow public abstract void drawTextureRegion(Texture texture, float x, float y, float regX, float regY, float regWidth, float regHeight);

    @Shadow public abstract void drawTextureRegion(float x1, float y1, float x2, float y2, float s1, float t1, float s2, float t2);

    @Shadow public abstract void drawText(CharSequence text, float x, float y);

    @Shadow public abstract void drawText(CharSequence text, float x, float y, Color c);

    @Shadow private Font font;
    @Unique
    private final Color tmpColor = new Color();

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height) {
        quent$drawTex(texture, x, y, width, height, 0, 0, 256, 256, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v) {
        quent$drawTex(texture, x, y, width, height, u, v, width, height, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight) {
        quent$drawTex(texture, x, y, width, height, u, v, uWidth, vHeight, 256, 256);
    }

    @Override
    public void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        Texture tex = TextureManager.get(texture);
        tex.bind();
        drawTextureRegion(x, y + height, width, -height, (float) textureWidth / u, (float) textureHeight / v, (float) textureWidth / (u + uWidth), (float) textureHeight / (v + vHeight));
    }

    @Override
    public void quent$drawString(String text, int scale, int x, int y, int color, boolean shadow) {
        tmpColor.setRed(color >> 16 & 0xff);
        tmpColor.setGreen(color >> 8 & 0xff);
        tmpColor.setBlue(color & 0xff);
        tmpColor.setAlpha(color >> 24 & 0xff);
        drawText(text, x, y, tmpColor);
    }

    @Override
    public void quent$drawBox(int x, int y, int width, int height, int color) {
        // TODO: Add drawBox(...) support for Terasology
    }

    @Override
    public void quent$fillBox(int x, int y, int width, int height, int color) {
        // TODO: Add fillBox(...) support for Terasology
    }

    @Override
    public void quent$translate(float x, float y) {
        // TODO: Add translate(...) support for Terasology
    }

    @Override
    public void quent$scale(float x, float y) {
        // TODO: Add scale(...) support for Terasology
    }

    @Override
    public void quent$pushMatrix() {
        // TODO: Add pushMatrix(...) support for Terasology
    }

    @Override
    public void quent$popMatrix() {
        // TODO: Add popMatrix(...) support for Terasology
    }

    @Override
    public int quent$getTextHeight(String text, int scale) {
        return font.getHeight(text);
    }

    @Override
    public int quent$getTextWidth(String text, int scale) {
        return font.getWidth(text);
    }

    @Override
    public void quent$drawBackground(IGuiRenderer renderer, int mouseY, int y, float delta) {
        // TODO: Add drawBackground(...) support for Terasology
    }
}
