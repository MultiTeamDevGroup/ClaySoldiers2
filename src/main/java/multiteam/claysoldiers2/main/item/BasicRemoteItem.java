package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicRemoteItem extends Item {

    public BasicRemoteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        List<ClaySoldier> entities = level.getEntitiesOfClass(ClaySoldier.class, new AABB(player.getX() - CSAPI.Settings.basicRemoteRadius, player.getY() - CSAPI.Settings.basicRemoteRadius, player.getZ() - CSAPI.Settings.basicRemoteRadius, player.getX() + CSAPI.Settings.basicRemoteRadius, player.getY() + CSAPI.Settings.basicRemoteRadius, player.getZ() + CSAPI.Settings.basicRemoteRadius));

        for (ClaySoldier soldier : entities) {
            soldier.removeSoldier();
        }

        return super.use(level, player, interactionHand);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add((new TranslatableComponent("tooltip.claysoldiers2.basic_remote_info")).append(new TextComponent("" + CSAPI.Settings.basicRemoteRadius)));
    }
}
