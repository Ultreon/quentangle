package dev.ultreon.quentangle.fabric.network;

import dev.ultreon.quentangle.mc.Constants;
import dev.ultreon.quentangle.util.Env;
import dev.ultreon.quentangle.mc.network.NetworkRegistry;
import dev.ultreon.quentangle.mc.network.Networker;
import dev.ultreon.quentangle.mc.network.PacketReader;
import dev.ultreon.quentangle.mc.network.PayloadWrapper;
import dev.ultreon.quentangle.mc.network.endpoint.ClientEndpoint;
import dev.ultreon.quentangle.mc.network.endpoint.ServerEndpoint;
import dev.ultreon.quentangle.mc.network.packet.Packet;
import dev.ultreon.quentangle.mc.platform.McPlatform;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class FabricNetworkRegistry implements NetworkRegistry {
    public static final String UNREGISTERED_PACKET_WARNING = "Received packet of type {} from {} which is not registered for this network!";
    private final String modId;
    private final Networker networker;
    private final Map<Class<? extends Packet<?>>, CustomPacketPayload.Type<?>> typeRegistry = new HashMap<>();

    public FabricNetworkRegistry(Networker networker, String modId) {
        this.networker = networker;
        this.modId = modId;
    }

    @Override
    public <T extends Packet<T> & ClientEndpoint> void registerClient(String name, Class<T> clazz, PacketReader<T> reader) {
        var type = new CustomPacketPayload.Type<PayloadWrapper<T>>(ResourceLocation.fromNamespaceAndPath(modId, name));
        this.typeRegistry.put(clazz, type);

        PayloadTypeRegistry.playS2C().register(type,
                StreamCodec.of(
                        (buf, wrapper) -> wrapper.write(buf),
                        (buf) -> new PayloadWrapper<>(type, reader.read(buf))
                ));
        if (McPlatform.getEnv() == Env.CLIENT)
            ClientPlayNetworking.registerGlobalReceiver(type, (packet, context) -> {
                if (packet.type() == type) {
                    packet.packet.handle(this.networker);
                } else {
                    Constants.LOG.warn(UNREGISTERED_PACKET_WARNING, packet.type(), context.player().getScoreboardName());
                }
            });
    }

    @Override
    public <T extends Packet<T> & ServerEndpoint> void registerServer(String name, Class<T> clazz, PacketReader<T> reader) {
        var type = new CustomPacketPayload.Type<PayloadWrapper<T>>(ResourceLocation.fromNamespaceAndPath(modId, name));
        this.typeRegistry.put(clazz, type);

        PayloadTypeRegistry.playC2S().register(type,
                StreamCodec.of(
                        (buf, wrapper) -> wrapper.write(buf),
                        (buf) -> new PayloadWrapper<>(type, reader.read(buf))
                ));
        ServerPlayNetworking.registerGlobalReceiver(type, (packet, context) -> {
            if (packet.type() == type) {
                packet.packet.handle(this.networker, context.player());
            } else {
                Constants.LOG.warn("Received packet of type {} from {} which is not registered for this network!", packet.type(), context.player().getScoreboardName());
            }
        });
    }

    @Override
    public <T extends Packet<T> & ServerEndpoint & ClientEndpoint> void registerBiDirectional(String name, Class<T> clazz, PacketReader<T> reader) {
        var type = new CustomPacketPayload.Type<PayloadWrapper<T>>(ResourceLocation.fromNamespaceAndPath(modId, name));
        this.typeRegistry.put(clazz, type);

        StreamCodec<RegistryFriendlyByteBuf, PayloadWrapper<T>> codec = StreamCodec.of(
                (buf, wrapper) -> wrapper.write(buf),
                (buf) -> new PayloadWrapper<>(type, reader.read(buf))
        );
        PayloadTypeRegistry.playS2C().register(type, codec);
        PayloadTypeRegistry.playC2S().register(type, codec);
        if (McPlatform.getEnv() == Env.CLIENT)
            ClientPlayNetworking.registerGlobalReceiver(type, (packet, context) -> {
                if (packet.type() == type) {
                    packet.packet.handle(networker);
                } else {
                    Constants.LOG.warn("Received packet of type {} from {} which is not registered for this network!", packet.type(), context.player().getScoreboardName());
                }
            });
        ServerPlayNetworking.registerGlobalReceiver(type, (packet, context) -> {
            if (packet.type() == type) {
                packet.packet.handle(networker, context.player());
            } else {
                Constants.LOG.warn("Received packet of type {} from {} which is not registered for this network!", packet.type(), context.player().getScoreboardName());
            }
        });
    }

    <T extends Packet<T>> CustomPacketPayload.Type<?> getType(Class<T> aClass) {
        return typeRegistry.get(aClass);
    }
}
