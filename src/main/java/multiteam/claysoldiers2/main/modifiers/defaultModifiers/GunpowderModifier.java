package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import com.mojang.blaze3d.vertex.PoseStack;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class GunpowderModifier extends CSModifier {

    public GunpowderModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, boolean canBeStacked, int stackingLimit, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, canBeStacked, stackingLimit, incompatibleModifiers);
    }

    @Override
    public void onModifierAdded(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierAttack(ClaySoldierEntity thisSoldier, Entity targetEntity, Instance thisModifierInstance) {

    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldierEntity thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {
        thisModifierInstance.shrink(1, thisSoldier);
        thisSoldier.getLevel().explode(thisSoldier, thisSoldier.getX(), thisSoldier.getY(), thisSoldier.getZ(), 3.0F, Explosion.BlockInteraction.NONE);
    }

    @Override
    public void additionalModifierRenderComponent(ClaySoldierEntity thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn) {
        //super.additionalModifierRenderComponent(thisSoldier, entityYaw, partialTicks, matrixStack, multiBufferSource, packedLightIn);

        matrixStack.pushPose();

        System.out.println("EY IM RENDERING " + this);

        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(Items.CREEPER_HEAD), ItemTransforms.TransformType.HEAD, packedLightIn, packedLightIn, matrixStack, multiBufferSource, 0);

        matrixStack.popPose();

    }
}
