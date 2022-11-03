package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;
import software.bernie.geckolib3.geo.render.built.GeoModel;

import java.awt.*;
import java.util.List;

public class LilypadModifier extends NonStackingCSModifier {

    public LilypadModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierAdded(ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierAttack(ClaySoldier thisSoldier, Entity targetEntity, Instance thisModifierInstance) {

    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldier thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierTick(ClaySoldier thisSoldier, Instance thisModifierInstance) {
        if (thisSoldier.isInWater()) {
            thisSoldier.getNavigation().setCanFloat(true);
            thisSoldier.getJumpControl().jump();
        } else {
            thisSoldier.getNavigation().setCanFloat(false);
        }
    }

    @Override
    public void additionalModifierRenderComponent(ClaySoldier thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn, GeoModel model) {
        matrixStack.pushPose();

        matrixStack.translate(0.0d, 0.0d, 0.0d);
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        //matrixStack.mulPose(Vector3f.ZP.rotation(0));
        //matrixStack.mulPose(Vector3f.YP.rotation(0));
        matrixStack.mulPose(Vector3f.XP.rotation(80.0f));
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(Items.LILY_PAD), ItemTransforms.TransformType.FIXED, 15, 15, matrixStack, multiBufferSource, 0);

        matrixStack.popPose();
    }
}
