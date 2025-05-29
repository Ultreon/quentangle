package dev.ultreon.quentangle.mc.dev;

import dev.ultreon.quentangle.mc.dev.entity.TestEntity;
import dev.ultreon.quentangle.mc.platform.XinexPlatform;
import dev.ultreon.quentangle.mc.registrar.Registrar;
import dev.ultreon.quentangle.mc.registrar.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class DevEntities {
    private static final Registrar<EntityType<?>> REGISTRAR = XinexPlatform.getRegistrarManager("xinexlib").getRegistrar(Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<TestEntity>, EntityType<?>> TEST = REGISTRAR.register("test", () -> EntityType.Builder.of(TestEntity::new, MobCategory.AMBIENT)
            .sized(0.5f, 0.5f)
            .build("test"));

    public static void load() {
        REGISTRAR.load();
    }
}
