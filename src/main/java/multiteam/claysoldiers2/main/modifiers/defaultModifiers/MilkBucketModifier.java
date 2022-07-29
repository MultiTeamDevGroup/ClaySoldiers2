package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MilkBucketModifier extends NonStackingCSModifier {

    public MilkBucketModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierAdded(ClaySoldier thisSoldier, Instance thisModifierInstance) {
        List<ItemStack> dropsList = new ArrayList<>();

        for (Instance instance : thisSoldier.getModifiers()){
            if(instance != null && instance != thisModifierInstance){
                int amount;
                if(instance.getModifier().canBeStacked()){
                    amount = instance.getAmount();
                }else{
                    amount = 1;
                }
                dropsList.add(new ItemStack(instance.getModifier().getModifierItem(), amount));
            }
        }

        dropsList.add(new ItemStack(Items.BUCKET));

        for (ItemStack stack : dropsList){
            thisSoldier.getLevel().addFreshEntity(new ItemEntity(thisSoldier.getLevel(), thisSoldier.getX(), thisSoldier.getY(), thisSoldier.getZ(), stack));
        }

        thisSoldier.getModifiers().clear();
        thisSoldier.removeSoldier();
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

    }
}
