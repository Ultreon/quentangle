package dev.ultreon.quentangle.text;

import dev.ultreon.quentangle.platform.Services;

public interface ITextObject {
    String quent$getText();

    static IMutableTextObject literal(String text) {
        return Services.PLATFORM.createLiteralText(text);
    }

    static IMutableTextObject translatable(String key, Object... args) {
        return Services.PLATFORM.createTranslatableText(key, args);
    }

    static IMutableTextObject translatable(String key, Iterable<Object> args) {
        return Services.PLATFORM.createTranslatableText(key, args);
    }

    static ITextObject empty() {
        return Services.PLATFORM.createEmptyText();
    }
}
