package multiteam.multicorelibport;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ItemGroupTool extends CreativeModeTab {

    public Supplier<ItemStack> displayStack;

    public ItemGroupTool(String label, Supplier<ItemStack> displayStack) {
        super(label);
        this.displayStack = displayStack;
    }

    @Override
    public ItemStack makeIcon() {
        return displayStack.get();
    }
}
