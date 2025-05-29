package dev.ultreon.quentangle.terasology.util;

import dev.ultreon.quantum.util.NamespaceID;
import dev.ultreon.quentangle.registry.INamespaceID;
import org.jetbrains.annotations.NotNull;

public class NamespaceID implements INamespaceID {
    private final String domain;
    private final String path;

    private NamespaceID(String domain, String path) {
        this.domain = domain;
        this.path = path;
    }

    public static NamespaceID of(String domain, String path) {
        return new NamespaceID(domain, path);
    }

    @Override
    public String quent$domain() {
        return domain;
    }

    @Override
    public String quent$path() {
        return path;
    }
}
