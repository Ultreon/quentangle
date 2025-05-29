package dev.ultreon.quentangle.api.resources;

import java.io.InputStream;

public interface IResource {
    InputStream quent$inputStream();

    byte[] quent$bytes();
}
