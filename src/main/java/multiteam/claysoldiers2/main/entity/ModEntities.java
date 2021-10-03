package multiteam.claysoldiers2.main.entity;

import multiteam.claysoldiers2.ClaySoldiers2;
import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierEntity;
import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierRenderer;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.multicore_lib.setup.utilities.RegistrationTool;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = ClaySoldiers2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntities {

    public static final RegistryObject<EntityType<ClaySoldierEntity>> CLAY_SOLDIER = buildEntity("clay_soldier", ClaySoldierEntity::new, MathF.BlockToFloatScale(4), MathF.BlockToFloatScale(8), MobCategory.CREATURE, 8, 3); //8, 3 is default


    @SubscribeEvent
    public static void clientSetup(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CLAY_SOLDIER.get(), ClaySoldierRenderer::new);
    }

    public static void applyAttributes(EntityAttributeCreationEvent event){
        event.put(CLAY_SOLDIER.get(), ClaySoldierEntity.createAttributes().build());
    }


    public static void register() { }

    //Not yet using the buildEntityV2 from MultiCoreLib since its kinda broken atm.
    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, MobCategory classification, int trackingRange, int updateinterval) {
        return Registration.ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, classification).sized(width, height).clientTrackingRange(trackingRange).updateInterval(updateinterval).build(name));
    }
}
