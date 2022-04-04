package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.List;

public class UpdateClientSoldierPacket {

    private final List<CSModifier.Instance> modifiers;
    private final boolean isInvisibleToOthers;
    private final boolean canSeeInvisibleToOthers;
    private final boolean hostileAgainstItsOwnKind;
    private final boolean shouldStickToPosition;
    private final boolean shouldBeFuckingGlowing;
    private final ResourceKey<Level> type;

    public UpdateClientSoldierPacket(FriendlyByteBuf buf){
        this.modifiers = ; //QBOI HELP ME
        this.isInvisibleToOthers = buf.readBoolean();
        this.canSeeInvisibleToOthers = buf.readBoolean();
        this.hostileAgainstItsOwnKind = buf.readBoolean();
        this.shouldStickToPosition = buf.readBoolean();
        this.shouldBeFuckingGlowing = buf.readBoolean();
        this.type = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
    }

    public UpdateClientSoldierPacket(List<CSModifier.Instance> modifiers, boolean isInvisibleToOthers, boolean canSeeInvisibleToOthers, boolean hostileAgainstItsOwnKind, boolean shouldStickToPosition, boolean shouldBeFuckingGlowing, ResourceKey<Level> type){
        this.modifiers = modifiers;
        this.isInvisibleToOthers = isInvisibleToOthers;
        this.canSeeInvisibleToOthers = canSeeInvisibleToOthers;
        this.hostileAgainstItsOwnKind = hostileAgainstItsOwnKind;
        this.shouldBeFuckingGlowing = shouldBeFuckingGlowing;
        this.shouldStickToPosition = shouldStickToPosition;
        this.type = type;
    }
}
