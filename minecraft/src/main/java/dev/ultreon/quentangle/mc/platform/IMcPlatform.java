package dev.ultreon.quentangle.mc.platform;

import com.mojang.brigadier.Command;
import dev.ultreon.quantum.api.neocommand.CommandRegistrant;
import dev.ultreon.quentangle.Game;
import dev.ultreon.quentangle.api.block.IBlocksApi;
import dev.ultreon.quentangle.item.IItemsApi;
import dev.ultreon.quentangle.mc.components.ComponentManager;
import dev.ultreon.quentangle.mc.network.NetworkRegistry;
import dev.ultreon.quentangle.mc.network.Networker;
import dev.ultreon.quentangle.mc.platform.services.IClientPlatform;
import dev.ultreon.quentangle.mc.registrar.RegistrarManager;
import dev.ultreon.quentangle.mc.tabs.IItemGroupBuilder;
import dev.ultreon.quentangle.platform.services.IPlatform;
import dev.ultreon.quentangle.registry.INamespaceID;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public interface IMcPlatform extends IPlatform {
    @Override
    default String getDefaultNamespace() {
        return "minecraft";
    }

    @Override
    default int getDefaultMaxStackSize() {
        return 64;
    }

    @Override
    default int getChunkHeight() {
        return 384;
    }

    @Override
    default int getChunkBase() {
        return -64;
    }

    @Override
    default boolean is3DChunks() {
        return false;
    }

    @Override
    default int getChunkWidth() {
        return 16;
    }

    @Override
    default IBlocksApi getBlocksApi() {
        throw new UnsupportedOperationException("Blocks API is not implemented for Minecraft");
    }

    @Override
    default IItemsApi getItemsApi() {
        throw new UnsupportedOperationException("Items API is not implemented for Minecraft");
    }

    @Override
    default INamespaceID createId(String domain, String path) {
        return (INamespaceID) (Object) ResourceLocation.fromNamespaceAndPath(domain, path);
    }

    @Override
    default Game getGame() {
        return Game.Minecraft;
    }

    /// Gets the registrar manager for the given mod id
    ///
    /// @param modId The mod id
    /// @return The registrar manager
    RegistrarManager getRegistrarManager(String modId);

    /// Gets the component manager for the given mod id
    ///
    /// @param modId The mod id
    /// @return The component manager
    ComponentManager getComponentManager(String modId);

    /// Creates a new creative mode tab builder
    ///
    /// @see CreativeModeTab
    /// @return The creative mode tab builder
    IItemGroupBuilder creativeTabBuilder();

    /// Creates a new networker. This generally only needs to be created once per mod. Or not at all.
    ///
    /// @param modId The mod id
    /// @param registrant The network registry
    /// @return A new networker instance.
    Networker createNetworker(String modId, Consumer<NetworkRegistry> registrant);

    /// Registers a new command.
    ///
    /// @param registrant The command
    /// @see Command
    void registerCommand(ICommandRegistrant registrant);

    /// Gets the current client-side platform
    IClientPlatform client();
}
