package multiteam.claysoldiers2.data;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.data.client.ModBlockStateProvider;
import multiteam.claysoldiers2.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClaySoldiers2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(output, existingFileHelper));
    }
}
