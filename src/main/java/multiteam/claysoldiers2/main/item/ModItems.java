package multiteam.claysoldiers2.main.item;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> CLAY_SOLDIER = Registration.ITEMS.register("clay_soldier", () -> new ClaySoldierItem(new Item.Properties().tab(ClaySoldiers2.CLAY_MENU)));

    public static void register(){}
}
