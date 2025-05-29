package dev.ultreon.quentangle.registry;

import dev.ultreon.quentangle.platform.Services;

import java.util.function.Function;

public interface INamespaceID {
    static INamespaceID quent$of(String domain, String path) {
        return Services.PLATFORM.createId(domain, path);
    }

    static INamespaceID quent$parse(String id) {
        String[] split = id.split(":");
        if (split.length == 1) {
            String path = split[0];
            if (!path.matches("[a-z0-9/._]+")) throw new IllegalArgumentException("Invalid path: " + id);
            return Services.PLATFORM.createId(Services.PLATFORM.getDefaultNamespace(), path);
        }
        if (split.length != 2) throw new IllegalArgumentException("Invalid id: " + id);
        if (!split[0].matches("[a-z0-9_]+")) throw new IllegalArgumentException("Invalid domain: " + id);
        if (!split[1].matches("[a-z0-9/._]+")) throw new IllegalArgumentException("Invalid path: " + id);
        return Services.PLATFORM.createId(split[0], split[1]);
    }

    default INamespaceID quent$withPath(String path) {
        return INamespaceID.quent$of(quent$domain(), path);
    }

    default INamespaceID quent$withDomain(String domain) {
        return INamespaceID.quent$of(domain, quent$path());
    }

    default INamespaceID quent$map(Function<String, String> mapper) {
        return INamespaceID.quent$of(quent$domain(), mapper.apply(quent$path()));
    }

    default INamespaceID quent$withDefaultDomain() {
        return INamespaceID.quent$of(Services.PLATFORM.getDefaultNamespace(), quent$path());
    }

    String quent$domain();

    String quent$path();
}
