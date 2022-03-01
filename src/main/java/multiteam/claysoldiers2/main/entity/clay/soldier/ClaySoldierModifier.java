package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierAPI.ClaySoldierModifierType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ClaySoldierModifier extends ForgeRegistryEntry<ClaySoldierModifier> {
    private static final List<ClaySoldierModifier> VALUES = new ArrayList<>();
    private static int currentId = 0;

    private final ClaySoldierModifierType modifierType;
    private final Item modifierItem;
    private final String modifierName;
    private final Color modifierColor;
    private final boolean canBeStacked;
    private final int maxStackingLimit;
    private final ModifierBehavior behavior;
    private final List<Item> incompatibleModifiers;
    private final ModifierBehaviorAttackEvent attackEventBehavior;
    private final ModifierBehaviorDefenseEvent defenseEventBehavior;

    private final int ordinal;

    public ClaySoldierModifier(ClaySoldierModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, boolean canBeStacked, int stackingLimit, ModifierBehavior behavior, List<Item> incompatibleModifiers, ModifierBehaviorAttackEvent attackBehavior, ModifierBehaviorDefenseEvent defenseBehavior) {
        this.modifierType = modifierType;
        this.modifierItem = modifierItem;
        this.modifierName = modifierName;
        this.modifierColor = modifierColor;
        this.canBeStacked = canBeStacked;
        this.maxStackingLimit = stackingLimit;
        this.behavior = behavior;
        this.incompatibleModifiers = incompatibleModifiers;
        this.attackEventBehavior = attackBehavior;
        this.defenseEventBehavior = defenseBehavior;

        VALUES.add(this);
        ordinal = currentId++;
    }

    public ClaySoldierModifierType getModifierType() {
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

    public List<Item> getIncompatibleModifiers() {
        return this.incompatibleModifiers;
    }

    public void ExecuteModifierOn(ClaySoldierEntity entity, ClaySoldierModifier thisModifier) {
        this.behavior.ExecuteModifierOn(entity, thisModifier);
    }

    public void ExecuteModifierOnAttack(ClaySoldierEntity self, ClaySoldierModifier thisModifier, LivingEntity attacked) {
        this.attackEventBehavior.ExecuteModifierOnAttack(self, thisModifier, attacked);
    }

    public void ExecuteModifierOnDefend(LivingEntity attacker, ClaySoldierModifier thisModifier, ClaySoldierEntity self) {
        this.defenseEventBehavior.ExecuteModifierOnDefend(attacker, thisModifier, self);
    }

    public String getDescriptionId() {
        return "tooltip." + ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.modifier." + getModifierName();
    }

    @FunctionalInterface
    public interface ModifierBehavior {
        void ExecuteModifierOn(ClaySoldierEntity soldier, ClaySoldierModifier thisModifier);
    }

    @FunctionalInterface
    public interface ModifierBehaviorAttackEvent {
        void ExecuteModifierOnAttack(ClaySoldierEntity self, ClaySoldierModifier thisModifier, LivingEntity attacked);
    }

    @FunctionalInterface
    public interface ModifierBehaviorDefenseEvent {
        void ExecuteModifierOnDefend(LivingEntity attacker, ClaySoldierModifier thisModifier, ClaySoldierEntity self);
    }

    @Deprecated(forRemoval = true)
    public static ClaySoldierModifier[] values() {
        return VALUES.toArray(new ClaySoldierModifier[0]);
    }

    @Deprecated(forRemoval = true)
    public static ClaySoldierModifier valueOf(String name) {
        return Registration.getModifierRegistry().getValue(new ResourceLocation(name));
    }

    public int ordinal() {
        return ordinal;
    }

    public static class Instance {
        private final ClaySoldierModifier modifier;
        private int amount;

        public Instance(ClaySoldierModifier modifier, int amount) {
            this.modifier = modifier;
            this.amount = amount;
        }

        public ClaySoldierModifier getModifier() {
            return modifier;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Instance copy() {
            return new Instance(modifier, amount);
        }
    }
}
