package multiteam.claysoldiers2.main.modifiers;

import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CSAPI {

    public static class Settings {
        public static int stackablesLimit = 32;
        public static int flowerStackLimit = 5;
        public static int soldierViewDistance = 32;
    }

    public enum ClaySoldierMaterial {
        CLAY_SOLDIER(
                ModItems.CLAY_SOLDIER,
                new Color(0x9499a4),
                "clay"
        ),
        WHITE_CLAY_SOLDIER(
                ModItems.WHITE_CLAY_SOLDIER,
                new Color(0xcccbd8),
                "white clay"
        ),
        YELLOW_CLAY_SOLDIER(
                ModItems.YELLOW_CLAY_SOLDIER,
                new Color(0xefd817),
                "yellow clay"
        ),
        RED_CLAY_SOLDIER(
                ModItems.RED_CLAY_SOLDIER,
                new Color(0xa51d36),
                "red clay"
        ),
        PURPLE_CLAY_SOLDIER(
                ModItems.PURPLE_CLAY_SOLDIER,
                new Color(0xa453ce),
                "purple clay"
        ),
        PINK_CLAY_SOLDIER(
                ModItems.PINK_CLAY_SOLDIER,
                new Color(0xeda7cb),
                "pink clay"
        ),
        ORANGE_CLAY_SOLDIER(
                ModItems.ORANGE_CLAY_SOLDIER,
                new Color(0xe69e34),
                "orange clay"
        ),
        MAGENTA_CLAY_SOLDIER(
                ModItems.MAGENTA_CLAY_SOLDIER,
                new Color(0xdb7ad5),
                "magenta clay"
        ),
        LIME_CLAY_SOLDIER(
                ModItems.LIME_CLAY_SOLDIER,
                new Color(0x83d41c),
                "lime clay"
        ),
        LIGHT_GRAY_CLAY_SOLDIER(
                ModItems.LIGHT_GRAY_CLAY_SOLDIER,
                new Color(0xbabac1),
                "light gray clay"
        ),
        LIGHT_BLUE_CLAY_SOLDIER(
                ModItems.LIGHT_BLUE_CLAY_SOLDIER,
                new Color(0x8fb9f4),
                "light blue clay"
        ),
        GREEN_CLAY_SOLDIER(
                ModItems.GREEN_CLAY_SOLDIER,
                new Color(0x4a6b18),
                "green clay"
        ),
        GRAY_CLAY_SOLDIER(
                ModItems.GRAY_CLAY_SOLDIER,
                new Color(0x979797),
                "gray clay"
        ),
        CYAN_CLAY_SOLDIER(
                ModItems.CYAN_CLAY_SOLDIER,
                new Color(0x3c8eb0),
                "cyan clay"
        ),
        BROWN_CLAY_SOLDIER(
                ModItems.BROWN_CLAY_SOLDIER,
                new Color(0x995d33),
                "brown clay"
        ),
        BLUE_CLAY_SOLDIER(
                ModItems.BLUE_CLAY_SOLDIER,
                new Color(0x345ec3),
                "blue clay"
        ),
        BLACK_CLAY_SOLDIER(
                ModItems.BLACK_CLAY_SOLDIER,
                new Color(0x383751),
                "black clay"
        ),
        CARROT_CLAY_SOLDIER(
                ModItems.CARROT_CLAY_SOLDIER,
                new Color(0xff8e09),
                "carrot"
        ),
        POTATO_CLAY_SOLDIER(
                ModItems.POTATO_CLAY_SOLDIER,
                new Color(0xd9aa51),
                "potato"
        ),
        POISONOUS_POTATO_CLAY_SOLDIER(
                ModItems.POISONOUS_POTATO_CLAY_SOLDIER,
                new Color(0x6b863e),
                "poisonous potato"
        ),
        BEETROOT_CLAY_SOLDIER(
                ModItems.BEETROOT_CLAY_SOLDIER,
                new Color(0xa4272c),
                "beetroot"
        ),
        APPLE_CLAY_SOLDIER(
                ModItems.APPLE_CLAY_SOLDIER,
                new Color(0xdd1725),
                "apple"
        ),
        MELON_CLAY_SOLDIER(
                ModItems.MELON_CLAY_SOLDIER,
                new Color(0x848920),
                "melon"
        );

        private final RegistryObject<Item> itemForm;
        private final Color materialColor;
        private final String materialName;

        ClaySoldierMaterial(RegistryObject<Item> itemForm, Color materialColor, String materialName) {
            this.itemForm = itemForm;
            this.materialColor = materialColor;
            this.materialName = materialName;
        }

        public Item getItemForm() {
            return this.itemForm.get();
        }

        public Color getMaterialColor() {
            return this.materialColor;
        }

        public String getMaterialName() {
            return this.materialName;
        }
    }

}

