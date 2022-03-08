package multiteam.claysoldiers2.main.modifiers.modifier;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.List;

public abstract class NonStackingCSModifier extends CSModifier {

    public NonStackingCSModifier(ModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor, List<RegistryObject<CSModifier>> incompatibleModifiers) {
        super(modifierType, modifierItem, modifierName, modifierColor, false, 0, incompatibleModifiers);
    }
}
