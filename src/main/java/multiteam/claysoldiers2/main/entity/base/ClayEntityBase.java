package multiteam.claysoldiers2.main.entity.base;

import multiteam.claysoldiers2.main.entity.claysoldier.ClaySoldier;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class ClayEntityBase extends PathfinderMob implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    static final EntityDataAccessor<Integer> DATA_MATERIAL = SynchedEntityData.defineId(ClaySoldier.class, EntityDataSerializers.INT);
    final CSAPI.ClaySoldierMaterial material;


    protected ClayEntityBase(EntityType<? extends PathfinderMob> entity, Level level, CSAPI.ClaySoldierMaterial material) {
        super(entity, level);
        this.material = material;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MATERIAL, 0);
    }

    public CSAPI.ClaySoldierMaterial getMaterial() {
        return CSAPI.ClaySoldierMaterial.values()[Mth.clamp(this.entityData.get(DATA_MATERIAL), 0, CSAPI.ClaySoldierMaterial.values().length - 1)];
    }

    public void setMaterial(CSAPI.ClaySoldierMaterial mat) {
        this.entityData.set(DATA_MATERIAL, mat.ordinal());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);

        data.putInt("Variant", this.getMaterial().ordinal());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data) {
        super.readAdditionalSaveData(data);

        this.setMaterial(CSAPI.ClaySoldierMaterial.values()[data.getInt("Variant")]);
    }

    public boolean isMatchingMaterial(ClayEntityBase entityComparedWith) {
        return this.getMaterial() == entityComparedWith.getMaterial();
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    public ItemStack getPickedResult(HitResult target) {
        return this.getItemForm();
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        super.die(damageSource);
        if (damageSource.isFire()) {
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(this.getBrickedForm())));
        } else {
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemForm()));
        }
    }

    public void removeSoldier() {
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemForm()));
        this.remove(RemovalReason.KILLED);
    }

    public abstract ItemStack getItemForm();

    public abstract Item getBrickedForm();

}
