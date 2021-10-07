package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class ClaySoldierModel extends AnimatedGeoModel<ClaySoldierEntity> implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public ResourceLocation getModelLocation(ClaySoldierEntity claySoldierEntity) {
        return new ResourceLocation(ClaySoldiers2.MOD_ID, "geo/entity/clay/clayman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ClaySoldierEntity claySoldierEntity) {
        String texLoc = "";
        String texLocBase = "textures/entity/";
        String subLocClayColor = "clayman_color/";
        String subLocSpecial = "clayman_special/";
        switch (claySoldierEntity.getVariant()){
            default:
                texLoc = texLocBase + "clayman.png";
                break;
            case 1:
                texLoc = texLocBase + subLocClayColor + "clayman_white.png";
                break;
            case 2:
                texLoc = texLocBase + subLocClayColor + "clayman_yellow.png";
                break;
            case 3:
                texLoc = texLocBase + subLocClayColor + "clayman_red.png";
                break;
            case 4:
                texLoc = texLocBase + subLocClayColor + "clayman_purple.png";
                break;
            case 5:
                texLoc = texLocBase + subLocClayColor + "clayman_pink.png";
                break;
            case 6:
                texLoc = texLocBase + subLocClayColor + "clayman_orange.png";
                break;
            case 7:
                texLoc = texLocBase + subLocClayColor + "clayman_magenta.png";
                break;
            case 8:
                texLoc = texLocBase + subLocClayColor + "clayman_lime.png";
                break;
            case 9:
                texLoc = texLocBase + subLocClayColor + "clayman_light_gray.png";
                break;
            case 10:
                texLoc = texLocBase + subLocClayColor + "clayman_light_blue.png";
                break;
            case 11:
                texLoc = texLocBase + subLocClayColor + "clayman_green.png";
                break;
            case 12:
                texLoc = texLocBase + subLocClayColor + "clayman_gray.png";
                break;
            case 13:
                texLoc = texLocBase + subLocClayColor + "clayman_cyan.png";
                break;
            case 14:
                texLoc = texLocBase + subLocClayColor + "clayman_brown.png";
                break;
            case 15:
                texLoc = texLocBase + subLocClayColor + "clayman_blue.png";
                break;
            case 16:
                texLoc = texLocBase + subLocClayColor + "clayman_black.png";
                break;
            case 17:
                texLoc = texLocBase + subLocSpecial + "clayman_carrot.png";
                break;
            case 18:
                texLoc = texLocBase + subLocSpecial + "clayman_potato.png";
                break;
            case 19:
                texLoc = texLocBase + subLocSpecial + "clayman_poisonous_potato.png";
                break;
            case 20:
                texLoc = texLocBase + subLocSpecial + "clayman_beetroot.png";
                break;
            case 21:
                texLoc = texLocBase + subLocSpecial + "clayman_apple.png";
                break;
            case 22:
                texLoc = texLocBase + subLocSpecial + "clayman_melon.png";
                break;
        }
        return new ResourceLocation(ClaySoldiers2.MOD_ID, texLoc);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ClaySoldierEntity claySoldierEntity) {
        return new ResourceLocation(ClaySoldiers2.MOD_ID, "animations/entity/clay/clayman.animation.json");
    }

    @Override
    public void setLivingAnimations(ClaySoldierEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
