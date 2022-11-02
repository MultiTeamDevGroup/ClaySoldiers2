package multiteam.claysoldiers2.main.modifiers.modifier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class CSModifier {

    public enum ModifierType {
        MAIN_HAND, //occupies the main hand
        MAIN_HAND_BOOST_ITEM, //occupies the main hand, while having a single use per item
        MAIN_HAND_AMOUNT_BOOST_ITEM, //occupies the main hand, while having an amount of uses per item
        OFF_HAND, //occupies the offhand
        OFF_HAND_BOOST_ITEM, //occupies the offhand, while having a single use per item
        OFF_HAND_INF_BOOST_COMBINED, //occupies the offhand, and applies an effect as long as its combined with another modifier
        ANY_HAND_BOOST_ITEM, //can be equipped in any hand, while having a single use per item
        ANY_HAND_AMOUNT_BOOST_ITEM, //occupies any hand, while having an amount of uses per item
        BOTH_HANDS, //occupies both hands
        BOOST_ITEM, //has a single use per item
        INF_BOOST, //applies an effect infinitely
        INF_BOOST_COSMETIC, //applies a cosmetic only effect infinitely
        INF_BOOST_COMBINED, //applies an effect as long as its combined with another modifier
        EFFECT, //applies effect for a period of time
        INF_EFFECT, //applies status effect infinitely
        CANCEL; //cancels any modifier on the soldier

        public boolean anyOf(List<ModifierType> type) {
            return type.contains(this);
        }
    }

    private final ModifierType modifierType;
    private final Item modifierItem;
    private final String modifierName;
    private final Color modifierColor;
    private final boolean canBeStacked;
    private final int maxStackingLimit;
    private final List<RegistryObject<CSModifier>> incompatibleModifiers;


    public CSModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, boolean canBeStacked, int stackingLimit, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        this.modifierType = modifierType;
        this.modifierItem = modifierItem;
        this.modifierName = modifierName;
        this.modifierColor = modifierColor;
        this.canBeStacked = canBeStacked;
        this.maxStackingLimit = stackingLimit;
        this.incompatibleModifiers = incompatibleModifiers;

    }

    public ModifierType getModifierType() {
        return this.modifierType;
    }

    public Item getModifierItem() {
        return this.modifierItem;
    }

    public String getModifierName() {
        return this.modifierName;
    }

    public Color getModifierColor() {
        return this.modifierColor;
    }

    public boolean canBeStacked() {
        return this.canBeStacked;
    }

    public int getMaxStackingLimit() {
        return this.maxStackingLimit;
    }

    public List<CSModifier> getIncompatibleModifiers() {
        List<CSModifier> ret = new ArrayList<>();
        for (RegistryObject<CSModifier> obj : this.incompatibleModifiers) {
            ret.add(obj.get());
        }
        return ret;
    }

    public abstract void onModifierAdded(ClaySoldier thisSoldier, Instance thisModifierInstance);

    public abstract void onModifierTick(ClaySoldier thisSoldier, Instance thisModifierInstance);

    public abstract void onModifierAttack(ClaySoldier thisSoldier, Entity targetEntity, Instance thisModifierInstance);

    public abstract Pair<DamageSource, Float> onModifierHurt(ClaySoldier thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance);

    public abstract void onModifierDeath(DamageSource damageSource, ClaySoldier thisSoldier, Instance thisModifierInstance);

    public void additionalModifierRenderComponent(ClaySoldier thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn) {}

    public void renderItemOnSoldierHead(Item itemToRender, float scale, double height, ClaySoldier thisSoldier, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource multiBufferSource, int packedLightIn){
        matrixStack.pushPose();

        matrixStack.translate(0.0d, height, 0.0d);
        matrixStack.scale(scale, scale, scale);
        //TODO make head rotation work
        //We need that + 180 to face the item into the correct direction
        //Straight up thisSoldier.getYHeadRot() causes to rotate with the head, but also adds the entityYaw so it rotates more than it should
        //Subtracting the entity yaw doesnt solve it; thisSoldier.getYHeadRot() - entityYaw
        //Also thisSoldier.getYHeadRot() needs to be reversed cuz it rotates in the wrong direction so (-thisSoldier.getYHeadRot())
        // :(
        matrixStack.mulPose(Vector3f.YP.rotationDegrees((float) ((180.0f) + (entityYaw * ((float) Math.PI / 180F)) - (-thisSoldier.getYHeadRot()) ) ));

        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(itemToRender), ItemTransforms.TransformType.HEAD, packedLightIn, packedLightIn, matrixStack, multiBufferSource, 0);

        matrixStack.popPose();
    }

    public String getDescriptionId() {
        return "tooltip." + ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.modifier." + getModifierName();
    }

    public static class Instance {
        private final CSModifier modifier;
        private int amount;

        public Instance(CSModifier modifier, int amount) {
            this.modifier = modifier;
            this.amount = amount;
        }

        public CSModifier getModifier() {
            return modifier;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void shrink(int amount, ClaySoldier thisSoldier) {
            this.amount--;
        }

        public void grow(int amount, ClaySoldier thisSoldier) {
            this.amount++;
        }

        public Instance copy() {
            return new Instance(modifier, amount);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Instance instance = (Instance) o;
            return amount == instance.amount && modifier.equals(instance.modifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(modifier, amount);
        }
    }
}


