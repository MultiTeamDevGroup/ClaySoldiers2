package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;
import java.util.function.Function;

/**
 * Clay Soldiers 2 networking class.
 *
 * @author MultiTeam
 */
public class Networking {
    private static SimpleChannel channel;

    private static int id = 0;

    /**
     * Network channel version..
     */
    private static final String VERSION = "1.0";

    /**
     * Advances the packet ID, and returns the new ID.
     *
     * @return the next ID to use for a packet.
     * @author Laz
     */
    private static int nextId() {
        return id++;
    }

    /**
     * Initialize networking channel.
     */
    public static void init() {
        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(ClaySoldiers2.MOD_ID, "claysoldiers2"),
                () -> VERSION, s -> Objects.equals(s, VERSION), s -> Objects.equals(s, VERSION)
        );

        register(UpdateClientSoldierPacket.class, UpdateClientSoldierPacket::new);
        register(SoldierPipelinePacket.class, SoldierPipelinePacket::new);
    }

    private static <T extends BasePacket<T>> void register(Class<T> clazz, Function<FriendlyByteBuf, T> construct) {
        channel.messageBuilder(clazz, nextId())
                .encoder(BasePacket::toBytes)
                .decoder(construct).consumerNetworkThread((first, second) -> {
                    return first.handlePacket(second);
                })
                .add();
    }

    /**
     * Send a packet to the server.
     *
     * @param packet the packet to send to the server.
     */
    public static void sendToServer(BasePacket<?> packet) {
        channel.sendToServer(packet);
    }

    /**
     * Send a packet to a client player.
     *
     * @param packet The packet to send to the client.
     * @param player The player to send the packet to.
     * @author Qboi123
     */
    public static void sendToClient(BasePacket<?> packet, ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    /**
     * Sends a packet to all connected clients.
     *
     * @param packet The packet to send to all clients.
     * @author Qboi123
     */
    public static void sendAll(BasePacket<?> packet) {
        channel.send(PacketDistributor.ALL.with(() -> null), packet);
    }

    public static void sendToLevel(BasePacket<?> packet, ResourceKey<Level> levelKey) {
        channel.send(PacketDistributor.DIMENSION.with(() -> levelKey), packet);
    }

    public static void sendToLevel(BasePacket<?> packet, Level level) {
        channel.send(PacketDistributor.DIMENSION.with(level::dimension), packet);
    }

    public static void sendToChunk(BasePacket<?> packet, LevelChunk chunk) {
        channel.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), packet);
    }
}
