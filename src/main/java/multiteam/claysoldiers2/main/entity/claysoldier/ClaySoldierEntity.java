package multiteam.claysoldiers2.main.entity.claysoldier;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.entity.ai.ClaySoldierAttackGoal;
import multiteam.claysoldiers2.main.entity.base.ClayEntityBase;
import multiteam.claysoldiers2.main.item.ModItems;
import multiteam.claysoldiers2.main.modifiers.CSAPI;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.DamageBonusCSModifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClaySoldierEntity extends ClayEntityBase {

    private List<CSModifier.Instance> modifiers = new ArrayList<>();

    public boolean isInvisibleToOthers = false;
    public boolean canSeeInvisibleToOthers = false;
    public boolean hostileAgainstItsOwnKind = false;

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world, CSAPI.ClaySoldierMaterial material) {
        super(entity, world, material);
        this.setMaterial(material);
    }

    public ClaySoldierEntity(EntityType<? extends PathfinderMob> entity, Level world) {
        super(entity, world, CSAPI.ClaySoldierMaterial.CLAY_SOLDIER);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, CSAPI.Settings.soldierViewDistance).add(Attributes.JUMP_STRENGTH, 1.0d).add(Attributes.KNOCKBACK_RESISTANCE, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ClaySoldierAttackGoal(this, 2, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ClaySoldierEntity.class, 0, true, false, (targetEntity) -> {
            if (targetEntity instanceof ClaySoldierEntity targetedSoldier) {
                return (!targetedSoldier.isMatchingMaterial(this) || this.hostileAgainstItsOwnKind) && (!targetedSoldier.isInvisibleToOthers || this.canSeeInvisibleToOthers);
            }
            return false;
        }));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
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
    public Item getBrickedForm() {
        return ModItems.BRICKED_SOLDIER.get();
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



    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag data) {
        super.addAdditionalSaveData(data);

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



    @Override
    public void tick() {
        super.tick();

        ClaySoldierEntity soldier = this;
        Level level = soldier.getLevel();

        //Picking up items
        if (!level.isClientSide) {

            List<ItemEntity> itemsAround = level.getEntitiesOfClass(ItemEntity.class, new AABB(soldier.getX() - 1, soldier.getY() - 1, soldier.getZ() - 1, soldier.getX() + 1, soldier.getY() + 1, soldier.getZ() + 1));

            for (ItemEntity itemEntity : itemsAround) {
                Pair<CSModifier.Instance, Integer> pickUpModifier = shouldPickUp(itemEntity.getItem());

                if(pickUpModifier.getB() > 0 && pickUpModifier.getA() != null){
                    if(this.getModifiers().contains(pickUpModifier.getA())){ //If we are adding more to a contained modifier
                        int indexOfOldModifier = this.getModifiers().indexOf(pickUpModifier.getA());
                        CSModifier.Instance oldModifier = this.getModifiers().get(indexOfOldModifier);

                        int newAmount = oldModifier.getAmount()+pickUpModifier.getB();

                        this.getModifiers().set(indexOfOldModifier, new CSModifier.Instance(oldModifier.getModifier(), newAmount));
                    }else{ // If we are adding a new modifier
                        this.getModifiers().add(pickUpModifier.getA());
                    }

                    pickUpModifier.getA().getModifier().onModifierAdded(soldier, pickUpModifier.getA());

                    itemEntity.getItem().shrink(pickUpModifier.getB());
                    level.playSound(null, soldier.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1, 1);
                }

            }
        }

        //Calling on Modifier Tick
        if (!level.isClientSide && !this.getModifiers().isEmpty()) {
            for (int i = 0; i < this.getModifiers().size(); i++) {
                CSModifier.Instance instance = this.getModifiers().get(i);
                if (instance != null) {
                    if(instance.getAmount() <= 0){
                        this.removeModifier(instance);
                        return;
                    }
                    instance.getModifier().onModifierTick(soldier, instance);
                } else {
                    return;
                }
            }
        }

    }

    public Pair<CSModifier.Instance, Integer> shouldPickUp(ItemStack stack) {
        int pickUpAmount = 0;
        CSModifier.Instance retInstance = null;

        for (CSModifier modifier : Registration.getModifierRegistry().getValues()) {
            if (modifier.getModifierItem() == stack.getItem()) {
                CSModifier.Instance thisModifierInstance = null;
                for (CSModifier.Instance inst : this.getModifiers()) {
                    if(inst.getModifier() == modifier){
                        thisModifierInstance = inst;
                    }
                }

                if (canEquip(modifier)) {
                    if (this.getModifiers().contains(thisModifierInstance)){
                        if(modifier.canBeStacked()){
                            pickUpAmount = Math.min(stack.getCount(), modifier.getMaxStackingLimit()-thisModifierInstance.getAmount());
                            retInstance = thisModifierInstance;
                        }
                    }else{
                        if(modifier.canBeStacked()){
                            pickUpAmount = Math.min(stack.getCount(), modifier.getMaxStackingLimit());
                            retInstance = new CSModifier.Instance(modifier, pickUpAmount);
                        }else{
                            pickUpAmount = 1;
                            retInstance = new CSModifier.Instance(modifier, pickUpAmount);
                        }
                    }
                }

            }
        }
        return new Pair<>(retInstance, pickUpAmount);
    }

    public boolean canEquip(CSModifier modifier){
        boolean ret = true;

        //Testing if the soldier has modifiers that are incompatible by default with this modifier
        if (!modifier.getIncompatibleModifiers().isEmpty()) {
            for (CSModifier.Instance modif : this.getModifiers()) {
                if (modifier.getIncompatibleModifiers().contains(modif.getModifier())) {
                    ret = false;
                }
            }
        }

        //TODO testing the type of the modifier if it can be picked up (aka hand slot is occupied or something)
        //switch (modifier.getModifierType())
        //also if its a main hand item or an off hand item, setting the handslot to have the item of the modifier

        //This has been already started, but i think its incomplete yet!

        switch (modifier.getModifierType()){
            case MAIN_HAND, MAIN_HAND_AMOUNT_BOOST_ITEM, MAIN_HAND_BOOST_ITEM:
                    if(!this.getMainHandItem().isEmpty() || (this.getMainHandItem().getItem() != modifier.getModifierItem() && modifier.canBeStacked())){
                        ret = false;
                    }
                break;
            case OFF_HAND, OFF_HAND_BOOST_ITEM, OFF_HAND_INF_BOOST_COMBINED:
                    if(!this.getOffhandItem().isEmpty() || (this.getOffhandItem().getItem() != modifier.getModifierItem() && modifier.canBeStacked())){
                        ret = false;
                    }
                break;
            case ANY_HAND_AMOUNT_BOOST_ITEM, ANY_HAND_BOOST_ITEM, BOTH_HANDS:
                    if(!this.getMainHandItem().isEmpty() && !this.getOffhandItem().isEmpty()){
                        ret = false;
                    }
                break;
        }


        return ret;
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity p_19971_, Entity p_19972_) {
        super.doEnchantDamageEffects(p_19971_, p_19972_);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        float attackDamage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float knockbackAmount = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);

        if(!this.getModifiers().isEmpty()){
            for (CSModifier.Instance inst : this.getModifiers()) {
                if(inst !=null){
                    inst.getModifier().onModifierAttack(this, entity, inst);
                    if(inst.getModifier() instanceof DamageBonusCSModifier){
                        attackDamage+=((DamageBonusCSModifier)inst.getModifier()).getDamageBonus();
                    }
                }else{
                    break;
                }
            }
        }

        if (entity instanceof LivingEntity) {
            attackDamage += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity)entity).getMobType());
            knockbackAmount += (float)EnchantmentHelper.getKnockbackBonus(this);
        }

        boolean flag = entity.hurt(DamageSource.mobAttack(this), attackDamage);
        if (flag) {
            if (knockbackAmount > 0.0F && entity instanceof LivingEntity) {
                ((LivingEntity)entity).knockback((double)(knockbackAmount * 0.5F), (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
            }

            this.doEnchantDamageEffects(this, entity);
            this.setLastHurtMob(entity);
        }


        return flag;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damageAmount) {
        DamageSource newSource = damageSource;
        float newDamage = damageAmount;
        if(!this.getModifiers().isEmpty()){
            for (CSModifier.Instance inst : this.getModifiers()) {
                if(inst !=null){
                    Pair<DamageSource, Float> pair =inst.getModifier().onModifierHurt(this, damageSource, damageAmount, inst);
                    newSource = pair.getA();
                    newDamage = pair.getB();
                }else{
                    break;
                }
            }
        }
        return super.hurt(newSource, newDamage);
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        if(!this.getModifiers().isEmpty()){
            for (CSModifier.Instance inst : this.getModifiers()) {
                if(inst !=null){
                    inst.getModifier().onModifierDeath(damageSource, this, inst);
                }else{
                    break;
                }
            }
        }

        super.die(damageSource);
    }

    //TODO make this actually do something
    public void stickToPosition(){

    }
}
