package multiteam.claysoldiers2.main;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ClaySoldiers2.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClaySoldiers2.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ClaySoldiers2.MOD_ID);


    public static void register(){
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modeEventBus);
        ITEMS.register(modeEventBus);
        ENTITY_TYPES.register(modeEventBus);

        ModItems.register();
        //ModBlocks.register();
        //ModEntities.register();
    }

}
