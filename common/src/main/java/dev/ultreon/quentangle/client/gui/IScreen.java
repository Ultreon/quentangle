package dev.ultreon.quentangle.client.gui;

import dev.ultreon.quentangle.api.client.gui.IGuiRenderer;

public interface IScreen {
    Screen quent$getQuentangleScreen();

    void quent$tick();

    void quent$render(IGuiRenderer renderer, int mouseX, int mouseY, float delta);

    void quent$close();

    void quent$back();

    void quent$open();
}
