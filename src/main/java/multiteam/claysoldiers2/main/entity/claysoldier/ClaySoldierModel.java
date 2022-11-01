package multiteam.claysoldiers2.main.entity.claysoldier;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class ClaySoldierModel extends AnimatedGeoModel<ClaySoldier> implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public ResourceLocation getModelResource(ClaySoldier claySoldier) {
        return new ResourceLocation(ClaySoldiers2.MOD_ID, "geo/entity/clay/clayman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClaySoldier claySoldier) {
        return new ResourceLocation(ClaySoldiers2.MOD_ID, "textures/entity/clayman/soldier_" + claySoldier.getMaterial().getMaterialName().replace(" ", "_") + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClaySoldier claySoldier) {
        return new ResourceLocation(ClaySoldiers2.MOD_ID, "animations/entity/clay/clayman.animation.json");
    }

    @Override
    public void setLivingAnimations(ClaySoldier entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        float limbSwing = customPredicate.getLimbSwing();
        float limbSwingAmount = customPredicate.getLimbSwingAmount();

        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        IBone rightLeg = this.getAnimationProcessor().getBone("rightleg");
        IBone leftLeg = this.getAnimationProcessor().getBone("leftleg");
        IBone rightHand = this.getAnimationProcessor().getBone("righthand");
        IBone leftHand = this.getAnimationProcessor().getBone("lefthand");

        leftLeg.setRotationX((float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        rightLeg.setRotationX((float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
        leftHand.setRotationX((float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        rightHand.setRotationX((float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
    }
}
