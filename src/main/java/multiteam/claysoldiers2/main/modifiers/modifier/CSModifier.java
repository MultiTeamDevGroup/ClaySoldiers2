package multiteam.claysoldiers2.main.modifiers.modifier;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CSModifier extends ForgeRegistryEntry<CSModifier> {

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
        for (RegistryObject<CSModifier> obj : this.incompatibleModifiers ) {
            ret.add(obj.get());
        }
        return ret;
    }

    public abstract void onModifierAdded(ClaySoldierEntity thisSoldier, Instance thisModifierInstance);
    public abstract void onModifierTick(ClaySoldierEntity thisSoldier, Instance thisModifierInstance);
    public abstract void onModifierAttack(ClaySoldierEntity thisSoldier, Entity targetEntity, Instance thisModifierInstance);
    public abstract Pair<DamageSource, Float> onModifierHurt(ClaySoldierEntity thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance);
    public abstract void onModifierDeath(DamageSource damageSource, ClaySoldierEntity thisSoldier, Instance thisModifierInstance);

    public void additionalModifierRenderComponent(){

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

        public void shrink(int amount, ClaySoldierEntity thisSoldier){
            this.amount--;
        }

        public Instance copy() {
            return new Instance(modifier, amount);
        }
    }
}


