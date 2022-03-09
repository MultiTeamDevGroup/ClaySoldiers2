package multiteam.claysoldiers2.main.modifiers.modifier;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldierEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class InfEffectCSModifier extends NonStackingCSModifier{

    public final MobEffectInstance effectInstance;

    public InfEffectCSModifier(MobEffectInstance effectInstance, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(ModifierType.INF_EFFECT, modifierItem, modifierName, modifierColor, incompatibleModifiers);
        this.effectInstance = effectInstance;
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
        thisSoldier.addEffect(this.effectInstance);
    }
}
