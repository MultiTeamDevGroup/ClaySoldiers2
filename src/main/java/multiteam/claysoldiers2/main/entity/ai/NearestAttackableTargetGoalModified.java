package multiteam.claysoldiers2.main.entity.ai;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class NearestAttackableTargetGoalModified<T extends LivingEntity> extends TargetGoal {

    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final Class<T> targetType;
    protected final int randomInterval;
    @javax.annotation.Nullable
    protected LivingEntity target;
    protected TargetingConditions targetConditions;

    public NearestAttackableTargetGoalModified(Mob mob, Class<T> targetType, boolean mustSee) {
        this(mob, targetType, 10, mustSee, false, (Predicate<LivingEntity>)null);
    }

    public NearestAttackableTargetGoalModified(Mob mob, Class<T> targetType, boolean mustSee, Predicate<LivingEntity> targetCondition) {
        this(mob, targetType, 10, mustSee, false, targetCondition);
    }

    public NearestAttackableTargetGoalModified(Mob mob, Class<T> targetType, boolean mustSee, boolean mustReach) {
        this(mob, targetType, 10, mustSee, mustReach, (Predicate<LivingEntity>)null);
    }

    public NearestAttackableTargetGoalModified(Mob mob, Class<T> targetType, int randomInterval, boolean mustSee, boolean mustReach, @javax.annotation.Nullable Predicate<LivingEntity> targetCondition) {
        super(mob, mustSee, mustReach);
        this.targetType = targetType;
        this.randomInterval = reducedTickDelay(randomInterval);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(targetCondition);
    }

    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double inflationSize) {
        return this.mob.getBoundingBox().inflate(inflationSize, 4.0D, inflationSize);
    }

    protected void findTarget() {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {

            this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance())), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }

    public void setTarget(@Nullable LivingEntity target) {
        this.target = target;
    }
}
