package dev.ultreon.quentangle.terasology.gui;

import dev.ultreon.quentangle.registry.INamespaceID;
import org.terasology.splash.glfw.graphics.Texture;

import java.io.IOException;
import java.util.HashMap;

public class TextureManager {
    private static final HashMap<INamespaceID, Texture> textures = new HashMap<>();

    public static Texture get(INamespaceID id) {
        if (textures.containsKey(id)) {
            return textures.get(id);
        }

        return textures.put(id, load(id));
    }

    private static Texture load(INamespaceID id) {
        try {
            return Texture.loadTexture(TextureManager.class.getClassLoader().getResource("assets/" + id.quent$domain() + "/textures/" + id.quent$path()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
