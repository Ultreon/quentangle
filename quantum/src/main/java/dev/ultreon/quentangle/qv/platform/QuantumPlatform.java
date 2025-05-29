package dev.ultreon.quentangle.qv.platform;

import dev.ultreon.quantum.block.Block;
import dev.ultreon.quantum.block.Blocks;
import dev.ultreon.quantum.block.state.BlockState;
import dev.ultreon.quantum.entity.player.Player;
import dev.ultreon.quantum.item.Item;
import dev.ultreon.quantum.item.Items;
import dev.ultreon.quantum.item.UseItemContext;
import dev.ultreon.quantum.text.TextObject;
import dev.ultreon.quantum.util.NamespaceID;
import dev.ultreon.quantum.world.UseResult;
import dev.ultreon.quantum.world.World;
import dev.ultreon.quantum.world.WorldAccess;
import dev.ultreon.quantum.world.vec.BlockVec;
import dev.ultreon.quentangle.CommonConstants;
import dev.ultreon.quentangle.Game;
import dev.ultreon.quentangle.ModLoader;
import dev.ultreon.quentangle.api.block.IBlockApi;
import dev.ultreon.quentangle.api.block.IBlockStateApi;
import dev.ultreon.quentangle.api.block.IBlocksApi;
import dev.ultreon.quentangle.api.item.IItemApi;
import dev.ultreon.quentangle.api.player.IPlayerApi;
import dev.ultreon.quentangle.api.world.IWorldApi;
import dev.ultreon.quentangle.block.IBlockFactory;
import dev.ultreon.quentangle.item.IItemsApi;
import dev.ultreon.quentangle.platform.IMod;
import dev.ultreon.quentangle.platform.services.IPlatform;
import dev.ultreon.quentangle.registry.INamespaceID;
import dev.ultreon.quentangle.text.IMutableTextObject;
import dev.ultreon.quentangle.text.ITextObject;
import dev.ultreon.quentangle.util.Env;
import dev.ultreon.quentangle.util.InteractResult;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class QuantumPlatform implements IPlatform {

    private final IBlocksApi blocksApi = new IBlocksApi() {
        @Override
        public IBlockApi getAir() {
            return (IBlockApi) Blocks.AIR;
        }

        @Override
        public IBlockApi getStone() {
            return (IBlockApi) Blocks.STONE;
        }

        @Override
        public IBlockApi getDirt() {
            return (IBlockApi) Blocks.DIRT;
        }

        @Override
        public IBlockApi getGrass() {
            return (IBlockApi) Blocks.GRASS_BLOCK;
        }

        @Override
        public IBlockApi create(IBlockFactory factory) {
            return (IBlockApi) (Object) new Block() {
                @Override
                public @NotNull UseResult use(@NotNull WorldAccess world, @NotNull Player player, @NotNull Item item, @NotNull BlockVec pos) {
                    InteractResult use = factory.use((IBlockStateApi) world.get(pos), (IWorldApi) world, new dev.ultreon.quentangle.util.BlockVec(pos.x, pos.y, pos.z), (IPlayerApi) player, new EmptyBlockHitApi());
                    return switch (use) {
                        case FAIL -> UseResult.DENY;
                        case PASS -> UseResult.SKIP;
                        case SUCCESS, CONSUME -> UseResult.ALLOW;
                        default -> {
                            CommonConstants.LOG.warn("Unknown interact result: {}", use);
                            yield UseResult.DENY;
                        }
                    };
                }

                @Override
                public void onDestroy(@NotNull World world, @NotNull BlockVec breaking, @NotNull BlockState blockState, @Nullable Player breaker) {
                    super.onDestroy(world, breaking, blockState, breaker);

                    factory.onBreak((IBlockStateApi) blockState, (IWorldApi) world, new dev.ultreon.quentangle.util.BlockVec(breaking.x, breaking.y, breaking.z), (IPlayerApi) breaker);
                }

                @Override
                public @NotNull BlockState onPlacedBy(@NotNull BlockState blockMeta, @NotNull BlockVec at, @NotNull UseItemContext context) {
                    if (factory.onPlace((IBlockStateApi) blockMeta, (IWorldApi) context.world(), new dev.ultreon.quentangle.util.BlockVec(at.x, at.y, at.z), (IPlayerApi) context.player())) {
                        return blockMeta;
                    } else {
                        return Blocks.AIR.getDefaultState();
                    }
                }
            };
        }
    };
    private final IItemsApi itemsApi = new IItemsApi() {
        @Override
        public IItemApi getAir() {
            return (IItemApi) Items.AIR;
        }
    };

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public String getDefaultNamespace() {
        return "quantum";
    }

    @Override
    public int getDefaultMaxStackSize() {
        return 64;
    }

    @Override
    public int getChunkBase() {
        return 0;
    }

    @Override
    public boolean is3DChunks() {
        return true;
    }

    @Override
    public INamespaceID createId(String domain, String path) {
        return (INamespaceID) (Object) new NamespaceID(domain, path);
    }

    @Override
    public IMutableTextObject createLiteralText(String text) {
        return (IMutableTextObject) TextObject.literal(text);
    }

    @Override
    public IMutableTextObject createTranslatableText(String key, Object... args) {
        return (IMutableTextObject) TextObject.translation(key, args);
    }

    @Override
    public IMutableTextObject createTranslatableText(String key, Iterable<Object> args) {
        if (args instanceof Collection<Object> collection) {
            return (IMutableTextObject) TextObject.translation(key, collection.toArray());
        }
        return (IMutableTextObject) TextObject.translation(key, List.of(args).toArray());
    }

    @Override
    public ITextObject createEmptyText() {
        return (ITextObject) TextObject.empty();
    }

    @Override
    public Optional<IMod> getMod(String modId) {
        return Optional.empty();
    }

    @Override
    public ModLoader getModLoader() {
        return ModLoader.Fabric;
    }

    @Override
    public Game getGame() {
        return Game.QuantumVoxel;
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public Env getEnv() {
        return switch (FabricLoader.getInstance().getEnvironmentType()) {
            case CLIENT -> Env.CLIENT;
            case SERVER -> Env.SERVER;
        };
    }

    @Override
    public int getChunkHeight() {
        return World.CS;
    }

    @Override
    public int getChunkWidth() {
        return World.CS;
    }

    @Override
    public IBlocksApi getBlocksApi() {
        return blocksApi;
    }

    @Override
    public IItemsApi getItemsApi() {
        return itemsApi;
    }
}
