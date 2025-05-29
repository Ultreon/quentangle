package dev.ultreon.quentangle.text;

public interface IMutableTextObject extends ITextObject {
    void quent$append(String text);

    void quent$append(ITextObject text);
}
