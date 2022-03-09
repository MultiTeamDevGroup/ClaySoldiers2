package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class HealingModifier extends CSModifier {

    public final float healAmount;

    public HealingModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, boolean canBeStacked, int stackingLimit, List<RegistryObject<CSModifier>> incompatibleModifiers, float healAmount) {
        super(modifierType, modifierItem, modifierName, modifierColor, canBeStacked, stackingLimit, incompatibleModifiers);
        this.healAmount = healAmount;
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
        int rand = thisSoldier.getLevel().getRandom().nextInt(100);
        if(rand > CSAPI.Settings.handUsageChance && thisSoldier.getHealth() < thisSoldier.getAttributes().getInstance(Attributes.MAX_HEALTH).getBaseValue()){
            thisSoldier.heal(this.healAmount);
        }
    }
}
