package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.item.ClaySoldierItem;
import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClaySoldierEntity extends PathfinderMob implements IAnimatable {

    static final EntityDataAccessor<Integer> DATA_MATERIAL = SynchedEntityData.defineId(ClaySoldierEntity.class, EntityDataSerializers.INT);
    final ClaySoldierAPI.ClaySoldierMaterial material;
    private List<ClaySoldierAPI.ClaySoldierModifier> modifiers = new ArrayList<>();

    private AnimationFactory factory = new AnimationFactory(this);

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
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void removeSoldier(){
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemForm()));
        //if(this.modifiers != null || this.modifiers.size() > 0){
        //            for (ClaySoldierAPI.ClaySoldierModifier modif: this.modifiers) {
        //                this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(modif.getModifierItem())));
        //            }
        //        }
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MATERIAL, 0);
    }


    public List<ClaySoldierAPI.ClaySoldierModifier> getModifiers(){
        return this.modifiers;
    }

    public void addModifier(ClaySoldierAPI.ClaySoldierModifier modifier){
        this.modifiers.add(modifier);
    }

    public void removeModifier(ClaySoldierAPI.ClaySoldierModifier modifier){
        this.modifiers.remove(modifier);
    }

    public ClaySoldierAPI.ClaySoldierMaterial getMaterial() {
        return ClaySoldierAPI.ClaySoldierMaterial.values()[Mth.clamp(this.entityData.get(DATA_MATERIAL), 0, ClaySoldierAPI.ClaySoldierMaterial.values().length-1)];
    }

    public void setMaterial(ClaySoldierAPI.ClaySoldierMaterial mat) {
        this.entityData.set(DATA_MATERIAL, mat.ordinal());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);
        data.putInt("Variant", this.getMaterial().ordinal());
        int[] modifs = new int[this.getModifiers().size()];
        for (int i = 0; i < this.modifiers.size(); i++){
            modifs[i] = this.modifiers.get(i).ordinal();
        }
        data.putIntArray("Modifiers", modifs);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data) {
        super.readAdditionalSaveData(data);
        this.setMaterial(ClaySoldierAPI.ClaySoldierMaterial.values()[data.getInt("Variant")]);
        for (int i = 0; i < data.getIntArray("Modifiers").length; i++){
            this.addModifier(ClaySoldierAPI.ClaySoldierModifier.values()[data.getIntArray("Modifiers")[i]]);
        }
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
    public void die(DamageSource damageSource) {
        super.die(damageSource);
        if(damageSource.isFire()){
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.BRICKED_SOLDIER.get())));
        }else{
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemForm()));
        }
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return this.getItemForm();
    }


    public ItemStack getItemForm(){
        ItemStack retStack = new ItemStack(this.getMaterial().getItemForm());

        CompoundTag tag = new CompoundTag();
        int[] modifs_ = new int[this.modifiers.size()];
        for (int i = 0; i < this.modifiers.size(); i++){
            modifs_[i] = this.modifiers.get(i).ordinal();
        }
        tag.putIntArray("Modifiers", modifs_);
        retStack.setTag(tag);

        return retStack;
    }

    private class BoolModifierCompound{
        public boolean retBool;
        public ClaySoldierAPI.ClaySoldierModifier retModif;

        BoolModifierCompound(boolean bool, ClaySoldierAPI.ClaySoldierModifier modif){
            this.retModif = modif;
            this.retBool = bool;
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
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();
        ClaySoldierEntity soldier = this;
        Level level = soldier.getLevel();

        if(!level.isClientSide()){

            List<ItemEntity> itemsAround = level.getEntitiesOfClass(ItemEntity.class, new AABB(soldier.getX()-1,soldier.getY()-1,soldier.getZ()-1,soldier.getX()+1,soldier.getY()+1,soldier.getZ()+1));

            for (ItemEntity itemEntity : itemsAround){
                BoolModifierCompound compund = shouldPickUp(itemEntity.getItem());
                if(compund.retBool){
                    soldier.addModifier(compund.retModif);
                    itemEntity.getItem().shrink(1);
                }
            }

        }
    }

    public BoolModifierCompound shouldPickUp(ItemStack stack) {
        BoolModifierCompound ret = new BoolModifierCompound(false, null);
        for (int i = 0; i < ClaySoldierAPI.ClaySoldierModifier.values().length; i++){
            if(ClaySoldierAPI.ClaySoldierModifier.values()[i].getModifierItem() == stack.getItem()){
                if(!this.modifiers.contains(ClaySoldierAPI.ClaySoldierModifier.values()[i])){
                    ret.retBool = true;
                    ret.retModif = ClaySoldierAPI.ClaySoldierModifier.values()[i];
                }
            }
        }
        return ret;
    }

}
