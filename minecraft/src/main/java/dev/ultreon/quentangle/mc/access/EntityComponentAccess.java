package dev.ultreon.quentangle.mc.access;

import dev.ultreon.quentangle.mc.components.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.Map;

public interface EntityComponentAccess {
    <T extends Component<Entity>> T xinexlib$getComponent(ResourceLocation name, Class<T> clazz);

    <T extends Component<Entity>> void xinexlib$setComponent(ResourceLocation name, T component);

    Map<ResourceLocation, Component<Entity>> xinexlib$getAllComponents();
}
