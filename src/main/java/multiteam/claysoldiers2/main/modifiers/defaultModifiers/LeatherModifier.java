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
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class LeatherModifier extends NonStackingCSModifier {

    public LeatherModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
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
        float newDamageAmount = damageAmount;
        if (damageAmount > 0.5f) {
            newDamageAmount = damageAmount - 0.5f;
        }

        int damageBlockingChance = thisSoldier.getLevel().getRandom().nextInt(100);
        if (damageBlockingChance >= 99) {
            newDamageAmount = 0;
        }
        return new Pair<>(damageSource, newDamageAmount);
    }

    @Override
    public void onModifierTick(ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void additionalModifierRenderComponent(ClaySoldier thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn, GeoModel model) {
        matrixStack.pushPose();

        matrixStack.translate(0.0d, 0.5d, 0.0d);
        matrixStack.scale(0.33f, 0.33f, 0.33f);
        //matrixStack.mulPose(Vector3f.ZP.rotation(0));
        //matrixStack.mulPose(Vector3f.YP.rotation(0));
        //matrixStack.mulPose(Vector3f.XP.rotation(0));
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(ModItems.RENDERING_DISPLAY_LEATHER_CHESTPLATE.get()), ItemTransforms.TransformType.HEAD, packedLightIn, packedLightIn, matrixStack, multiBufferSource, 0);

        matrixStack.popPose();
    }
}
