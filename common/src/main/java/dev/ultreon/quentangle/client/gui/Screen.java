package dev.ultreon.quentangle.client.gui;

import dev.ultreon.quentangle.api.client.gui.IGuiRenderer;

public class Screen {
    public void render(IGuiRenderer renderer, int mouseX, int mouseY, float delta) {
        renderer.quent$drawBackground(renderer, mouseY, mouseY, delta);
    }

    public void tick() {

    }
}
