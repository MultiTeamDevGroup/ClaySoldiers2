package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.server.ServerLifecycleHooks;

public class SoldierPipelinePacket extends BiDirectionalPacket<SoldierPipelinePacket> {
    private final ResourceKey<Level> dim;
    private final int id;
    private final CompoundTag pipeline;

    /**
     * Constructor for reading the packet from the buffer.
     *
     * @param buf byte buffer to read the packet from.
     * @see NetworkUtils#readModifierList(FriendlyByteBuf)
     */
    public SoldierPipelinePacket(FriendlyByteBuf buf) {
        this.dim = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        this.id = buf.readInt();
        this.pipeline = buf.readAnySizeNbt();
    }

    /**
     * Constructor for sending a packet.
     */
    public SoldierPipelinePacket(ClaySoldier claySoldier, CompoundTag pipeline) {
        dim = claySoldier.level.dimension();
        id = claySoldier.getId();
        this.pipeline = pipeline;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(dim.location());
        buf.writeInt(id);
        buf.writeNbt(pipeline);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected void handleClient(Connection conn) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null && level.dimension().equals(dim)) {
            ClaySoldier claySoldier = (ClaySoldier) level.getEntity(id);
            if (claySoldier != null) {
                claySoldier.onPipeline(pipeline);
            }
        }
    }

    @Override
    protected void handleServer(Connection conn, ServerPlayer sender) {
        MinecraftServer currentServer = ServerLifecycleHooks.getCurrentServer();
        if (currentServer == null) {
            return;
        }

        ServerLevel level = currentServer.getLevel(dim);
        if (level != null) {
            ClaySoldier claySoldier = (ClaySoldier) level.getEntity(id);
            if (claySoldier != null) {
                claySoldier.onPipeline(pipeline);
            }
        }
    }
}
