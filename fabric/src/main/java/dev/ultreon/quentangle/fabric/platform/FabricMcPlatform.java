package dev.ultreon.quentangle.fabric.platform;

import com.mojang.brigadier.CommandDispatcher;
import dev.ultreon.quantum.api.neocommand.CommandRegistrant;
import dev.ultreon.quentangle.ModLoader;
import dev.ultreon.quentangle.fabric.network.FabricNetworker;
import dev.ultreon.quentangle.fabric.registrar.FabricRegistrarManager;
import dev.ultreon.quentangle.fabric.tabs.FabricCreativeTabBuilder;
import dev.ultreon.quentangle.mc.components.ComponentManager;
import dev.ultreon.quentangle.mc.components.SimpleComponentManager;
import dev.ultreon.quentangle.mc.network.NetworkRegistry;
import dev.ultreon.quentangle.mc.network.Networker;
import dev.ultreon.quentangle.mc.platform.ICommandRegistrant;
import dev.ultreon.quentangle.mc.platform.IMcPlatform;
import dev.ultreon.quentangle.mc.platform.services.IClientPlatform;
import dev.ultreon.quentangle.mc.registrar.RegistrarManager;
import dev.ultreon.quentangle.mc.tabs.IItemGroupBuilder;
import dev.ultreon.quentangle.platform.IMod;
import dev.ultreon.quentangle.util.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public class FabricMcPlatform implements IMcPlatform {
    private final List<ICommandRegistrant> commandRegistrants = new ArrayList<>();
    private final Map<String, RegistrarManager> registrars = new HashMap<>();
    private IClientPlatform client;
    private final Map<String, ComponentManager> componentManagers = new HashMap<>();

    public FabricMcPlatform() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            client = new FabricClientPlatform();
        }

        CommandRegistrationCallback.EVENT.register(this::register);
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public ModLoader getModLoader() {
        return ModLoader.Fabric;
    }

    @Override
    public Env getEnv() {
        return switch (FabricLoader.getInstance().getEnvironmentType()) {
            case CLIENT -> Env.CLIENT;
            case SERVER -> Env.SERVER;
        };
    }

    @Override
    public RegistrarManager getRegistrarManager(String modId) {
        if (this.registrars.containsKey(modId)) {
            return this.registrars.get(modId);
        }

        this.registrars.put(modId, new FabricRegistrarManager(modId));
        return this.registrars.get(modId);
    }

    @Override
    public ComponentManager getComponentManager(String modId) {
        return componentManagers.computeIfAbsent(modId, SimpleComponentManager::new);
    }

    @Override
    public IItemGroupBuilder creativeTabBuilder() {
        return new FabricCreativeTabBuilder();
    }

    @Override
    public Networker createNetworker(String modId, Consumer<NetworkRegistry> registrant) {
        return new FabricNetworker(modId, registrant);
    }

    @Override
    public void registerCommand(ICommandRegistrant registrant) {
        this.commandRegistrants.add(registrant);
    }

    @Override
    public IClientPlatform client() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            return client;
        }
        throw new IllegalStateException("This method should only be called on the client");
    }

    @Override
    public Optional<IMod> getMod(String modId) {
        return FabricLoader.getInstance().getModContainer(modId).map(FabricMod::new);
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    private void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment) {
        for (ICommandRegistrant registrant : commandRegistrants) {
            registrant.register(dispatcher, registryAccess, environment);
        }
    }
}
