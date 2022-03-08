package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.ModEntities;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.util.ItemAttributeUtils;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClaySoldierItem extends Item {

    public CSAPI.ClaySoldierMaterial material;

    public ClaySoldierItem(Properties properties, CSAPI.ClaySoldierMaterial material_) {
        super(properties);
        this.material = material_;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level levelIn = context.getLevel();
        BlockPos soldierSummonPos = context.getClickedPos().offset(context.getClickedFace().getNormal());
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        if (levelIn.getBlockState(soldierSummonPos) == Blocks.AIR.defaultBlockState() && !levelIn.isClientSide) {

            List<CSModifier.Instance> modifiers = ItemAttributeUtils.getModifiers(stack);
            if (player.isShiftKeyDown()) {
                int stackSize = stack.getCount();
                for (int i = 0; i < stackSize; i++) {
                    ClaySoldierEntity soldierEntity = placeSoldier(levelIn, context);
                    for (CSModifier.Instance instance : modifiers) {
                        soldierEntity.addModifier(instance);
                    }
                }
                stack.shrink(stackSize);

                levelIn.playSound(player, soldierSummonPos, SoundEvents.GRAVEL_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);

            } else {
                ClaySoldierEntity soldierEntity = placeSoldier(levelIn, context);

                for (CSModifier.Instance instance : modifiers) {
                    soldierEntity.addModifier(instance);
                }

                // Todo: remove out-commented code at some point.
//                CompoundTag tag = stack.getTag();
//                if (tag != null) {
//                    for (int j = 0; j < tag.getIntArray("Modifiers").length; j++) {
//                        soldierEntity.addModifier(ClaySoldierModifier.values()[tag.getIntArray("Modifiers")[j]], tag.getIntArray("ModifiersAmounts")[j]);
//                    }
//                }

                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add((new TranslatableComponent("tooltip.claysoldiers2.material")).append((new TranslatableComponent("tooltip." + ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.material." + this.material.getMaterialName().replace(" ", "_"))).withStyle(Style.EMPTY.withColor(this.material.getMaterialColor().getRGB()))));
    }

    public ClaySoldierEntity placeSoldier(Level level, UseOnContext context) {
        ClaySoldierEntity soldierEntity = new ClaySoldierEntity(ModEntities.CLAY_SOLDIER.get(), level);
        level.addFreshEntity(soldierEntity);
        soldierEntity.setPos(context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);
        soldierEntity.setMaterial(this.material);
        return soldierEntity;
    }

}
