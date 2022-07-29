package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.DamageBonusCSModifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.List;

public class ShearsModifier extends DamageBonusCSModifier {

    boolean canUseShears = false;

    public ShearsModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public float getDamageBonus() {
        return canUseShears ? 5 : 0;
    }

    @Override
    public void onModifierAdded(ClaySoldier thisSoldier, Instance thisModifierInstance) {
        thisSoldier.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.RENDERING_DISPLAY_HALF_SHEAR.get()));
        thisSoldier.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(ModItems.RENDERING_DISPLAY_HALF_SHEAR.get()));
    }

    @Override
    public void onModifierAttack(ClaySoldier thisSoldier, Entity targetEntity, Instance thisModifierInstance) {

    }

    @Override
    public Pair<DamageSource, Float> onModifierHurt(ClaySoldier thisSoldier, DamageSource damageSource, float damageAmount, Instance thisModifierInstance) {
        return new Pair<>(damageSource, damageAmount);
    }

    @Override
    public void onModifierDeath(DamageSource damageSource, ClaySoldier thisSoldier, Instance thisModifierInstance) {

    }

    @Override
    public void onModifierTick(ClaySoldier thisSoldier, Instance thisModifierInstance) {
        this.canUseShears = thisSoldier.getMainHandItem().is(ModItems.RENDERING_DISPLAY_HALF_SHEAR.get()) && thisSoldier.getOffhandItem().is(ModItems.RENDERING_DISPLAY_HALF_SHEAR.get());
    }
}
