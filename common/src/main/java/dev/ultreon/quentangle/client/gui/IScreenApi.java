package dev.ultreon.quentangle.client.gui;

import dev.ultreon.quentangle.api.client.gui.IGuiRendererApi;

public interface IScreenApi {
    Screen quent$getQuentangleScreen();

    void quent$tick();

    void quent$render(IGuiRendererApi renderer, int mouseX, int mouseY, float delta);

    void quent$close();

    void quent$back();

    void quent$open();
}
