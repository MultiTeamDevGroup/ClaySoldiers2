package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ClaySoldierAPI {

    public enum ClaySoldierMaterial{
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

        private RegistryObject<Item> itemForm;
        private Color materialColor;
        private String materialName;

        ClaySoldierMaterial(RegistryObject<Item> itemForm, Color materialColor, String materialName) {
            this.itemForm = itemForm;
            this.materialColor = materialColor;
            this.materialName = materialName;
        }

        public Item getItemForm(){
            return this.itemForm.get();
        }
        public Color getMaterialColor(){
            return this.materialColor;
        }
        public String getMaterialName(){
            return this.materialName;
        }
    }

    public enum ClaySoldierModifierType{
        MAIN_HAND, //occupies the main hand
        MAIN_HAND_BOOST_ITEM, //occupies the main hand, while having a single use per item
        MAIN_HAND_AMOUNT_BOOST_ITEM, //occupies the main hand, while having an amount of uses per item
        OFF_HAND, //occupies the offhand
        OFF_HAND_BOOST_ITEM, //occupies the offhand, while having a single use per item
        OFF_HAND_INF_BOOST_COMBINED, //occupies the offhand, and applies an effect as long as its combined with another modifier
        ANY_HAND_BOOST_ITEM, //can be equipped in any hand, while having a single use per item
        ANY_HAND_AMOUNT_BOOST_ITEM, //occupies any hand, while having an amount of uses per item
        BOTH_HANDS, //occupies both hands
        BOOST_ITEM, //has a single use per item
        INF_BOOST, //applies an effect infinitely
        INF_BOOST_COSMETIC, //applies a cosmetic only effect infinitely
        INF_BOOST_COMBINED, //applies an effect as long as its combined with another modifier
        EFFECT, //applies effect for a period of time
        INF_EFFECT, //applies status effect infinitely
        CANCEL //cancels any modifier on the soldier
    }

    public enum ClaySoldierModifier{
        BLAZEROD_MAIN(
                ClaySoldierModifierType.MAIN_HAND,
                Items.BLAZE_ROD,
                "damage_boost_fire_aspect",
                new Color(0xff8000)
        ),
        BOWL_SECOND(
                ClaySoldierModifierType.OFF_HAND,
                Items.BOWL,
                "shield",
                new Color(0x4d2515)
        ),
        BRICK_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.BRICK,
                "heaviness",
                new Color(0xa63b26)
        ),
        CLAY_HEAL_SECOND(
                ClaySoldierModifierType.OFF_HAND_BOOST_ITEM,
                Items.CLAY_BALL,
                "clay_healer",
                new Color(0x9499a4)
        ),
        FEATHER_BOOST(
                ClaySoldierModifierType.INF_EFFECT,
                Items.FEATHER,
                "slow_falling",
                new Color(0xe6edf5)
        ),
        FLOWER_BOOST(
                ClaySoldierModifierType.EFFECT,
                Items.POPPY,
                "suspicious_effect",
                new Color(0x27E25C)
        ),
        GLISTERING_HEAL_SECOND(
                ClaySoldierModifierType.OFF_HAND_BOOST_ITEM,
                Items.GLISTERING_MELON_SLICE,
                "glistering_healer",
                new Color(0xFFF97E)
        ),
        KING_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.GOLD_NUGGET,
                "king",
                new Color(0xFFB700)
        ),
        GRAVEL_MAIN(
                ClaySoldierModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
                Items.GRAVEL,
                "gravel_ranged",
                new Color(0x3B3C3D)
        ),
        LEATHER_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.LEATHER,
                "leather_armor",
                new Color(0x703923)
        ),
        MAGMA_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.MAGMA_CREAM,
                "critical_damage",
                new Color(0xFF6200)
        ),
        PAPER_BOOST(
                ClaySoldierModifierType.INF_BOOST_COSMETIC,
                Items.PAPER,
                "cape",
                new Color(0xDEE1E8)
        ),
        RABBIT_HIDE_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.RABBIT_HIDE,
                "rabbit_armor",
                new Color(0x9B734A)
        ),
        SCUTE_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.SCUTE,
                "diver",
                new Color(0x0DBD41)
        ),
        SEEDS_MAIN(
                ClaySoldierModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
                Items.WHEAT_SEEDS,
                "seeds_ranged",
                new Color(0x30812A)
        ),
        STICK_MAIN(
                ClaySoldierModifierType.MAIN_HAND,
                Items.STICK,
                "damage_boost",
                new Color(0x5D350E)
        ),
        WOOL_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.WHITE_WOOL,
                "knockback_resistance",
                new Color(0xFFFFFF)
        ),
        FLINT_BOOST(
                ClaySoldierModifierType.INF_BOOST_COMBINED,
                Items.FLINT,
                "flint_damage_boost",
                new Color(0x626262)
        ),
        IRON_BOOST(
                ClaySoldierModifierType.INF_BOOST_COMBINED,
                Items.IRON_NUGGET,
                "iron_damage_boost",
                new Color(0xAFAFAF)
        ),
        REDSTONE_ANY(
                ClaySoldierModifierType.ANY_HAND_BOOST_ITEM,
                Items.REDSTONE,
                "blinding",
                new Color(0xFF0000)
        ),
        SLIME_ANY(
                ClaySoldierModifierType.ANY_HAND_BOOST_ITEM,
                Items.SLIME_BALL,
                "sticker",
                new Color(0x69AF11)
        ),
        GLOWSTONE_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.GLOWSTONE_DUST,
                "glowing",
                new Color(0xE5BE5F)
        ),
        MILK(
                ClaySoldierModifierType.CANCEL,
                Items.MILK_BUCKET,
                "cancel",
                new Color(0xE9EEF1)
        ),
        GUNPOWDER_BOOST(
                ClaySoldierModifierType.BOOST_ITEM,
                Items.GUNPOWDER,
                "explosive",
                new Color(0x5D5A5A)
        ),
        COAL_BOOST(
                ClaySoldierModifierType.OFF_HAND_INF_BOOST_COMBINED,
                Items.COAL,
                "fiery_fuel",
                new Color(0x3A3A3A)
        ),
        LILYPAD_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.LILY_PAD,
                "water_walker",
                new Color(0x355D03)
        ),
        GLASS_PANE_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.GLASS_PANE,
                "glasses",
                new Color(0xFFFFFF)
        ),
        DIAMOND_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.DIAMOND,
                "queen",
                new Color(0x0EC9AF)
        ),
        SHEAR_BOTH(
                ClaySoldierModifierType.BOTH_HANDS,
                Items.SHEARS,
                "scissor_hands",
                new Color(0xC5BDBB)
        ),
        ENDEREYE_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.ENDER_EYE,
                "allseeing_sight",
                new Color(0xff8000)
        ),
        SUGAR_BOOST(
                ClaySoldierModifierType.INF_EFFECT,
                Items.SUGAR,
                "speedey",
                new Color(0xECEEEF)
        ),
        EGG_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.EGG,
                "egg_cover",
                new Color(0xD9BCA3)
        ),
        FIRECHARGE_MAIN(
                ClaySoldierModifierType.MAIN_HAND_BOOST_ITEM,
                Items.FIRE_CHARGE,
                "fiery_hands",
                new Color(0x9D3907)
        ),
        WART_BOOST(
                ClaySoldierModifierType.INF_BOOST,
                Items.NETHER_WART,
                "crazy_warted",
                new Color(0xa62530)
        ),
        MUSHROOM_ANY(
                ClaySoldierModifierType.ANY_HAND_AMOUNT_BOOST_ITEM,
                Items.RED_MUSHROOM,
                "shroomer",
                new Color(0xFFFFFF)
        );


        ClaySoldierModifier(ClaySoldierModifierType modifierType, Item modifierItem, String modifierName, Color modifierColor) {

        }
    }

}

