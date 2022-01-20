package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.entity.ai.ClaySoldierAttackGoal;
import multiteam.claysoldiers2.main.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import oshi.util.tuples.Pair;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ClaySoldierEntity extends PathfinderMob implements IAnimatable {

    static final EntityDataAccessor<Integer> DATA_MATERIAL = SynchedEntityData.defineId(ClaySoldierEntity.class, EntityDataSerializers.INT);
    final ClaySoldierAPI.ClaySoldierMaterial material;
    private List<Pair<ClaySoldierAPI.ClaySoldierModifier, Integer>> modifiers = new ArrayList<>();

    public ItemStack MainHandItem = new ItemStack(Items.AIR);
    public ItemStack SecondHandItem = new ItemStack(Items.AIR);

    private AnimationFactory factory = new AnimationFactory(this);

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world);
        this.material = ClaySoldierAPI.ClaySoldierMaterial.CLAY_SOLDIER;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, ClaySoldierAPI.Settings.soldierViewDistance);
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
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MATERIAL, 0);
    }

    public List<Pair<ClaySoldierAPI.ClaySoldierModifier, Integer>> getModifiers(){
        return this.modifiers;
    }

    public void addModifier(ClaySoldierAPI.ClaySoldierModifier modifier, int amount){
        this.modifiers.add(new Pair<>(modifier, amount));
    }

    public void removeModifier(ClaySoldierAPI.ClaySoldierModifier modifierToRemove){
        ClaySoldierEntity soldier = this;
        for (int i = 0; i < soldier.getModifiers().size(); i++) {
            if(soldier.getModifiers().get(i) != null && soldier.getModifiers().get(i).getA() == modifierToRemove){
                soldier.getModifiers().remove(i);
            }else{return;}
        }
    }

    public void removeAllModifiers(){
        this.modifiers.clear();
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
        int[] modifsAmounts = new int[this.getModifiers().size()];
        for (int i = 0; i < this.modifiers.size(); i++){
            modifs[i] = this.modifiers.get(i).getA().ordinal();
            modifsAmounts[i] = this.modifiers.get(i).getB();
        }
        data.putIntArray("Modifiers", modifs);
        data.putIntArray("ModifiersAmounts", modifsAmounts);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data) {
        super.readAdditionalSaveData(data);
        this.setMaterial(ClaySoldierAPI.ClaySoldierMaterial.values()[data.getInt("Variant")]);
        for (int i = 0; i < data.getIntArray("Modifiers").length; i++){
            this.addModifier(ClaySoldierAPI.ClaySoldierModifier.values()[data.getIntArray("Modifiers")[i]], data.getIntArray("ModifiersAmounts")[i]);
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

    @Override
    public ItemStack getPickResult() {
        return this.getItemForm();
    }

    public ItemStack getItemForm(){
        ItemStack retStack = new ItemStack(this.getMaterial().getItemForm());

        if(!this.getModifiers().isEmpty()){
            CompoundTag tag = new CompoundTag();
            int[] modifs_ = new int[this.getModifiers().size()];
            int[] modifsAmounts_ = new int[this.getModifiers().size()];
            for (int i = 0; i < this.getModifiers().size(); i++){
                modifs_[i] = this.getModifiers().get(i).getA().ordinal();
                modifsAmounts_[i] = this.getModifiers().get(i).getB();
            }
            tag.putIntArray("Modifiers", modifs_);
            tag.putIntArray("ModifiersAmounts", modifsAmounts_);
            retStack.setTag(tag);
        }

        return retStack;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ClaySoldierAttackGoal(this, 2, true));
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

        //Picking up items
        if(!level.isClientSide){

            List<ItemEntity> itemsAround = level.getEntitiesOfClass(ItemEntity.class, new AABB(soldier.getX()-1,soldier.getY()-1,soldier.getZ()-1,soldier.getX()+1,soldier.getY()+1,soldier.getZ()+1));

            for (ItemEntity itemEntity : itemsAround){
                Pair<Pair<ClaySoldierAPI.ClaySoldierModifier, Integer>, Pair<Boolean, Integer>> compund = shouldPickUp(itemEntity.getItem());
                if(compund.getB().getA()){

                    Pair<ClaySoldierAPI.ClaySoldierModifier, Integer> oldModifier = new Pair<>(compund.getA().getA(), compund.getB().getB());

                    List<ClaySoldierAPI.ClaySoldierModifier> modifiersOfSoldier = new ArrayList<>();
                    for (Pair<ClaySoldierAPI.ClaySoldierModifier, Integer> pair: this.getModifiers()) {
                        modifiersOfSoldier.add(pair.getA());
                    }

                    boolean reason = modifiersOfSoldier.contains(oldModifier.getA());

                    if(reason){
                        soldier.getModifiers().remove(modifiersOfSoldier.indexOf(oldModifier.getA()));
                        soldier.addModifier(compund.getA().getA(), compund.getA().getB() + oldModifier.getB());
                    }else{
                        soldier.addModifier(compund.getA().getA(), compund.getA().getB());
                    }
                    itemEntity.getItem().shrink(compund.getA().getB());
                    level.playSound(null, soldier.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1, 1);
                    if(compund.getA().getA().getModifierType().anyOf(List.of(ClaySoldierAPI.ClaySoldierModifierType.MAIN_HAND, ClaySoldierAPI.ClaySoldierModifierType.MAIN_HAND_BOOST_ITEM, ClaySoldierAPI.ClaySoldierModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM))){
                        this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                    }else if(compund.getA().getA().getModifierType().anyOf(List.of(ClaySoldierAPI.ClaySoldierModifierType.OFF_HAND, ClaySoldierAPI.ClaySoldierModifierType.OFF_HAND_BOOST_ITEM, ClaySoldierAPI.ClaySoldierModifierType.OFF_HAND_INF_BOOST_COMBINED))){
                        this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                    }else if(compund.getA().getA().getModifierType().anyOf(List.of(ClaySoldierAPI.ClaySoldierModifierType.ANY_HAND_BOOST_ITEM, ClaySoldierAPI.ClaySoldierModifierType.ANY_HAND_AMOUNT_BOOST_ITEM))){
                        if(this.MainHandItem.getItem() == Items.AIR){
                            this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        }else if(this.SecondHandItem.getItem() == Items.AIR){
                            this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        }
                    }else if(compund.getA().getA().getModifierType().anyOf(List.of(ClaySoldierAPI.ClaySoldierModifierType.BOTH_HANDS))){
                        if(this.MainHandItem.getItem() == Items.AIR && this.SecondHandItem.getItem() == Items.AIR){
                            this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                            this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        }
                    }
                }
            }
        }

        //Handling modifiers
        if(!level.isClientSide && !this.getModifiers().isEmpty()){
            for (int i = 0; i < this.getModifiers().size(); i++) {
                if(this.getModifiers().get(i) != null){
                    this.getModifiers().get(i).getA().ExecuteModifierOn(this, this.getModifiers().get(i).getA());
                }else{return;}
            }
        }

    }

    public Pair<Pair<ClaySoldierAPI.ClaySoldierModifier, Integer>, Pair<Boolean, Integer>> shouldPickUp(ItemStack stack) {
        Pair<Pair<ClaySoldierAPI.ClaySoldierModifier, Integer>, Pair<Boolean, Integer>> ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
        for (int i = 0; i < ClaySoldierAPI.ClaySoldierModifier.values().length; i++){

            ClaySoldierAPI.ClaySoldierModifier modifier = ClaySoldierAPI.ClaySoldierModifier.values()[i];

            List<ClaySoldierAPI.ClaySoldierModifier> modifiersOfSoldier = new ArrayList<>();
            for (Pair<ClaySoldierAPI.ClaySoldierModifier, Integer> pair: this.getModifiers()) {
                modifiersOfSoldier.add(pair.getA());
            }



            if(modifier.getModifierItem() == stack.getItem()){
                boolean hasIncompatibles = false;

                if(!modifier.getIncompatibleModifiers().isEmpty()){
                    for (ClaySoldierAPI.ClaySoldierModifier modif : modifiersOfSoldier) {
                        if(modifier.getIncompatibleModifiers().contains(modif.getModifierItem())){
                            hasIncompatibles = true;
                        }
                    }
                }

                if(!hasIncompatibles){

                    int pickUpAmount = 1;
                    int containedAmount = 0;
                    if(modifiersOfSoldier.contains(modifier)){
                        containedAmount = this.getModifiers().get(modifiersOfSoldier.indexOf(modifier)).getB();
                    }

                    if(modifier.canBeStacked() && !modifiersOfSoldier.contains(modifier)){
                        if(stack.getCount() > modifier.getMaxStackingLimit()){
                            pickUpAmount = modifier.getMaxStackingLimit();
                        }else{
                            pickUpAmount = stack.getCount();
                        }
                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    }else if(modifier.canBeStacked() && modifiersOfSoldier.contains(modifier) && containedAmount < modifier.getMaxStackingLimit()){
                        //TODO fix when a single item is dropped/picked up, it adds a new on top of the already existing modifier, resulting in duplicates

                        if(stack.getCount() > modifier.getMaxStackingLimit()-containedAmount){
                            pickUpAmount = modifier.getMaxStackingLimit()-containedAmount;
                        }else{
                            pickUpAmount = stack.getCount();
                        }

                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    }else if(!modifier.canBeStacked() && !modifiersOfSoldier.contains(modifier)){
                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    }

                    if(ret.getB().getA()){
                        ClaySoldierAPI.ClaySoldierModifier thisModifier = ret.getA().getA();
                        switch (thisModifier.getModifierType()){
                            case MAIN_HAND -> {
                                if(this.MainHandItem.getItem() != Items.AIR){
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case OFF_HAND, OFF_HAND_INF_BOOST_COMBINED -> {
                                if(this.SecondHandItem.getItem() != Items.AIR){
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case MAIN_HAND_AMOUNT_BOOST_ITEM, MAIN_HAND_BOOST_ITEM -> {
                                if(this.MainHandItem.getItem() != Items.AIR || this.MainHandItem.getItem() != thisModifier.getModifierItem()){
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case OFF_HAND_BOOST_ITEM -> {
                                if(this.SecondHandItem.getItem() != Items.AIR || this.SecondHandItem.getItem() != thisModifier.getModifierItem()){
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case BOTH_HANDS -> {
                                if(this.MainHandItem.getItem() != Items.AIR && this.SecondHandItem.getItem() != Items.AIR){
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case ANY_HAND_AMOUNT_BOOST_ITEM, ANY_HAND_BOOST_ITEM -> {
                                if(this.MainHandItem.getItem() != Items.AIR){
                                    if(this.SecondHandItem.getItem() != Items.AIR){
                                        ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                    }
                                }else if(this.SecondHandItem.getItem() != Items.AIR){
                                    if(this.MainHandItem.getItem() != Items.AIR){
                                        ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                    }
                                }
                            }
                            case BOOST_ITEM -> {} //has a single use per item
                            case INF_BOOST -> {} //applies an effect infinitely
                            case INF_BOOST_COSMETIC -> {} //applies a cosmetic only effect infinitely
                            case INF_BOOST_COMBINED -> {} //applies an effect as long as its combined with another modifier
                            case EFFECT -> {} //applies effect for a period of time
                            case INF_EFFECT -> {} //applies status effect infinitely
                            case CANCEL -> {} //cancels any modifier on the soldier
                        }
                    }
                }
            }
        }
        return ret;
    }

}
