package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BasicRemoteItem extends Item {

    public BasicRemoteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        List<ClaySoldierEntity> entities = level.getEntitiesOfClass(ClaySoldierEntity.class, new AABB(player.getX() - 16, player.getY() - 16, player.getZ() - 16, player.getX() + 16, player.getY() + 16, player.getZ() + 16));

        for (ClaySoldierEntity soldier : entities) {
            soldier.removeSoldier();
        }

        return super.use(level, player, interactionHand);
    }
}
