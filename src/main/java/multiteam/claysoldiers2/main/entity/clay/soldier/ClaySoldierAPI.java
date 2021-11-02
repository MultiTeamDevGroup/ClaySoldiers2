package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.world.item.ItemStack;

public class ClaySoldierAPI {

    public static ItemStack getItemForm(ClaySoldierEntity entity){
        ItemStack stack;
        switch (entity.getVariant()){
            default:
                stack = new ItemStack(ModItems.CLAY_SOLDIER.get());
                break;
            case 1:
                stack = new ItemStack(ModItems.WHITE_CLAY_SOLDIER.get());
                break;
            case 2:
                stack = new ItemStack(ModItems.YELLOW_CLAY_SOLDIER.get());
                break;
            case 3:
                stack = new ItemStack(ModItems.RED_CLAY_SOLDIER.get());
                break;
            case 4:
                stack = new ItemStack(ModItems.PURPLE_CLAY_SOLDIER.get());
                break;
            case 5:
                stack = new ItemStack(ModItems.PINK_CLAY_SOLDIER.get());
                break;
            case 6:
                stack = new ItemStack(ModItems.ORANGE_CLAY_SOLDIER.get());
                break;
            case 7:
                stack = new ItemStack(ModItems.MAGENTA_CLAY_SOLDIER.get());
                break;
            case 8:
                stack = new ItemStack(ModItems.LIME_CLAY_SOLDIER.get());
                break;
            case 9:
                stack = new ItemStack(ModItems.LIGHT_GRAY_CLAY_SOLDIER.get());
                break;
            case 10:
                stack = new ItemStack(ModItems.LIGHT_BLUE_CLAY_SOLDIER.get());
                break;
            case 11:
                stack = new ItemStack(ModItems.GREEN_CLAY_SOLDIER.get());
                break;
            case 12:
                stack = new ItemStack(ModItems.GRAY_CLAY_SOLDIER.get());
                break;
            case 13:
                stack = new ItemStack(ModItems.CYAN_CLAY_SOLDIER.get());
                break;
            case 14:
                stack = new ItemStack(ModItems.BROWN_CLAY_SOLDIER.get());
                break;
            case 15:
                stack = new ItemStack(ModItems.BLUE_CLAY_SOLDIER.get());
                break;
            case 16:
                stack = new ItemStack(ModItems.BLACK_CLAY_SOLDIER.get());
                break;
            case 17:
                stack = new ItemStack(ModItems.CARROT_CLAY_SOLDIER.get());
                break;
            case 18:
                stack = new ItemStack(ModItems.POTATO_CLAY_SOLDIER.get());
                break;
            case 19:
                stack = new ItemStack(ModItems.POISONOUS_POTATO_CLAY_SOLDIER.get());
                break;
            case 20:
                stack = new ItemStack(ModItems.BEETROOT_CLAY_SOLDIER.get());
                break;
            case 21:
                stack = new ItemStack(ModItems.APPLE_CLAY_SOLDIER.get());
                break;
            case 22:
                stack = new ItemStack(ModItems.MELON_CLAY_SOLDIER.get());
                break;
        }
        return stack;
    }
}
