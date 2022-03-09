package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.ModModifiers;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class BlazeRodModifier extends NonStackingCSModifier {

    public final int defaultFireSeconds;
    public int modifiedFireSeconds;

    public BlazeRodModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
        this.defaultFireSeconds = 5;
    }

    @Override
    public void onModifierAdded(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {
        thisSoldier.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this.getModifierItem()));
    }

    @Override
    public void onModifierAttack(ClaySoldierEntity thisSoldier, Entity targetEntity, Instance thisModifierInstance) {
        targetEntity.setSecondsOnFire(Math.max(modifiedFireSeconds, defaultFireSeconds));

        Instance coalModifier = null;
        for (Instance instance : thisSoldier.getModifiers()){
            if(instance.getModifier() == ModModifiers.COAL_BOOST.get()){
                coalModifier = instance;
                break;
            }
        }
        if(coalModifier != null){
            coalModifier.setAmount(coalModifier.getAmount()-1);
        }
    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldierEntity thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }
}
