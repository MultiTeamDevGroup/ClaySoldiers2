package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.List;

public class FlowerModifier extends NonStackingCSModifier {

    private MobEffectInstance effect;

    public FlowerModifier(Item modifierItem, String modifierName, Color modifierColor, MobEffectInstance effect, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(CSModifier.ModifierType.EFFECT, modifierItem, modifierName, modifierColor, incompatibleModifiers);
        this.effect = effect;
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
