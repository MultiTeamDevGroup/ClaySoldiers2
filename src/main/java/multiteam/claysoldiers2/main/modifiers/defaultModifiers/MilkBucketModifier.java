package multiteam.claysoldiers2.main.modifiers.defaultModifiers;

import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.NonStackingCSModifier;
import net.minecraft.world.item.Item;

import java.awt.*;
import java.util.List;

public class MilkBucketModifier extends NonStackingCSModifier {

    public MilkBucketModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<CSModifier> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, incompatibleModifiers);
    }

    @Override
    public void onModifierAttack(ClaySoldierEntity targetSoldier) {

    }

    @Override
    public void onModifierHurt(ClaySoldierEntity thisSoldier, ClaySoldierEntity attackerSoldier) {

    }

    @Override
    public void onModifierTick(ClaySoldierEntity thisSoldier) {

    }

    /*
    (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    for (int i = 0; i < soldier.getModifiers().size(); i++) {
                        if (soldier.getModifiers().get(i) != null && soldier.getModifiers().get(i).getA().getModifierItem() != Items.MILK_BUCKET) {
                            ItemStack dropStack = new ItemStack(soldier.getModifiers().get(i).getA().getModifierItem());
                            if (soldier.getModifiers().get(i).getA().canBeStacked()) {
                                dropStack.setCount(soldier.getModifiers().get(i).getB());
                            }
                            soldier.getLevel().addFreshEntity(new ItemEntity(soldier.level, soldier.getX(), soldier.getY(), soldier.getZ(), dropStack));
                            soldier.removeModifier(soldier.getModifiers().get(i).getA());
                        } else {
                            return;
                        }
                    }

                    soldier.removeAllModifiers();
                    soldier.getLevel().addFreshEntity(new ItemEntity(soldier.level, soldier.getX(), soldier.getY(), soldier.getZ(), new ItemStack(Items.BUCKET)));
                    soldier.removeSoldier();
                }
            },
     */
}
