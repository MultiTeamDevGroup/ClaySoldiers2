package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class ClaySoldierEntity extends PathfinderMob implements IAnimatable {

    static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(ClaySoldierEntity.class, EntityDataSerializers.INT);
    final ClaySoldierAPI.ClaySoldierMaterial material;

    private AnimationFactory factory = new AnimationFactory(this);

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world, ClaySoldierAPI.ClaySoldierMaterial material) {
        super(entity, world);
        this.material = material;
    }

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world);
        this.material = ClaySoldierAPI.ClaySoldierMaterial.CLAY_SOLDIER;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double)0.1F).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        //event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tinyman.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void removeSoldier(){
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(this.material.getItemForm())));
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }



    public ClaySoldierAPI.ClaySoldierMaterial getMaterial() {
        return ClaySoldierAPI.ClaySoldierMaterial.values()[Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 0, ClaySoldierAPI.ClaySoldierMaterial.values().length-1)];
    }


    public void setMaterial(ClaySoldierAPI.ClaySoldierMaterial mat) {
        this.entityData.set(DATA_VARIANT_ID, mat.ordinal());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);
        data.putInt("Variant", this.getMaterial().ordinal());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data) {
        super.readAdditionalSaveData(data);
        this.setMaterial(ClaySoldierAPI.ClaySoldierMaterial.values()[data.getInt("Variant")]);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    public boolean isSoldierMatching(ClaySoldierEntity soldierComparedWith){
        if(this.getMaterial() == soldierComparedWith.getMaterial()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 2, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ClaySoldierEntity.class, 0, true, false, (targetEntity) -> {
            if(targetEntity instanceof ClaySoldierEntity){
                ClaySoldierEntity targetedSoldier = (ClaySoldierEntity) targetEntity;
                return !targetedSoldier.isSoldierMatching(this);
            }
            return false;
        }));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
    }

    @Override
    public void die(DamageSource damageSource) {
        super.die(damageSource);
        if(damageSource.isFire()){
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.BRICKED_SOLDIER.get())));
        }else{
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(this.material.getItemForm())));
        }
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(this.material.getItemForm());
    }
}
