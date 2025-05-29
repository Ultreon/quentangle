package dev.ultreon.quentangle.mc;

import com.mojang.brigadier.arguments.StringArgumentType;
import dev.ultreon.quentangle.mc.components.Component;
import dev.ultreon.quentangle.mc.components.ComponentHolder;
import dev.ultreon.quentangle.mc.components.ComponentManager;
import dev.ultreon.quentangle.mc.components.EntityComponentBuilder;
import dev.ultreon.quentangle.mc.dev.DevEntities;
import dev.ultreon.quentangle.mc.dev.network.packets.PacketToClient;
import dev.ultreon.quentangle.mc.event.block.AttemptBlockSetEvent;
import dev.ultreon.quentangle.mc.event.block.BlockSetEvent;
import dev.ultreon.quentangle.mc.event.entity.EntitySpawnEvent;
import dev.ultreon.quentangle.mc.event.entity.LivingHurtEvent;
import dev.ultreon.quentangle.mc.event.interact.UseBlockEvent;
import dev.ultreon.quentangle.mc.event.interact.UseEntityEvent;
import dev.ultreon.quentangle.mc.event.interact.UseItemEvent;
import dev.ultreon.quentangle.mc.event.player.PlayerAttackEntityEvent;
import dev.ultreon.quentangle.mc.event.player.PlayerPlaceBlockEvent;
import dev.ultreon.quentangle.mc.event.server.ServerChatEvent;
import dev.ultreon.quentangle.mc.event.system.EventSystem;
import dev.ultreon.quentangle.mc.item.XinexBlockItem;
import dev.ultreon.quentangle.mc.network.Networker;
import dev.ultreon.quentangle.mc.platform.XinexPlatform;
import dev.ultreon.quentangle.mc.registrar.Registrar;
import dev.ultreon.quentangle.mc.registrar.RegistrarManager;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

