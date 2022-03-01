package multiteam.claysoldiers2.main.util;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierModifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ItemAttributeUtils {
    private ItemAttributeUtils() {
        throw ExceptionUtils.utilityClassException();
    }

    public static @NotNull List<ClaySoldierModifier.Instance> getModifiers(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return Collections.emptyList();

        ListTag modifiers = tag.getList("Modifiers", Tag.TAG_COMPOUND);
        List<ClaySoldierModifier.Instance> output = new ArrayList<>();
        for (Tag modifierTag : modifiers) {
            if (modifierTag instanceof CompoundTag compoundTag) {
                ResourceLocation type = new ResourceLocation(compoundTag.getString("Type"));
                ClaySoldierModifier modifier = Registration.getModifierRegistry().getValue(type);
                int amount = compoundTag.getInt("Amount");

                output.add(new ClaySoldierModifier.Instance(modifier, amount));
            }
        }

        return Collections.unmodifiableList(output);
    }
}
