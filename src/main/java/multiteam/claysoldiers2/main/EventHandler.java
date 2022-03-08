package multiteam.claysoldiers2.main;

import com.mojang.datafixers.util.Either;
import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.item.ClaySoldierItem;
import multiteam.claysoldiers2.main.util.ItemAttributeUtils;
import multiteam.multicore_lib.setup.utilities.render.tooltip.itemtextcomp.ItemWithTextTooltipComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ClaySoldiers2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void gatherTooltips(RenderTooltipEvent.GatherComponents event) {
        if (event.getItemStack().getItem() instanceof ClaySoldierItem) {
            List<CSModifier.Instance> modifiers1 = ItemAttributeUtils.getModifiers(event.getItemStack());
            List<ItemWithTextTooltipComponent.ItemTextCompoundRow> rowList = new ArrayList<>();

            for (CSModifier.Instance instance : modifiers1) {
                ItemStack stack = new ItemStack(instance.getModifier().getModifierItem());
                stack.setCount(instance.getAmount());
                rowList.add(new ItemWithTextTooltipComponent.ItemTextCompoundRow(
                        List.of(stack),
                        (TranslatableComponent) new TranslatableComponent(instance.getModifier().getDescriptionId())
                                .withStyle(Style.EMPTY.withColor(instance.getModifier().getModifierColor().getRGB()))
                ));
            }

            event.getTooltipElements().add(Either.right(new ItemWithTextTooltipComponent(rowList)));
        }
    }
}