class XinexLibDev {
    static void initDev() {
        RegistrationInfo result = registrationTest();

        result.blockRegistrar().load();
        result.itemRegistrar().load();
        result.creativeModeTabRegistrar().load();

        DevEntities.load();

        eventTest(result);

        final ThreadLocal<Random> random = ThreadLocal.withInitial(Random::new);
        EventSystem.MAIN.on(AttemptBlockSetEvent.class, event -> {
            if (!event.getState().is(result.secondBlock())) return;
            Random r = random.get();
            r.setSeed(event.getBlockPosition().asLong());
            if (r.nextBoolean()) event.cancel();
        });

        moreEventTests(result);

        class TestyComponent implements Component<Entity> {
            private String name = "John Doe";

            public TestyComponent(String name) {
                this.name = name;
            }

            @Override
            public void save(CompoundTag tag, HolderLookup.Provider registryLookup) {
                tag.putString("name", name);
            }

            @Override
            public void load(CompoundTag tag, HolderLookup.Provider registryLookup) {
                name = tag.getString("name");
                if (name.isBlank()) name = "John Doe";
            }
        }

        ComponentManager componentManager = XinexPlatform.getComponentManager(Constants.MOD_ID);
        ComponentHolder<Entity, TestyComponent> testy = componentManager.registerComponent("testy", new EntityComponentBuilder<TestyComponent>(TestyComponent.class)
                .factory(entity -> new TestyComponent("John Doe"))
                .target(EntityType.PLAYER));

        EventSystem.MAIN.on(ServerChatEvent.class, event -> {
            if (event.getMessageContent().getString().equalsIgnoreCase("what is my name?")) {
                ServerPlayer player = event.getPlayer();
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Your player name is " + testy.get(player).name));
                event.cancel();
            }

            if (event.getMessageContent().getString().toLowerCase().startsWith("my name is ")) {
                ServerPlayer player = event.getPlayer();
                testy.get(player).name = event.getMessageContent().getString().substring("my name is ".length());
                event.cancel();
            }
        });

        Constants.LOG.info("The developer mode is enabled!");

        Networker networker = XinexPlatform.createNetworker(Constants.MOD_ID, iNetworkRegistry -> {
            iNetworkRegistry.registerClient("packet2client", PacketToClient.class, PacketToClient::read);
//            iNetworkRegistry.registerServer("packet2server", PacketToServer.class, PacketToServer::read);
        });

        XinexPlatform.registerCommand((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("xinex-dev:packets")
                    .then(Commands.literal("packet")
                            .then(Commands.argument("message", StringArgumentType.greedyString())
                                    .executes(context -> {
                                        try {
                                            String string = StringArgumentType.getString(context, "message");
                                            PacketToClient packet = new PacketToClient(string);
                                            networker.sendToClient(packet, context.getSource().getPlayerOrException());
                                        } catch (Exception e) {
                                            Constants.LOG.error("Failed to send packet", e);
                                        }
                                        return 1;
                                    })
                            )
                    )
            );
        });
    }

    private static void moreEventTests(RegistrationInfo result) {
        EventSystem.MAIN.on(BlockSetEvent.class, event -> {
            if (!event.getState().is(result.secondBlock())) return;

            for (Player player : event.getLevel().players()) {
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Block set at " + event.getBlockPosition() + "!"));
            }
        });

        EventSystem.MAIN.on(EntitySpawnEvent.class, event -> {
            if (event.getEntity() instanceof Creeper && event.getEntity().position().y > 64)
                event.cancel();
        });

        EventSystem.MAIN.on(EntitySpawnEvent.FreshSpawnEvent.class, event -> {
            if (event.getEntity() instanceof Zombie && event.getEntity().position().y > 64)
                event.cancel();
        });

        EventSystem.MAIN.on(EntitySpawnEvent.ExistingSpawnEvent.class, event -> {
            if (event.getEntity() instanceof Skeleton && event.getEntity().position().y > 64)
                event.cancel();
        });

        EventSystem.MAIN.on(UseBlockEvent.class, event -> {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("You used a block at " + event.getBlockPosition() + "!"));
            }
        });

        EventSystem.MAIN.on(UseItemEvent.class, event -> {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("You used an item at " + event.getPosition() + "!"));
            }
        });

        EventSystem.MAIN.on(UseEntityEvent.class, event -> {
            if (event.getTarget() instanceof Chicken) {
                if (!event.getLevel().isClientSide) event.getTarget().kill();
                event.cancel(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
            }
        });

        EventSystem.MAIN.on(PlayerAttackEntityEvent.class, event -> {
            if (event.getVictim() instanceof Chicken) {
                event.getVictim().level().explode(event.getVictim(), event.getVictim().position().x, event.getVictim().position().y, event.getVictim().position().z, 4, Level.ExplosionInteraction.MOB);
                event.cancel();
            }
        });

        EventSystem.MAIN.on(LivingHurtEvent.class, event -> {
            if (event.getVictim() instanceof Pig && event.getAttacker() instanceof Player attacker) {
                attacker.hurt(new DamageSource(event.getDamageSource().typeHolder(), event.getVictim(), event.getAttacker()), event.getAmount());
                event.cancel();
            }
        });
    }

    private static void eventTest(RegistrationInfo result) {
        EventSystem.MAIN.on(PlayerPlaceBlockEvent.class, event -> {
            if (!event.getState().is(result.testBlock())) return;
            Player player = event.getPlayer();
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal("You placed a test block at " + event.getBlockPosition() + "!"));
            if (event.getBlockPosition().getY() < 64) event.cancel();
        });

        EventSystem.MAIN.on(ServerChatEvent.class, event -> {
            if (event.getMessageContent().getString().equalsIgnoreCase("hello")) {
                ServerPlayer player = event.getPlayer();
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Hello, " + player.getName().getString()));
            }

            if (event.getMessageContent().getString().equalsIgnoreCase("bye")) {
                ServerPlayer player = event.getPlayer();
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Bye, " + player.getName().getString()));
            }

            if (event.getMessageContent().getString().equalsIgnoreCase("block this")) {
                event.cancel();
            }
        });
    }

    private static @NotNull RegistrationInfo registrationTest() {
        RegistrarManager registrarManager = XinexPlatform.getRegistrarManager(Constants.MOD_ID);
        Registrar<Block> blockRegistrar = registrarManager.getRegistrar(Registries.BLOCK);
        var testBlock = blockRegistrar.register("test_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
        var secondBlock = blockRegistrar.register("second_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
        Registrar<Item> itemRegistrar = registrarManager.getRegistrar(Registries.ITEM);
        var testItem = itemRegistrar.register("test_item", () -> new Item(new Item.Properties().stacksTo(1)));
        var testBlockItem = itemRegistrar.register("test_block_item", () -> new XinexBlockItem(testBlock, new Item.Properties().stacksTo(1)));
        var secondBlockItem = itemRegistrar.register("second_block_item", () -> new XinexBlockItem(secondBlock, new Item.Properties().stacksTo(1)));
        Registrar<CreativeModeTab> creativeModeTabRegistrar = registrarManager.getRegistrar(Registries.CREATIVE_MODE_TAB);
        var testTab = creativeModeTabRegistrar.register("test_tab", () -> XinexPlatform.creativeTabBuilder().icon(() -> new ItemStack(testBlockItem)).displayItems((itemDisplayParameters, output) -> {
            output.accept(new ItemStack(testItem));
            output.accept(new ItemStack(testBlockItem));
            output.accept(new ItemStack(secondBlockItem));
        }).title(net.minecraft.network.chat.Component.literal("Test Tab")).build());

        Constants.LOG.info("The ID for test_block is {}", testBlock.getId());
        Constants.LOG.info("The ID for test_item is {}", testItem.getId());
        Constants.LOG.info("The ID for test_block_item is {}", testBlockItem.getId());
        Constants.LOG.info("The ID for second_block is {}", secondBlock.getId());
        Constants.LOG.info("The ID for second_block_item is {}", secondBlockItem.getId());
        Constants.LOG.info("The ID for test_tab is {}", testTab.getId());
        RegistrationInfo result = new RegistrationInfo(blockRegistrar, testBlock, secondBlock, itemRegistrar, creativeModeTabRegistrar);
        return result;
    }

    private record RegistrationInfo(Registrar<Block> blockRegistrar, dev.ultreon.quentangle.mc.registrar.RegistrySupplier<Block, Block> testBlock, dev.ultreon.quentangle.mc.registrar.RegistrySupplier<Block, Block> secondBlock, Registrar<Item> itemRegistrar, Registrar<CreativeModeTab> creativeModeTabRegistrar) {
    }
}
