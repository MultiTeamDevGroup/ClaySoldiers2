package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;

public class Networking {
    private static SimpleChannel channel;
    private static int id = 0;

    private static final String VERSION = "1.0";

    private static int nextId() {
        return id++;
    }

    public static void registerMessages() {
        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(ClaySoldiers2.MOD_ID, "claysoldiers2"),
                () -> VERSION, s -> Objects.equals(s, VERSION), s -> Objects.equals(s, VERSION)
        );

        channel.messageBuilder(UpdateClientSoldierPacket.class, nextId())
                .encoder(UpdateClientSoldierPacket::toBytes)
                .decoder(UpdateClientSoldierPacket::new)
                .consumer(UpdateClientSoldierPacket::handlePacket)
                .add();
    }

    public static void sendToServer(BasePacket<?> packet) {
        channel.sendToServer(packet);
    }

    public static void sendToClient(BasePacket<?> packet, ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

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
