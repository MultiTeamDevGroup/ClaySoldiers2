package multiteam.claysoldiers2;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.ModEntities;
import multiteam.claysoldiers2.main.networking.Networking;
import multiteam.multicore_lib.setup.utilities.generic.ItemGroupTool;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(ClaySoldiers2.MOD_ID)
public class ClaySoldiers2 {
    public static final String MOD_ID = "claysoldiers2";
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroupTool CLAY_MENU = new ItemGroupTool(MOD_ID + "_creative_tab", () -> new ItemStack(Items.CLAY_BALL));

    public ClaySoldiers2() {
        GeckoLib.initialize();
        Registration.register();

        Networking.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onNewRegistry);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEntities::applyAttributes);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation res(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

//    /**
//     * Event handler for creating new registries.
//     *
//     * @param event new registry event
//     */
//    private void onNewRegistry(final RegistryEvent.NewRegistry event) {
//        ModClaySoldierModifiers.makeRegistry();
//    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
