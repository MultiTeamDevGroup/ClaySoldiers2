package multiteam.claysoldiers2.main.entity.claysoldier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClaySoldierRenderer extends GeoEntityRenderer<ClaySoldierEntity> {

    private static List<CSModifier> modifierRenderExceptionsOffhand = new ArrayList<>();
    private static List<CSModifier> modifierRenderExceptionsMainHand = new ArrayList<>();

    public ClaySoldierRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ClaySoldierModel());
    }

    @Override
    public RenderType getRenderType(ClaySoldierEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("righthand") && getRenderExceptionsForModifiersMainHand(mainHand.getItem())) {
            stack.pushPose();
            stack.mulPose(Vector3f.XP.rotationDegrees(-75));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            stack.translate(0.09D, 0.06D, 0.2D); //x z y actually, where z is the way the entity is facing
            stack.scale(0.25f, 0.25f, 0.25f);
            Minecraft.getInstance().getItemRenderer().renderStatic(mainHand, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }

        if (bone.getName().equals("lefthand") && getRenderExceptionsForModifiersOffhand(offHand.getItem())) {
            stack.pushPose();
            stack.mulPose(Vector3f.XP.rotationDegrees(-75));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            stack.translate(-0.09D, 0.06D, 0.2D); //x z y actually, where z is the way the entity is facing
            stack.scale(0.25f, 0.25f, 0.25f);
            Minecraft.getInstance().getItemRenderer().renderStatic(offHand, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }

        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void renderLate(ClaySoldierEntity thisSoldier, PoseStack matrixStack, float ticks, MultiBufferSource bufferIn, VertexConsumer vertexConsumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderLate(thisSoldier, matrixStack, ticks, bufferIn, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);

        if(thisSoldier.shouldStickToPosition){
            matrixStack.pushPose();

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack stack = new ItemStack(ModItems.RENDERING_DISPLAY_SLIME_SPLOTCH.get());
            BakedModel ibakedmodel = itemRenderer.getModel(stack, thisSoldier.getLevel(), null, 0);

            itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, matrixStack, bufferIn, packedLightIn, packedLightIn, ibakedmodel);

            matrixStack.popPose();
        }

        System.out.println("trying to render shits and farts");

        if(!thisSoldier.getModifiers().isEmpty()){
            for (CSModifier.Instance instance : thisSoldier.getModifiers()){
                System.out.println(instance);
                if(instance != null){
                    System.out.println("get rendered bitch " + instance);
                    instance.getModifier().additionalModifierRenderComponent(thisSoldier, thisSoldier.getYRot(), partialTicks, matrixStack, bufferIn, packedLightIn);
                }
            }
        }
    }

    @Override
    public void render(ClaySoldierEntity thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(thisSoldier, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);



    }


    private boolean getRenderExceptionsForModifiersOffhand(Item item){
        boolean ret = true;
        for (CSModifier modifier : modifierRenderExceptionsOffhand){
            if (modifier.getModifierItem() == item) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    private boolean getRenderExceptionsForModifiersMainHand(Item item){
        boolean ret = true;
        for (CSModifier modifier : modifierRenderExceptionsMainHand){
            if (modifier.getModifierItem() == item) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static void addRenderExceptionForModifierOffhand(CSModifier modifier){
        modifierRenderExceptionsOffhand.add(modifier);
    }

    public static void addRenderExceptionForModifierMainHand(CSModifier modifier){
        modifierRenderExceptionsMainHand.add(modifier);
    }

    @Override
    protected int getBlockLightLevel(ClaySoldierEntity soldierEntity, BlockPos blockPos) {
        if(soldierEntity.shouldBeFuckingGlowing){
            return 15;
        }else{
            return soldierEntity.level.getBrightness(LightLayer.BLOCK, blockPos);
        }
    }
}
