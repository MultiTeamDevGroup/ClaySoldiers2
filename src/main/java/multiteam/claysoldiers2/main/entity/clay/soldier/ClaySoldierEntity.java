package multiteam.claysoldiers2.main.entity.clay.soldier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
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
    static final int variantsNumber = 23;

    private AnimationFactory factory = new AnimationFactory(this);

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world);
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

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return ClaySoldierAPI.getItemForm(this);
    }

    public void removeSoldier(){
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), ClaySoldierAPI.getItemForm(this)));
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }


    public int getVariant() {
        return Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 0, variantsNumber-1);
    }


    public void setVariant(int id) {
        this.entityData.set(DATA_VARIANT_ID, id);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);
        data.putInt("Variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data) {
        super.readAdditionalSaveData(data);
        this.setVariant(data.getInt("Variant"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    public boolean isSoldierMatching(ClaySoldierEntity soldierComparedWith){
        if(this.getVariant() == soldierComparedWith.getVariant()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ClaySoldierEntity.class, 4, true, false, (targetEntity) -> {
            if(targetEntity instanceof ClaySoldierEntity){
                ClaySoldierEntity targetedSoldier = (ClaySoldierEntity) targetEntity;
                return !targetedSoldier.isSoldierMatching(this);
            }
            return false;
        }));
    }
}
