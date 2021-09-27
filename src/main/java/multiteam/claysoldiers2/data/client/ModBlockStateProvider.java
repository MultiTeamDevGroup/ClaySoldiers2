package multiteam.claysoldiers2.data.client;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ClaySoldiers2.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //simpleBlock(ModBlocks.SILKY_CLOTH_BLOCK.get());
    }
}
