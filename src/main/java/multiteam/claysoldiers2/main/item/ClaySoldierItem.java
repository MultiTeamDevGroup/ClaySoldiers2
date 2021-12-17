package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.ModEntities;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierAPI;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClaySoldierItem extends Item {

    public ClaySoldierAPI.ClaySoldierMaterial material;

    public ClaySoldierItem(Properties properties, ClaySoldierAPI.ClaySoldierMaterial material_) {
        super(properties);
        this.material = material_;
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
                    placeSoldier(worldin, context);
                }
                stack.shrink(stackSize);

                worldin.playSound(playerEntity, soldierSummonPos, SoundEvents.GRAVEL_BREAK, SoundSource.PLAYERS, 1.0f ,1.0f);

            }else{
                placeSoldier(worldin, context);

                if(!playerEntity.isCreative()){
                    stack.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add((new TranslatableComponent("tooltip.claysoldiers2.material")).append((new TranslatableComponent("tooltip." + ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.material."+ this.material.getMaterialName().replace(" ", "_"))).withStyle(Style.EMPTY.withColor(this.material.getMaterialColor().getRGB()))));
    }

    public ClaySoldierEntity placeSoldier(Level level, UseOnContext context){
        ClaySoldierEntity soldierEntity = new ClaySoldierEntity(ModEntities.CLAY_SOLDIER.get(), level);
        level.addFreshEntity(soldierEntity);
        soldierEntity.setPos(context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);
        soldierEntity.setMaterial(this.material);
        return soldierEntity;
    }

    //*
    // @NotNull
    //    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
    //        List<ItemWithTextTooltipComponent.ItemTextCompoundRow> rowList = new ArrayList<>();;
    //
    //        if(this.modifiers != null){
    //            for (ClaySoldierAPI.ClaySoldierModifier modifier: this.modifiers) {
    //
    //                rowList.add(new ItemWithTextTooltipComponent.ItemTextCompoundRow(List.of(
    //                        new ItemStack(modifier.getModifierItem())
    //                ), (TranslatableComponent) new TranslatableComponent("tooltip."+ ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.modifier." + modifier.getModifierName()).withStyle(Style.EMPTY.withColor(modifier.getModifierColor().getRGB()))));
    //
    //            }
    //        }
    //
    //        return Optional.of(new ItemWithTextTooltipComponent(rowList));
    //    }
    //
    //
    // public ItemStack addModifiers(List<ClaySoldierAPI.ClaySoldierModifier> modifs, ItemStack stack){
    //        this.modifiers.clear();
    //        this.modifiers = modifs;
    //        CompoundTag tag = new CompoundTag();
    //        int[] modifs_ = new int[modifs.size()];
    //        for (int i = 0; i < modifs.size(); i++){
    //            modifs_[i] = modifs.get(i).ordinal();
    //        }
    //        tag.putIntArray("Modifiers", modifs_);
    //        stack.setTag(tag);
    //        return stack;
    //    }
    //
    //*//



}
