package multiteam.claysoldiers2.main.entity.claysoldier;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.ai.ClaySoldierAttackGoal;
import multiteam.claysoldiers2.main.entity.ai.ClaySoldierFollowKingGoal;
import multiteam.claysoldiers2.main.entity.ai.NearestAttackableTargetGoalModified;
import multiteam.claysoldiers2.main.entity.base.ClayEntityBase;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.modifiers.ModModifiers;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.DamageBonusCSModifier;
import multiteam.claysoldiers2.main.networking.Networking;
import multiteam.claysoldiers2.main.networking.SoldierPipelinePacket;
import net.minecraft.core.Registry;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClaySoldier extends ClayEntityBase {

    private CompoundTag pipeline = new CompoundTag();
    private final List<CSModifier.Instance> modifiers = new ArrayList<>();

    public boolean isInvisibleToOthers = false;
    public boolean canSeeInvisibleToOthers = false;
    public boolean hostileAgainstItsOwnKind = false;
    public boolean shouldStickToPosition = false;
    public Vec3 stickingPosition = new Vec3(0, 0, 0);
    private static final EntityDataAccessor<CompoundTag> PIPELINE_ACCESSOR = SynchedEntityData.defineId(ClaySoldier.class, EntityDataSerializers.COMPOUND_TAG);
    private boolean fullBright;
    private boolean modifiersChanged = false;
    private final Object lock = new Object();

    public ClaySoldier(EntityType<? extends PathfinderMob> entity, Level world, CSAPI.ClaySoldierMaterial material) {
        super(entity, world, material);
        this.setMaterial(material);

        entityData.define(PIPELINE_ACCESSOR, pipeline);
    }

    public ClaySoldier(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world, CSAPI.ClaySoldierMaterial.CLAY_SOLDIER);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, CSAPI.Settings.soldierViewDistance).add(Attributes.JUMP_STRENGTH, 1.0d).add(Attributes.KNOCKBACK_RESISTANCE, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ClaySoldierAttackGoal(this, 2, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoalModified<>(this, ClaySoldier.class, 0, true, false, (targetEntity) -> {
            if (targetEntity instanceof ClaySoldier targetedSoldier) {
                return (!targetedSoldier.isMatchingMaterial(this) || this.hostileAgainstItsOwnKind) && (!targetedSoldier.isInvisibleToOthers || this.canSeeInvisibleToOthers);
            }
            return false;
        }));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new ClaySoldierFollowKingGoal(this, 3.0D, 2.0F, 30.0F, (targetEntity) ->{
            if (targetEntity instanceof ClaySoldier targetedSoldier) {
                return targetedSoldier.isMatchingMaterial(this) && targetedSoldier.hasModifier(ModModifiers.KING_BOOST.get()) && targetedSoldier != this;
            }else{
                return false;
            }
        }));
    }

    @Override
    public ItemStack getItemForm() {
        ItemStack itemForm = new ItemStack(this.getMaterial().getItemForm());

        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();

        if (!this.getModifiers().isEmpty()) {
            for (CSModifier.Instance entry : getModifiers()) {
                // Better readable exceptions if registry name is null. (Better for addon developers)
                ResourceLocation modifierKey = Registration.getModifierRegistry().getKey(entry.getModifier());
                Objects.requireNonNull(modifierKey, "Registry name is null for modifier " + entry.getModifier().getClass().getName());

                CompoundTag modifierTag = new CompoundTag();
                modifierTag.putString("Type", modifierKey.toString());
                modifierTag.putInt("Amount", entry.getAmount());
                list.add(modifierTag);
            }
            tag.put("Modifiers", list);

            itemForm.setTag(tag);
        }

        // Better readable exceptions if registry names are null. (Better for addon developers)
        ResourceLocation mainHandKey = ForgeRegistries.ITEMS.getKey(this.getMainHandItem().getItem());
        ResourceLocation offhandKey = ForgeRegistries.ITEMS.getKey(this.getOffhandItem().getItem());
        Objects.requireNonNull(mainHandKey, "Registry name is null for main hand item " + this.getMainHandItem().getItem().getClass().getName());
        Objects.requireNonNull(offhandKey, "Registry name is null for offhand item " + this.getOffhandItem().getItem().getClass().getName());

        tag.putString("MainHandItem", mainHandKey.toString());
        tag.putString("OffHandItem", offhandKey.toString());

        return itemForm;
    }

    @Override
    public Item getBrickedForm() {
        return ModItems.BRICKED_SOLDIER.get();
    }


    public List<CSModifier.Instance> getModifiers() {
        return this.modifiers;
    }

    public void addModifier(CSModifier.Instance modifierToAdd) {
        this.modifiers.add(modifierToAdd);
        this.modifiersChanged = true;
    }

    public void stackModifier(CSModifier.Instance modifierToAdd, CSModifier.Instance modifierOnSoldier){
        modifierOnSoldier.setAmount(modifierOnSoldier.getAmount()+modifierToAdd.getAmount());
        this.modifiersChanged = true;
    }

    public void removeModifier(CSModifier.Instance modifierToRemove) {
        this.modifiers.remove(modifierToRemove);
        this.modifiersChanged = true;
    }

    public void cacheModifiersForSending() {
        pipeline.put("Modifiers", getModifiersAsTag());
    }

    private ListTag getModifiersAsTag() {
        final ListTag list = new ListTag();
        for (CSModifier.Instance entry : getModifiers()) {
            CompoundTag modifierTag = new CompoundTag();
            modifierTag.putString("Type", Objects.requireNonNull(Registration.getModifierRegistry().getKey(entry.getModifier())).toString());
            modifierTag.putInt("Amount", entry.getAmount());
            list.add(modifierTag);
        }
        return list;
    }


    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag data) {

        ListTag list = new ListTag();
        for (CSModifier.Instance entry : this.getModifiers()) {
            int amount = entry.getAmount();
            ResourceLocation modifier = Registration.getModifierRegistry().getKey(entry.getModifier());

            CompoundTag modifierTag = new CompoundTag();
            modifierTag.putString("Type", Objects.requireNonNull(modifier).toString());
            modifierTag.putInt("Amount", amount);

            list.add(modifierTag);
        }
        data.put("Modifiers", list);

        data.putString("MainHandItem", ForgeRegistries.ITEMS.getKey(this.getMainHandItem().getItem()).toString());
        data.putString("OffHandItem", ForgeRegistries.ITEMS.getKey(this.getOffhandItem().getItem()).toString());

        data.putBoolean("ShouldStickToPosition", this.shouldStickToPosition);
        data.putFloat("StickingPositionX", (float) this.stickingPosition.x);
        data.putFloat("StickingPositionY", (float) this.stickingPosition.y);
        data.putFloat("StickingPositionZ", (float) this.stickingPosition.z);

        super.addAdditionalSaveData(data);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag data) {
        modifiers.clear();
        // Data structure:
        // + "Modifier" (Compound)
        // |-  "Type" (String)
        // |-  "Amount" (Int)
        for (Tag tag : data.getList("Modifiers", Tag.TAG_COMPOUND)) {
            System.out.println();
            if (tag instanceof CompoundTag modifierTag) {
                ResourceLocation type = new ResourceLocation(modifierTag.getString("Type"));
                CSModifier modifier = Registration.getModifierRegistry().getValue(type);
                int amount = modifierTag.getInt("Amount");
                this.addModifier(new CSModifier.Instance(modifier, amount));
                System.out.println(modifier.getModifierName());
            }
        }

        this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Registry.ITEM.get(new ResourceLocation(data.getString("MainHandItem")))));
        this.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Registry.ITEM.get(new ResourceLocation(data.getString("OffHandItem")))));

        this.shouldStickToPosition = data.getBoolean("ShouldStickToPosition");
        this.stickingPosition = new Vec3(data.getFloat("StickingPositionX"), data.getFloat("StickingPositionY"), data.getFloat("StickingPositionZ"));

        super.readAdditionalSaveData(data);
    }


    @Override
    public void tick() {
        super.tick();

        //Picking up items
        if (!this.getLevel().isClientSide) {
            List<ItemEntity> itemsAround = this.getLevel().getEntitiesOfClass(ItemEntity.class, new AABB(this.getX() - 1, this.getY() - 1, this.getZ() - 1, this.getX() + 1, this.getY() + 1, this.getZ() + 1));

            for (ItemEntity itemEntity : itemsAround) {
                CSModifier.Instance pickUpModifier = shouldPickUpModifierItem(itemEntity.getItem());
                //Pair<CSModifier.Instance, Integer> pickUpModifier = shouldPickUpModifierItem(itemEntity.getItem());

                if(pickUpModifier != null){

                    //finding the modifier on the soldier to apply
                    CSModifier.Instance modifierInstanceOnSoldier = null;
                    for (CSModifier.Instance inst : this.getModifiers()) {
                        if (inst.getModifier() == pickUpModifier.getModifier()) {
                            modifierInstanceOnSoldier = inst;
                        }
                    }

                    //if null, we are adding a new modifier. if not, we are stacking it.
                    //the shouldPickUp() checks for stacking, so we dont have to do that here
                    if(modifierInstanceOnSoldier == null){
                        this.addModifier(pickUpModifier);
                    }else{
                        this.stackModifier(pickUpModifier, modifierInstanceOnSoldier);
                    }

                    pickUpModifier.getModifier().onModifierAdded(this, pickUpModifier);
                    itemEntity.getItem().shrink(pickUpModifier.getAmount());
                    this.getLevel().playSound(null, this.blockPosition (), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1, 1);

                }
            }

            //Calling on Modifier Tick
            if (!this.getModifiers().isEmpty()) {
                for (int i = 0; i < this.getModifiers().size(); i++) {
                    CSModifier.Instance instance = this.getModifiers().get(i);
                    if (instance != null) {
                        if (instance.getAmount() <= 0) {
                            this.removeModifier(instance);
                            break;
                        }
                        instance.getModifier().onModifierTick(this, instance);
                    } else {
                        break;
                    }
                }
            }
        }

        //Handle shouldStickToPosition
        if (this.shouldStickToPosition) {
            this.setPos(this.stickingPosition);
        }

        if (this.modifiersChanged && !this.getLevel().isClientSide) {
            cacheModifiersForSending();
            this.modifiersChanged = false;
        }

        if (!this.getLevel().isClientSide()) {
            syncToClient();
        }
    }

    private void syncToClient() {
        if (pipeline != null && !pipeline.isEmpty()) {
            //System.out.println("pipeline = " + pipeline);
            Networking.sendAll(new SoldierPipelinePacket(this, pipeline));
            pipeline = new CompoundTag();
        }
    }

    public void onPipeline(CompoundTag pipeline) {
        if (pipeline.contains("Modifiers", Tag.TAG_LIST)) {
            synchronized (lock) {
                modifiers.clear();
                pipeline.getList("Modifiers", Tag.TAG_COMPOUND).forEach(tag -> {
                    if (tag instanceof CompoundTag modifierTag) {
                        ResourceLocation type = new ResourceLocation(modifierTag.getString("Type"));
                        CSModifier modifier = Registration.getModifierRegistry().getValue(type);
                        int amount = modifierTag.getInt("Amount");
                        this.addModifier(new CSModifier.Instance(modifier, amount));
                    }
                });
            }
        }
        if (pipeline.contains("ShouldStickToPosition", Tag.TAG_BYTE)) {
            this.shouldStickToPosition = pipeline.getBoolean("ShouldStickToPosition");
        }
        if (pipeline.contains("StickingPosition", Tag.TAG_COMPOUND)) {
            this.stickingPosition = new Vec3(pipeline.getCompound("StickingPosition").getFloat("X"), pipeline.getCompound("StickingPosition").getFloat("Y"), pipeline.getCompound("StickingPosition").getFloat("Z"));
        }
        if (pipeline.contains("HostileAgainstItsOwnKind", Tag.TAG_BYTE)) {
            this.hostileAgainstItsOwnKind = pipeline.getBoolean("HostileAgainstItsOwnKind");
        }
        if (pipeline.contains("FullBright", Tag.TAG_BYTE)) {
            this.fullBright = pipeline.getBoolean("FullBright");
        }
    }

    public CSModifier.Instance shouldPickUpModifierItem(ItemStack pickUpStack){
        int pickUpAmount;
        CSModifier pickUpModifier = null;

        //find modifier for the item
        for (CSModifier modifierFromRegistry : Registration.getModifierRegistry().getValues()){
            if(modifierFromRegistry.getModifierItem() == pickUpStack.getItem()){
                //found modifier for item
                pickUpModifier = modifierFromRegistry;
            }
        }

        //if there is no modifier for the item, return null, no need to further code execution
        if(pickUpModifier == null){
            return null;
        }

        // check if the soldier has the modifier already
        CSModifier.Instance modifierInstanceOnSoldier = null;
        for (CSModifier.Instance inst : this.getModifiers()) {
            if (inst.getModifier() == pickUpModifier) {
                modifierInstanceOnSoldier = inst;
            }
        }

        //if there is a modifier, decide if soldier can pick up, if can't return null
        if(canEquipModifier(pickUpModifier, modifierInstanceOnSoldier)){

            if(modifierInstanceOnSoldier != null){ //soldier contains the modifier
                if(pickUpModifier.canBeStacked()){
                    pickUpAmount = Math.min(pickUpStack.getCount(), pickUpModifier.getMaxStackingLimit() - modifierInstanceOnSoldier.getAmount());
                }else{ // if it has the modifier but cant be stacked.... no pickup return null
                    return null;
                }
            }else{ //soldier doesn't have this modifier yet
                if(pickUpModifier.canBeStacked()){
                    pickUpAmount = Math.min(pickUpStack.getCount(), pickUpModifier.getMaxStackingLimit());
                }else{
                    pickUpAmount = 1;
                }
            }
        }else{
            return null;
        }


        return new CSModifier.Instance(pickUpModifier, pickUpAmount);
    }

    public boolean canEquipModifier(CSModifier modifier, CSModifier.Instance modifierOnSoldier){
        //check if the soldier has the modifier, if it cant be stacked it cant be picked up
        if(modifierOnSoldier != null){
            if(!modifierOnSoldier.getModifier().canBeStacked()){
                return false;
            }else{
                //check if it has space to stack; if it can be stacked but doesnt have more space to fit, dont pick up
                return modifierOnSoldier.getAmount() < modifierOnSoldier.getModifier().getMaxStackingLimit();
            }
        }else{
            switch (modifier.getModifierType()) {
                case MAIN_HAND, MAIN_HAND_AMOUNT_BOOST_ITEM, MAIN_HAND_BOOST_ITEM:
                    return this.getMainHandItem().isEmpty();
                case OFF_HAND, OFF_HAND_BOOST_ITEM, OFF_HAND_INF_BOOST_COMBINED:
                    return this.getOffhandItem().isEmpty();
                case ANY_HAND_AMOUNT_BOOST_ITEM, ANY_HAND_BOOST_ITEM, BOTH_HANDS:
                    return this.getMainHandItem().isEmpty() || this.getOffhandItem().isEmpty();
            }
        }

        return true;
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity p_19971_, Entity p_19972_) {
        super.doEnchantDamageEffects(p_19971_, p_19972_);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float knockbackAmount = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);

        if (!this.getModifiers().isEmpty()) {
            for (CSModifier.Instance inst : this.getModifiers()) {
                if (inst != null) {
                    inst.getModifier().onModifierAttack(this, entity, inst);
                    if (inst.getModifier() instanceof DamageBonusCSModifier) {
                        attackDamage += ((DamageBonusCSModifier) inst.getModifier()).getDamageBonus();
                    }
                } else {
                    break;
                }
            }
        }

        if (entity instanceof LivingEntity) {
            attackDamage += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity) entity).getMobType());
            knockbackAmount += (float) EnchantmentHelper.getKnockbackBonus(this);
        }

        boolean flag = entity.hurt(DamageSource.mobAttack(this), attackDamage);
        if (flag) {
            if (knockbackAmount > 0.0F && entity instanceof LivingEntity) {
                ((LivingEntity) entity).knockback(knockbackAmount * 0.5F, Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(this.getYRot() * ((float) Math.PI / 180F)));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
            }

            this.doEnchantDamageEffects(this, entity);
            this.setLastHurtMob(entity);
        }


        return flag;
    }

    @Override
    public SynchedEntityData getEntityData() {
        return super.getEntityData();
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float damageAmount) {
        DamageSource newSource = damageSource;
        float newDamage = damageAmount;
        if (!this.getModifiers().isEmpty()) {
            for (CSModifier.Instance inst : this.getModifiers()) {
                if (inst != null) {
                    Pair<DamageSource, Float> pair = inst.getModifier().onModifierHurt(this, damageSource, damageAmount, inst);
                    if (pair.getA() != null && pair.getB() != null) {
                        newSource = pair.getA();
                        newDamage = pair.getB();
                    }
                } else {
                    break;
                }
            }
        }
        return super.hurt(newSource, newDamage);
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        if (!this.getModifiers().isEmpty()) {
            for (CSModifier.Instance inst : this.getModifiers()) {
                if (inst != null) {
                    inst.getModifier().onModifierDeath(damageSource, this, inst);
                } else {
                    break;
                }
            }
        }

        super.die(damageSource);
    }


    public boolean hasModifier(CSModifier csModifier) {
        boolean retVal = false;
        for (CSModifier.Instance modifier : this.getModifiers()) {
            if (modifier.getModifier() == csModifier) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    public boolean isFullBright() {
        return fullBright;
    }

    public void setFullBright(boolean fullBright) {
        this.fullBright = fullBright;
    }
}
