package dev.ultreon.quentangle.api.client.gui;

import dev.ultreon.quentangle.registry.INamespaceID;

public interface IGuiRendererApi {
    void quent$drawTex(INamespaceID texture, int x, int y, int width, int height);
    void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v);
    void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight);
    void quent$drawTex(INamespaceID texture, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight);

    default void quent$drawString(String text, int x, int y) {
        quent$drawString(text, x, y, true);
    }

    default void quent$drawString(String text, int x, int y, boolean shadow) {
        quent$drawString(text, x, y, 0xFFFFFFFF, shadow);
    }

    default void quent$drawString(String text, int x, int y, int color) {
        quent$drawString(text, x, y, color, true);
    }

    default void quent$drawString(String text, int scale, int x, int y, int color) {
        quent$drawString(text, scale, x, y, color, true);
    }

    default void quent$drawString(String text, int x, int y, int color, boolean shadow) {
        quent$drawString(text, 1, x, y, color, shadow);
    }

    void quent$drawString(String text, int scale, int x, int y, int color, boolean shadow);

    void quent$drawBox(int x, int y, int width, int height, int color);
    void quent$fillBox(int x, int y, int width, int height, int color);

    void quent$translate(float x, float y);
    void quent$scale(float x, float y);

    void quent$pushMatrix();
    void quent$popMatrix();

    int quent$getTextHeight(String text, int scale);
    int quent$getTextWidth(String text, int scale);

    void quent$drawBackground(IGuiRendererApi renderer, int mouseY, int y, float delta);
}
