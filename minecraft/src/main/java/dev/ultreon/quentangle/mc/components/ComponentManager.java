package dev.ultreon.quentangle.mc.components;

import dev.ultreon.quentangle.mc.platform.services.IMcPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

/// A manager for entity components (and soon maybe others)
///
/// @author <a href="https://github.com/XyperCode">XyperCode</a>
/// @since 0.1.0
/// @see IMcPlatform#getComponentManager(String)
public interface ComponentManager {
    <T extends Component<Entity>> ComponentHolder<Entity, T> registerComponent(String name, EntityComponentBuilder<T> factory);
    <T extends Component<Entity>> @Nullable T getComponent(ResourceLocation name, Entity entity, Class<T> clazz);
}
