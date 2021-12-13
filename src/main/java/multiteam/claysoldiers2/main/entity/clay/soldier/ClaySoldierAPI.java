package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

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

    public static Color getSoldierColor(int type){
        Color color;
        switch (type){
            default:
                color = new Color(0x9499a4);
                break;
            case 1:
                color = new Color(0xcccbd8);
                break;
            case 2:
                color = new Color(0xefd817);
                break;
            case 3:
                color = new Color(0xa51d36);
                break;
            case 4:
                color = new Color(0xa453ce);
                break;
            case 5:
                color = new Color(0xeda7cb);
                break;
            case 6:
                color = new Color(0xe69e34);
                break;
            case 7:
                color = new Color(0xdb7ad5);
                break;
            case 8:
                color = new Color(0x83d41c);
                break;
            case 9:
                color = new Color(0xbabac1);
                break;
            case 10:
                color = new Color(0x8fb9f4);
                break;
            case 11:
                color = new Color(0x4a6b18);
                break;
            case 12:
                color = new Color(0x979797);
                break;
            case 13:
                color = new Color(0x3c8eb0);
                break;
            case 14:
                color = new Color(0x995d33);
                break;
            case 15:
                color = new Color(0x345ec3);
                break;
            case 16:
                color = new Color(0x383751);
                break;
            case 17:
                color = new Color(0xff8e09);
                break;
            case 18:
                color = new Color(0xd9aa51);
                break;
            case 19:
                color = new Color(0x6b863e);
                break;
            case 20:
                color = new Color(0xa4272c);
                break;
            case 21:
                color = new Color(0xdd1725);
                break;
            case 22:
                color = new Color(0x848920);
                break;
        }
        return color;
    }

    public static String getSoldierMaterial(int type){
        String material;
        switch (type){
            default:
                material = "clay";
                break;
            case 1:
                material = "white clay";
                break;
            case 2:
                material = "yellow clay";
                break;
            case 3:
                material = "red clay";
                break;
            case 4:
                material = "purple clay";
                break;
            case 5:
                material = "pink clay";
                break;
            case 6:
                material = "orange clay";
                break;
            case 7:
                material = "magenta clay";
                break;
            case 8:
                material = "lime clay";
                break;
            case 9:
                material = "light gray clay";
                break;
            case 10:
                material = "light blue clay";
                break;
            case 11:
                material = "green clay";
                break;
            case 12:
                material = "gray clay";
                break;
            case 13:
                material = "cyan clay";
                break;
            case 14:
                material = "brown clay";
                break;
            case 15:
                material = "blue clay";
                break;
            case 16:
                material = "black clay";
                break;
            case 17:
                material = "carrot";
                break;
            case 18:
                material = "potato";
                break;
            case 19:
                material = "poisonous potato";
                break;
            case 20:
                material = "beetroot";
                break;
            case 21:
                material = "apple";
                break;
            case 22:
                material = "melon";
                break;
        }
        return material;
    }
}
