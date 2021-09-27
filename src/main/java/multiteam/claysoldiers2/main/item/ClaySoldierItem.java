package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.main.entity.ModEntities;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ClaySoldierItem extends Item {

    public ClaySoldierItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level worldin = context.getLevel();
        BlockPos soldierSummonPos = context.getClickedPos().offset(context.getClickedFace().getNormal());
        Player playerEntity = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if(worldin.getBlockState(soldierSummonPos) == Blocks.AIR.defaultBlockState() && !worldin.isClientSide){

            if(playerEntity.isShiftKeyDown()){
                int stackSize = stack.getCount();
                for (int i = 0; i < stackSize; i++){
                    ClaySoldierEntity soldierEntity = new ClaySoldierEntity(ModEntities.CLAY_SOLDIER.get(), worldin);
                    worldin.addFreshEntity(soldierEntity);
                    soldierEntity.setPos(context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);
                }
                stack.shrink(stackSize);

                worldin.playSound(playerEntity, soldierSummonPos, SoundEvents.GRAVEL_BREAK, SoundSource.PLAYERS, 1.0f ,1.0f);

            }else{
                ClaySoldierEntity soldierEntity = new ClaySoldierEntity(ModEntities.CLAY_SOLDIER.get(), worldin);
                worldin.addFreshEntity(soldierEntity);
                soldierEntity.setPos(context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);

                if(!playerEntity.isCreative()){
                    stack.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
