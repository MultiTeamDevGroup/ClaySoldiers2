package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.List;

public class RedstoneModifier extends CSModifier {

    public RedstoneModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, boolean canBeStacked, int stackingLimit, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, canBeStacked, stackingLimit, incompatibleModifiers);
    }

    @Override
    public void onModifierAttack(Entity targetSoldier) {

    }

    @Override
    public void onModifierHurt(ClaySoldierEntity thisSoldier, ClaySoldierEntity attackerSoldier) {

    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier) {

    }
}
