package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class MushroomModifier extends NonStackingCSModifier {

    public MushroomModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierAdded(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierAttack(ClaySoldierEntity thisSoldier, Entity targetEntity, Instance thisModifierInstance) {
        if(targetEntity instanceof LivingEntity targetLivingEntity){
            targetLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
            if(targetLivingEntity instanceof ClaySoldierEntity targetSoldier){
                if(targetSoldier.getMaterial() == CSAPI.ClaySoldierMaterial.POTATO_CLAY_SOLDIER){
                    targetSoldier.setMaterial(CSAPI.ClaySoldierMaterial.POISONOUS_POTATO_CLAY_SOLDIER);
                }
            }
        }
    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldierEntity thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {

    }
}
