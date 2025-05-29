package dev.ultreon.quentangle.registry;

import org.jetbrains.annotations.Nullable;

public interface IRegistry<T> {
    T quent$get(INamespaceID name);

    @Nullable Iterable<T> quent$getByTag(INamespaceID tag);

    Iterable<T> quent$getAll();
}
