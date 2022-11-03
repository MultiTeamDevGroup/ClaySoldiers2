package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import com.mojang.blaze3d.vertex.PoseStack;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;
import software.bernie.geckolib3.geo.render.built.GeoModel;

import java.awt.*;
import java.util.List;

public class GoldNuggetModifier extends NonStackingCSModifier {

    public GoldNuggetModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
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
    public void onModifierTick(ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void additionalModifierRenderComponent(ClaySoldier thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn, GeoModel model) {
        matrixStack.pushPose();

        renderItemOnSoldierHead(ModItems.RENDERING_DISPLAY_CROWN.get(), 0.25f, 0.0d, thisSoldier, entityYaw, partialTicks, matrixStack, multiBufferSource, packedLightIn, model);

        matrixStack.popPose();

    }
}
