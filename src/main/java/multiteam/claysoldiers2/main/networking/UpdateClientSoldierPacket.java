package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.core.Registry;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UpdateClientSoldierPacket extends PacketToServer<UpdateClientSoldierPacket> {
    private final List<CSModifier.Instance> modifiers;
    private final boolean isInvisibleToOthers;
    private final boolean canSeeInvisibleToOthers;
    private final boolean hostileAgainstItsOwnKind;
    private final boolean shouldStickToPosition;
    private final boolean shouldBeFuckingGlowing;
    private final ResourceKey<Level> type;

    /**
     * Constructor for reading the packet from the buffer.
     *
     * @param buf byte buffer to read the packet from.
     * @see NetworkUtils#readModifierList(FriendlyByteBuf)
     */
    public UpdateClientSoldierPacket(FriendlyByteBuf buf){
        this.modifiers = NetworkUtils.readModifierList(buf); //QBOI HELP ME
        this.isInvisibleToOthers = buf.readBoolean();
        this.canSeeInvisibleToOthers = buf.readBoolean();
        this.hostileAgainstItsOwnKind = buf.readBoolean();
        this.shouldStickToPosition = buf.readBoolean();
        this.shouldBeFuckingGlowing = buf.readBoolean();
        this.type = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
    }

    /**
     * Constructor for sending a packet.
     */
    public UpdateClientSoldierPacket(List<CSModifier.Instance> modifiers, boolean isInvisibleToOthers, boolean canSeeInvisibleToOthers, boolean hostileAgainstItsOwnKind, boolean shouldStickToPosition, boolean shouldBeFuckingGlowing, ResourceKey<Level> type){
        this.modifiers = modifiers;
        this.isInvisibleToOthers = isInvisibleToOthers;
        this.canSeeInvisibleToOthers = canSeeInvisibleToOthers;
        this.hostileAgainstItsOwnKind = hostileAgainstItsOwnKind;
        this.shouldBeFuckingGlowing = shouldBeFuckingGlowing;
        this.shouldStickToPosition = shouldStickToPosition;
        this.type = type;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        NetworkUtils.writeModifierList(buf, modifiers);
        buf.writeBoolean(isInvisibleToOthers);
        buf.writeBoolean(canSeeInvisibleToOthers);
        buf.writeBoolean(hostileAgainstItsOwnKind);
        buf.writeBoolean(shouldStickToPosition);
        buf.writeBoolean(shouldBeFuckingGlowing);
        buf.writeResourceLocation(type.location());
    }

    @Override
    protected void handle(@NotNull Connection connection, @NotNull ServerPlayer sender) {

    }
}
