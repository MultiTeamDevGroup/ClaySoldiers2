package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> CLAY_SOLDIER = Registration.ITEMS.register("clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 0));
    public static final RegistryObject<Item> WHITE_CLAY_SOLDIER = Registration.ITEMS.register("white_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 1));
    public static final RegistryObject<Item> YELLOW_CLAY_SOLDIER = Registration.ITEMS.register("yellow_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 2));
    public static final RegistryObject<Item> RED_CLAY_SOLDIER = Registration.ITEMS.register("red_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 3));
    public static final RegistryObject<Item> PURPLE_CLAY_SOLDIER = Registration.ITEMS.register("purple_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 4));
    public static final RegistryObject<Item> PINK_CLAY_SOLDIER = Registration.ITEMS.register("pink_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 5));
    public static final RegistryObject<Item> ORANGE_CLAY_SOLDIER = Registration.ITEMS.register("orange_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 6));
    public static final RegistryObject<Item> MAGENTA_CLAY_SOLDIER = Registration.ITEMS.register("magenta_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 7));
    public static final RegistryObject<Item> LIME_CLAY_SOLDIER = Registration.ITEMS.register("lime_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 8));
    public static final RegistryObject<Item> LIGHT_GRAY_CLAY_SOLDIER = Registration.ITEMS.register("light_gray_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 9));
    public static final RegistryObject<Item> LIGHT_BLUE_CLAY_SOLDIER = Registration.ITEMS.register("light_blue_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 10));
    public static final RegistryObject<Item> GREEN_CLAY_SOLDIER = Registration.ITEMS.register("green_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 11));
    public static final RegistryObject<Item> GRAY_CLAY_SOLDIER = Registration.ITEMS.register("gray_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 12));
    public static final RegistryObject<Item> CYAN_CLAY_SOLDIER = Registration.ITEMS.register("cyan_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 13));
    public static final RegistryObject<Item> BROWN_CLAY_SOLDIER = Registration.ITEMS.register("brown_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 14));
    public static final RegistryObject<Item> BLUE_CLAY_SOLDIER = Registration.ITEMS.register("blue_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 15));
    public static final RegistryObject<Item> BLACK_CLAY_SOLDIER = Registration.ITEMS.register("black_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 16));
    public static final RegistryObject<Item> CARROT_CLAY_SOLDIER = Registration.ITEMS.register("carrot_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 17));
    public static final RegistryObject<Item> POTATO_CLAY_SOLDIER = Registration.ITEMS.register("potato_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 18));
    public static final RegistryObject<Item> POISONOUS_POTATO_CLAY_SOLDIER = Registration.ITEMS.register("poisonous_potato_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 19));
    public static final RegistryObject<Item> BEETROOT_CLAY_SOLDIER = Registration.ITEMS.register("beetroot_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 20));
    public static final RegistryObject<Item> APPLE_CLAY_SOLDIER = Registration.ITEMS.register("apple_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 21));
    public static final RegistryObject<Item> MELON_CLAY_SOLDIER = Registration.ITEMS.register("melon_clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(16), 22));

    public static final RegistryObject<Item> BRICKED_SOLDIER = Registration.ITEMS.register("bricked_soldier", () -> new Item(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(64)));

    public static final RegistryObject<Item> BASIC_REMOTE = Registration.ITEMS.register("basic_remote", () -> new BasicRemoteItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU).stacksTo(1)));

    public static void register(){}
}
