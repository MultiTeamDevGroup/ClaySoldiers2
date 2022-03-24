package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ICON_ITEM_SOLDIER_INFORMATIONS = Registration.ITEMS.register("icon_soldier_info", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ICON_ITEM_SOLDIER_MODIFIERS = Registration.ITEMS.register("icon_soldier_modifier_list", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RENDERING_DISPLAY_HALF_SHEAR = Registration.ITEMS.register("half_shear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RENDERING_DISPLAY_MINI_SHIELD = Registration.ITEMS.register("soldier_shield_model", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RENDERING_DISPLAY_SLIME_SPLOTCH = Registration.ITEMS.register("slime_footing", () -> new Item(new Item.Properties()));



    public static final RegistryObject<Item> CLAY_SOLDIER = Registration.ITEMS.register("clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.CLAY_SOLDIER));
    public static final RegistryObject<Item> WHITE_CLAY_SOLDIER = Registration.ITEMS.register("white_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.WHITE_CLAY_SOLDIER));
    public static final RegistryObject<Item> YELLOW_CLAY_SOLDIER = Registration.ITEMS.register("yellow_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.YELLOW_CLAY_SOLDIER));
    public static final RegistryObject<Item> RED_CLAY_SOLDIER = Registration.ITEMS.register("red_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.RED_CLAY_SOLDIER));
    public static final RegistryObject<Item> PURPLE_CLAY_SOLDIER = Registration.ITEMS.register("purple_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.PURPLE_CLAY_SOLDIER));
    public static final RegistryObject<Item> PINK_CLAY_SOLDIER = Registration.ITEMS.register("pink_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.PINK_CLAY_SOLDIER));
    public static final RegistryObject<Item> ORANGE_CLAY_SOLDIER = Registration.ITEMS.register("orange_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.ORANGE_CLAY_SOLDIER));
    public static final RegistryObject<Item> MAGENTA_CLAY_SOLDIER = Registration.ITEMS.register("magenta_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.MAGENTA_CLAY_SOLDIER));
    public static final RegistryObject<Item> LIME_CLAY_SOLDIER = Registration.ITEMS.register("lime_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.LIME_CLAY_SOLDIER));
    public static final RegistryObject<Item> LIGHT_GRAY_CLAY_SOLDIER = Registration.ITEMS.register("light_gray_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.LIGHT_GRAY_CLAY_SOLDIER));
    public static final RegistryObject<Item> LIGHT_BLUE_CLAY_SOLDIER = Registration.ITEMS.register("light_blue_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.LIGHT_BLUE_CLAY_SOLDIER));
    public static final RegistryObject<Item> GREEN_CLAY_SOLDIER = Registration.ITEMS.register("green_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.GREEN_CLAY_SOLDIER));
    public static final RegistryObject<Item> GRAY_CLAY_SOLDIER = Registration.ITEMS.register("gray_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.GRAY_CLAY_SOLDIER));
    public static final RegistryObject<Item> CYAN_CLAY_SOLDIER = Registration.ITEMS.register("cyan_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.CYAN_CLAY_SOLDIER));
    public static final RegistryObject<Item> BROWN_CLAY_SOLDIER = Registration.ITEMS.register("brown_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.BROWN_CLAY_SOLDIER));
    public static final RegistryObject<Item> BLUE_CLAY_SOLDIER = Registration.ITEMS.register("blue_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.BLUE_CLAY_SOLDIER));
    public static final RegistryObject<Item> BLACK_CLAY_SOLDIER = Registration.ITEMS.register("black_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.BLACK_CLAY_SOLDIER));
    public static final RegistryObject<Item> CARROT_CLAY_SOLDIER = Registration.ITEMS.register("carrot_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.CARROT_CLAY_SOLDIER));
    public static final RegistryObject<Item> POTATO_CLAY_SOLDIER = Registration.ITEMS.register("potato_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.POTATO_CLAY_SOLDIER));
    public static final RegistryObject<Item> POISONOUS_POTATO_CLAY_SOLDIER = Registration.ITEMS.register("poisonous_potato_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.POISONOUS_POTATO_CLAY_SOLDIER));
    public static final RegistryObject<Item> BEETROOT_CLAY_SOLDIER = Registration.ITEMS.register("beetroot_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.BEETROOT_CLAY_SOLDIER));
    public static final RegistryObject<Item> APPLE_CLAY_SOLDIER = Registration.ITEMS.register("apple_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.APPLE_CLAY_SOLDIER));
    public static final RegistryObject<Item> MELON_CLAY_SOLDIER = Registration.ITEMS.register("melon_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), CSAPI.ClaySoldierMaterial.MELON_CLAY_SOLDIER));

    public static final RegistryObject<Item> BRICKED_SOLDIER = Registration.ITEMS.register("bricked_soldier", () -> new Item(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(64)));

    public static final RegistryObject<Item> BASIC_REMOTE = Registration.ITEMS.register("basic_remote", () -> new BasicRemoteItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(1)));

    public static void register() {
    }
}
