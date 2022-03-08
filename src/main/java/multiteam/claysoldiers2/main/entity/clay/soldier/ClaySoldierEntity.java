package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.ai.ClaySoldierAttackGoal;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.modifiers.CSAPI.ModifierType;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
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
import org.jetbrains.annotations.NotNull;
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
import java.util.Objects;

public class ClaySoldierEntity extends PathfinderMob implements IAnimatable {

    static final EntityDataAccessor<Integer> DATA_MATERIAL = SynchedEntityData.defineId(ClaySoldierEntity.class, EntityDataSerializers.INT);
    final CSAPI.ClaySoldierMaterial material;
    private List<CSModifier.Instance> modifiers = new ArrayList<>();

    //private final List<Pair<ClaySoldierModifier, Integer>> modifiers = new ArrayList<>();


    private final AnimationFactory factory = new AnimationFactory(this);

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world);
        this.material = CSAPI.ClaySoldierMaterial.CLAY_SOLDIER;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, CSAPI.Settings.soldierViewDistance);
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

    public void removeSoldier() {
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemForm()));
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MATERIAL, 0);
    }


    public List<CSModifier.Instance> getModifiers() {
        return this.modifiers;
    }

    public void addModifier(CSModifier.Instance modifierToAdd) {
        this.modifiers.add(modifierToAdd);
    }

    public void removeModifier(CSModifier.Instance modifierToRemove) {
        this.modifiers.remove(modifierToRemove);
    }

    public void removeAllModifiers() {
        this.modifiers.clear();
    }

    public CSAPI.ClaySoldierMaterial getMaterial() {
        return CSAPI.ClaySoldierMaterial.values()[Mth.clamp(this.entityData.get(DATA_MATERIAL), 0, CSAPI.ClaySoldierMaterial.values().length - 1)];
    }

    public void setMaterial(CSAPI.ClaySoldierMaterial mat) {
        this.entityData.set(DATA_MATERIAL, mat.ordinal());
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag data) {
        super.addAdditionalSaveData(data);
        data.putInt("Variant", this.getMaterial().ordinal());

        ListTag list = new ListTag();
        for (CSModifier.Instance entry : this.getModifiers()) {
            Integer amount = entry.getAmount();
            ResourceLocation modifier = entry.getModifier().getRegistryName();
            CompoundTag modifierTag = new CompoundTag();
            modifierTag.putString("Type", Objects.requireNonNull(modifier).toString());
            modifierTag.putInt("Amount", amount);
            list.add(modifierTag);
        }
        data.put("Modifiers", list);

    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag data) {
        super.readAdditionalSaveData(data);
        this.setMaterial(CSAPI.ClaySoldierMaterial.values()[data.getInt("Variant")]);

        // Data structure:
        // + "Modifier" (Compound)
        // |-  "Type" (String)
        // |-  "Amount" (Int)
        for (Tag tag : data.getList("Modifiers", Tag.TAG_STRING)) {
            if (tag instanceof CompoundTag modifierTag) {
                ResourceLocation type = new ResourceLocation(modifierTag.getString("Type"));
                CSModifier modifier = Registration.getModifierRegistry().getValue(type);
                int amount = modifierTag.getInt("Amount");
                this.addModifier(new CSModifier.Instance(modifier, amount));
            }
        }

    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor p_21434_, @NotNull DifficultyInstance p_21435_, @NotNull MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    public boolean isSoldierMatching(ClaySoldierEntity soldierComparedWith) {
        return this.getMaterial() == soldierComparedWith.getMaterial();
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        super.die(damageSource);
        if (damageSource.isFire()) {
            this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.BRICKED_SOLDIER.get())));
        } else {
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

    public ItemStack getItemForm() {
        ItemStack itemForm = new ItemStack(this.getMaterial().getItemForm());

        if (!this.getModifiers().isEmpty()) {
            CompoundTag tag = new CompoundTag();
            ListTag list = new ListTag();
            for (CSModifier.Instance entry : getModifiers()) {
                CompoundTag modifierTag = new CompoundTag();
                modifierTag.putString("Type", entry.getModifier().getRegistryName().toString());
                modifierTag.putInt("Amount", entry.getAmount());
                list.add(modifierTag);
            }
            tag.put("Modifiers", list);

            itemForm.setTag(tag);
        }

        return itemForm;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ClaySoldierAttackGoal(this, 2, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ClaySoldierEntity.class, 0, true, false, (targetEntity) -> {
            if (targetEntity instanceof ClaySoldierEntity targetedSoldier) {
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
        if (!level.isClientSide) {

            List<ItemEntity> itemsAround = level.getEntitiesOfClass(ItemEntity.class, new AABB(soldier.getX() - 1, soldier.getY() - 1, soldier.getZ() - 1, soldier.getX() + 1, soldier.getY() + 1, soldier.getZ() + 1));

            for (ItemEntity itemEntity : itemsAround) {
                Pair<Pair<CSModifier, Integer>, Pair<Boolean, Integer>> compund = shouldPickUp(itemEntity.getItem());
                if (compund.getB().getA()) {

                    CSModifier.Instance oldModifier = new Pair<>(compund.getA().getA(), compund.getB().getB());

                    boolean reason = this.modifiers.contains(oldModifier);

                    if (reason) {
                        soldier.getModifiers().remove(modifiersOfSoldier.indexOf(oldModifier.getA()));
                        soldier.addModifier(compund.getA().getA(), compund.getA().getB() + oldModifier.getB());
                    } else {
                        soldier.addModifier(compund.getA().getA(), compund.getA().getB());
                    }
                    itemEntity.getItem().shrink(compund.getA().getB());
                    level.playSound(null, soldier.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1, 1);
                    if (compund.getA().getA().getModifierType().anyOf(List.of(ModifierType.MAIN_HAND, ModifierType.MAIN_HAND_BOOST_ITEM, ModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM))) {
                        this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                    } else if (compund.getA().getA().getModifierType().anyOf(List.of(ModifierType.OFF_HAND, ModifierType.OFF_HAND_BOOST_ITEM, ModifierType.OFF_HAND_INF_BOOST_COMBINED))) {
                        this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                    } else if (compund.getA().getA().getModifierType().anyOf(List.of(ModifierType.ANY_HAND_BOOST_ITEM, ModifierType.ANY_HAND_AMOUNT_BOOST_ITEM))) {
                        if (this.MainHandItem.getItem() == Items.AIR) {
                            this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        } else if (this.SecondHandItem.getItem() == Items.AIR) {
                            this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        }
                    } else if (compund.getA().getA().getModifierType().anyOf(List.of(ModifierType.BOTH_HANDS))) {
                        if (this.MainHandItem.getItem() == Items.AIR && this.SecondHandItem.getItem() == Items.AIR) {
                            this.MainHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                            this.SecondHandItem = new ItemStack(compund.getA().getA().getModifierItem(), compund.getA().getB());
                        }
                    }
                }
            }
        }

        //Handling modifiers
        if (!level.isClientSide && !this.getModifiers().isEmpty()) {
            for (int i = 0; i < this.getModifiers().size(); i++) {
                if (this.getModifiers().get(i) != null) {
                    this.getModifiers().get(i).getA().ExecuteModifierOn(this, this.getModifiers().get(i).getA());
                } else {
                    return;
                }
            }
        }

    }

    public Pair<Pair<CSModifier, Integer>, Pair<Boolean, Integer>> shouldPickUp(ItemStack stack) {
        Pair<Pair<CSModifier, Integer>, Pair<Boolean, Integer>> ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
        for (CSModifier modifier : Registration.getModifierRegistry().getValues()) {
            List<CSModifier> modifiersOfSoldier = new ArrayList<>();
            for (Pair<CSModifier, Integer> pair : this.getModifiers()) {
                modifiersOfSoldier.add(pair.getA());
            }

            if (modifier.getModifierItem() == stack.getItem()) {
                boolean hasIncompatibles = false;

                if (!modifier.getIncompatibleModifiers().isEmpty()) {
                    for (CSModifier modif : modifiersOfSoldier) {
                        if (modifier.getIncompatibleModifiers().contains(modif.getModifierItem())) {
                            hasIncompatibles = true;
                        }
                    }
                }

                if (!hasIncompatibles) {

                    int pickUpAmount = 1;
                    int containedAmount = 0;
                    if (modifiersOfSoldier.contains(modifier)) {
                        containedAmount = this.getModifiers().get(modifiersOfSoldier.indexOf(modifier)).getB();
                    }

                    if (modifier.canBeStacked() && !modifiersOfSoldier.contains(modifier)) {
                        if (stack.getCount() > modifier.getMaxStackingLimit()) {
                            pickUpAmount = modifier.getMaxStackingLimit();
                        } else {
                            pickUpAmount = stack.getCount();
                        }
                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    } else if (modifier.canBeStacked() && modifiersOfSoldier.contains(modifier) && containedAmount < modifier.getMaxStackingLimit()) {
                        //TODO fix when a single item is dropped/picked up, it adds a new on top of the already existing modifier, resulting in duplicates

                        if (stack.getCount() > modifier.getMaxStackingLimit() - containedAmount) {
                            pickUpAmount = modifier.getMaxStackingLimit() - containedAmount;
                        } else {
                            pickUpAmount = stack.getCount();
                        }

                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    } else if (!modifier.canBeStacked() && !modifiersOfSoldier.contains(modifier)) {
                        ret = new Pair<>(new Pair<>(modifier, pickUpAmount), new Pair<>(true, containedAmount));
                    }

                    if (ret.getB().getA()) {
                        CSModifier thisModifier = ret.getA().getA();
                        switch (thisModifier.getModifierType()) {
                            case MAIN_HAND -> {
                                if (this.MainHandItem.getItem() != Items.AIR) {
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case OFF_HAND, OFF_HAND_INF_BOOST_COMBINED -> {
                                if (this.SecondHandItem.getItem() != Items.AIR) {
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case MAIN_HAND_AMOUNT_BOOST_ITEM, MAIN_HAND_BOOST_ITEM -> {
                                if (this.MainHandItem.getItem() != Items.AIR || this.MainHandItem.getItem() != thisModifier.getModifierItem()) {
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case OFF_HAND_BOOST_ITEM -> {
                                if (this.SecondHandItem.getItem() != Items.AIR || this.SecondHandItem.getItem() != thisModifier.getModifierItem()) {
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case BOTH_HANDS -> {
                                if (this.MainHandItem.getItem() != Items.AIR && this.SecondHandItem.getItem() != Items.AIR) {
                                    ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                }
                            }
                            case ANY_HAND_AMOUNT_BOOST_ITEM, ANY_HAND_BOOST_ITEM -> {
                                if (this.MainHandItem.getItem() != Items.AIR) {
                                    if (this.SecondHandItem.getItem() != Items.AIR) {
                                        ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                    }
                                } else if (this.SecondHandItem.getItem() != Items.AIR) {
                                    if (this.MainHandItem.getItem() != Items.AIR) {
                                        ret = new Pair<>(new Pair<>(null, null), new Pair<>(false, 0));
                                    }
                                }
                            }
                            case BOOST_ITEM -> {
                            } //has a single use per item
                            case INF_BOOST -> {
                            } //applies an effect infinitely
                            case INF_BOOST_COSMETIC -> {
                            } //applies a cosmetic only effect infinitely
                            case INF_BOOST_COMBINED -> {
                            } //applies an effect as long as its combined with another modifier
                            case EFFECT -> {
                            } //applies effect for a period of time
                            case INF_EFFECT -> {
                            } //applies status effect infinitely
                            case CANCEL -> {
                            } //cancels any modifier on the soldier
                        }
                    }
                }
            }
        }
        return ret;
    }

}
