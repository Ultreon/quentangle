package dev.ultreon.quentangle.mc.client;

import dev.ultreon.quentangle.util.Env;
import dev.ultreon.quentangle.mc.EnvExecutor;
import dev.ultreon.quentangle.mc.client.event.LocalPlayerQuitEvent;
import dev.ultreon.quentangle.mc.client.event.screen.ClientScreenOpenEvent;
import dev.ultreon.quentangle.mc.client.render.TestEntityRenderer;
import dev.ultreon.quentangle.mc.client.render.model.TestEntityModel;
import dev.ultreon.quentangle.mc.dev.DevEntities;
import dev.ultreon.quentangle.mc.event.system.EventSystem;
import dev.ultreon.quentangle.mc.platform.XinexPlatform;
import dev.ultreon.quentangle.mc.platform.services.IClientPlatform;
import net.minecraft.client.Minecraft;

public class XinexLibClient {
    public static void init() {
        if (XinexPlatform.isDevelopmentEnvironment() && "true".equals(System.getProperty("xinexlib.dev"))) {
            initDev();
        }
    }

    private static void initDev() {
        IClientPlatform client = XinexPlatform.client();
        client.entityRenderers().register(DevEntities.TEST::get, TestEntityRenderer::new);
        client.entityRenderers().registerModel(TestEntityModel.LAYER_LOCATION, TestEntityModel::createBodyLayer);

        EventSystem.MAIN.on(ClientScreenOpenEvent.class, event -> {
            System.out.printf("Screen opened: %s%n", event.getScreen().getClass().getName());
        });
    }

    public static void onDisconnect() {
        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> EventSystem.MAIN.publish(new LocalPlayerQuitEvent(Minecraft.getInstance().player)));
    }
}
