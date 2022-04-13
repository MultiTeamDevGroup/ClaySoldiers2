package multiteam.claysoldiers2.main.networking;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class BiDirectionalPacket<T extends BiDirectionalPacket<T>> extends BasePacket<T> {
    public BiDirectionalPacket() {
        super();
    }

    @Override
    public final boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        switch (ctx.getDirection()) {
            case PLAY_TO_CLIENT -> ctx.enqueueWork(() -> handleClient(ctx.getNetworkManager()));
            case PLAY_TO_SERVER -> ctx.enqueueWork(() -> handleServer(ctx.getNetworkManager(), ctx.getSender()));
            default -> {
            }
        }
        ctx.setPacketHandled(true);
        return true;
    }

    protected abstract void handleClient(Connection manager);

    protected abstract void handleServer(Connection manager, ServerPlayer sender);
}
