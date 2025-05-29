package dev.ultreon.quentangle.platform.services;

import dev.ultreon.quentangle.Game;
import dev.ultreon.quentangle.ModLoader;
import dev.ultreon.quentangle.api.block.IBlocksApi;
import dev.ultreon.quentangle.item.IItemsApi;
import dev.ultreon.quentangle.platform.IMod;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.text.IMutableTextObject;
import dev.ultreon.quentangle.text.ITextObject;
import dev.ultreon.quentangle.util.Env;

import java.nio.file.Path;
import java.util.Optional;

public interface IPlatform {
    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    String getDefaultNamespace();

    int getDefaultMaxStackSize();

    IBlocksApi getBlocksApi();

    IItemsApi getItemsApi();

    int getChunkWidth();

    int getChunkHeight();

    int getChunkBase();

    boolean is3DChunks();

    INamespaceID createId(String domain, String path);

    IMutableTextObject createLiteralText(String text);

    IMutableTextObject createTranslatableText(String key, Object... args);

    IMutableTextObject createTranslatableText(String key, Iterable<Object> args);

    ITextObject createEmptyText();

    Optional<IMod> getMod(String modId);

    ModLoader getModLoader();

    Game getGame();

    Path getConfigDir();

    /// Gets the current environment
    ///
    /// @return The current environment
    Env getEnv();
}
