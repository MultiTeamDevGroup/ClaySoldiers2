package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class SugarModifier extends NonStackingCSModifier {

    public SugarModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierAttack(Entity targetEntity) {

    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldierEntity thisSoldier, DamageSource damageSource, float damageAmount) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier) {

    }
}
