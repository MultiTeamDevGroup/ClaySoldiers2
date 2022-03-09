package multiteam.claysoldiers2.main.modifiers.modifier;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.List;

public abstract class OnlyOneLifeTimeLongCSModifier extends NonStackingCSModifier{

    public OnlyOneLifeTimeLongCSModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldierEntity thisSoldier, Instance thisModifierInstance) {
        thisSoldier.removeModifier(thisModifierInstance);
    }
}
