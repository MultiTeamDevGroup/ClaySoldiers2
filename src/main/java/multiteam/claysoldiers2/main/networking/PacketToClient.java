package multiteam.claysoldiers2.main.networking;

import net.minecraft.network.Connection;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class PacketToClient<T extends PacketToClient<T>> extends BasePacket<T> {
    public PacketToClient() {
        super();
    }

    @Override
    public final boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        if (ctx.getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.enqueueWork(() -> handle(ctx.getNetworkManager()));
        ctx.setPacketHandled(true);
        return true;
    }

    protected abstract void handle(Connection connection);
}
