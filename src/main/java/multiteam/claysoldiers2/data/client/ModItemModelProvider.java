package multiteam.claysoldiers2.data.client;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ClaySoldiers2.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blockitems
        //withExistingParent("cabbage_bush", modLoc("block/cabbage_bush"));


        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        builder(itemGenerated, "icon_soldier_modifier_list");
        builder(itemGenerated, "icon_soldier_info");

        builder(itemGenerated, "basic_remote");

        builder(itemGenerated, "clay_soldier");
        builder(itemGenerated, "white_clay_soldier");
        builder(itemGenerated, "yellow_clay_soldier");
        builder(itemGenerated, "red_clay_soldier");
        builder(itemGenerated, "purple_clay_soldier");
        builder(itemGenerated, "pink_clay_soldier");
        builder(itemGenerated, "orange_clay_soldier");
        builder(itemGenerated, "magenta_clay_soldier");
        builder(itemGenerated, "lime_clay_soldier");
        builder(itemGenerated, "light_gray_clay_soldier");
        builder(itemGenerated, "light_blue_clay_soldier");
        builder(itemGenerated, "green_clay_soldier");
        builder(itemGenerated, "gray_clay_soldier");
        builder(itemGenerated, "cyan_clay_soldier");
        builder(itemGenerated, "brown_clay_soldier");
        builder(itemGenerated, "blue_clay_soldier");
        builder(itemGenerated, "black_clay_soldier");
        builder(itemGenerated, "carrot_clay_soldier");
        builder(itemGenerated, "potato_clay_soldier");
        builder(itemGenerated, "poisonous_potato_clay_soldier");
        builder(itemGenerated, "beetroot_clay_soldier");
        builder(itemGenerated, "apple_clay_soldier");
        builder(itemGenerated, "melon_clay_soldier");

        builder(itemGenerated, "bricked_soldier");


    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
