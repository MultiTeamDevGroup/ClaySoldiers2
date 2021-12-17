package multiteam.claysoldiers2.main;

import com.mojang.datafixers.util.Either;
import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierAPI;
import multiteam.claysoldiers2.main.item.ClaySoldierItem;
import multiteam.multicore_lib.MultiCoreLib;
import multiteam.multicore_lib.setup.utilities.render.tooltip.itemtextcomp.ItemWithTextTooltipComponent;
import net.minecraft.nbt.CompoundTag;
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
        System.out.println("lets start adding tooltip images");
        if (event.getItemStack().getItem() instanceof ClaySoldierItem) {
            CompoundTag tag = event.getItemStack().getTag();
            System.out.println(tag + " trying still. one step further");
            if(tag != null){
                System.out.println("k tag wasnt null. PROCEED!");
                if(tag.getIntArray("Modifiers").length > 0){
                System.out.println("very nice, modifiers list actually contains modifiers");
                    List<ClaySoldierAPI.ClaySoldierModifier> modifiers = new ArrayList<>();
                    for (int i = 0; i < tag.getIntArray("Modifiers").length; i++){
                        modifiers.add(ClaySoldierAPI.ClaySoldierModifier.values()[tag.getIntArray("Modifiers")[i]]);
                    }

                    List<ItemWithTextTooltipComponent.ItemTextCompoundRow> rowList = new ArrayList<>();;
                    for (ClaySoldierAPI.ClaySoldierModifier modifier: modifiers) {
                        rowList.add(new ItemWithTextTooltipComponent.ItemTextCompoundRow(List.of(
                                new ItemStack(modifier.getModifierItem())
                        ), (TranslatableComponent) new TranslatableComponent("tooltip."+ ClaySoldiers2.MOD_ID + ".clay_soldier_item_attributes.modifier." + modifier.getModifierName()).withStyle(Style.EMPTY.withColor(modifier.getModifierColor().getRGB()))));
                    }

                    event.getTooltipElements().add(Either.right(new ItemWithTextTooltipComponent(rowList)));
                }
            }
        }
    }
}
