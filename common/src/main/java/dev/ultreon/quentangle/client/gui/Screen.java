package dev.ultreon.quentangle.client.gui;

import dev.ultreon.quentangle.api.client.gui.IGuiRendererApi;

public class Screen {
    public void render(IGuiRendererApi renderer, int mouseX, int mouseY, float delta) {
        renderer.quent$drawBackground(renderer, mouseY, mouseY, delta);
    }

    public void tick() {

    }
}
