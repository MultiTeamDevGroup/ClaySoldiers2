package multiteam.claysoldiers2.main;

import com.mojang.datafixers.util.Either;
import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierModifier;
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
            List<ClaySoldierModifier.Instance> modifiers1 = ItemAttributeUtils.getModifiers(event.getItemStack());
            List<ItemWithTextTooltipComponent.ItemTextCompoundRow> rowList = new ArrayList<>();

            for (ClaySoldierModifier.Instance instance : modifiers1) {
                ItemStack stack = new ItemStack(instance.getModifier().getModifierItem());
                stack.setCount(instance.getAmount());
                rowList.add(new ItemWithTextTooltipComponent.ItemTextCompoundRow(
                        List.of(stack),
                        (TranslatableComponent) new TranslatableComponent(instance.getModifier().getDescriptionId())
                                .withStyle(Style.EMPTY.withColor(instance.getModifier().getModifierColor().getRGB()))
                ));
            }

            event.getTooltipElements().add(Either.right(new ItemWithTextTooltipComponent(rowList)));

//            CompoundTag tag = event.getItemStack().getTag();
//            if (tag != null) {
//                if (tag.getIntArray("Modifiers").length > 0) {
//                    List<Pair<ClaySoldierModifier, Integer>> modifiers = new ArrayList<>();
//                    for (int i = 0; i < tag.getIntArray("Modifiers").length; i++) {
//                        modifiers.add(new Pair<>(ClaySoldierModifier.values()[tag.getIntArray("Modifiers")[i]], tag.getIntArray("ModifiersAmounts")[i]));
//                    }
//
//                    List<ItemWithTextTooltipComponent.ItemTextCompoundRow> rowList = new ArrayList<>();
//                    for (Pair<ClaySoldierModifier, Integer> modifier : modifiers) {
//                        ItemStack stack = new ItemStack(modifier.getA().getModifierItem());
//                        stack.setCount(modifier.getB());
//                        rowList.add(new ItemWithTextTooltipComponent.ItemTextCompoundRow(List.of(
//                                stack
//                        ), (TranslatableComponent) new TranslatableComponent("tooltip." + ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.modifier." + modifier.getA().getModifierName()).withStyle(Style.EMPTY.withColor(modifier.getA().getModifierColor().getRGB()))));
//                    }
//
//                    event.getTooltipElements().add(Either.right(new ItemWithTextTooltipComponent(rowList)));
//                }
//            }
        }
    }
}
