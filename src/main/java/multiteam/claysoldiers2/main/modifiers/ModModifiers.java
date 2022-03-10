package multiteam.claysoldiers2.main.modifiers;

import multiteam.claysoldiers2.main.modifiers.defaultModifiers.*;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.modifiers.modifier.InfEffectCSModifier;
import multiteam.claysoldiers2.main.util.ExceptionUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static multiteam.claysoldiers2.main.Registration.MODIFIERS;

public final class ModModifiers {
    private static Supplier<IForgeRegistry<CSModifier>> registry;

    private ModModifiers() {
        throw ExceptionUtils.utilityClassException();
    }

    public static final RegistryObject<CSModifier> BLAZEROD_MAIN = register("blazerod_main", () -> new BlazeRodModifier(
            CSModifier.ModifierType.MAIN_HAND,
            Items.BLAZE_ROD,
            "damage_boost_fire_aspect",
            new Color(0xff8000),
            new ArrayList<>()
            ));

    public static final RegistryObject<CSModifier> BOWL_SECOND = register("bowl_second", () -> new BowlModifier(
            CSModifier.ModifierType.OFF_HAND,
            Items.BOWL,
            "shield",
            new Color(0x4d2515),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> BRICK_BOOST = register("brick_boost", () -> new BrickModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.BRICK,
            "heaviness",
            new Color(0xa63b26),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> CLAY_HEAL_SECOND = register("clay_heal_second", () -> new HealingModifier(
            CSModifier.ModifierType.OFF_HAND_BOOST_ITEM,
            Items.CLAY_BALL,
            "clay_healer",
            new Color(0x9499a4),
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>(),
            1f
    ));

    public static final RegistryObject<CSModifier> FEATHER_BOOST = register("feather_boost", () -> new InfEffectCSModifier(
            new MobEffectInstance(MobEffects.SLOW_FALLING, 12),
            Items.FEATHER,
            "slow_falling",
            new Color(0xe6edf5),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_ALLIUM = register("flower_boost_allium", () -> new FlowerModifier(
            Items.ALLIUM,
            "suspicious_effect_allium",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_AZURE_BLUET = register("flower_boost_azure_bluet", () -> new FlowerModifier(
            Items.AZURE_BLUET,
            "suspicious_effect_azure_bluet",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.BLINDNESS, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_BLUE_ORCHID = register("flower_boost_blue_orchid", () -> new FlowerModifier(
            Items.BLUE_ORCHID,
            "suspicious_effect_blue_orchid",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.SATURATION, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_DANDELION = register("flower_boost_dandelion", () -> new FlowerModifier(
            Items.DANDELION,
            "suspicious_effect_dandelion",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.SATURATION, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_CORNFLOWER = register("flower_boost_cornflower", () -> new FlowerModifier(
            Items.CORNFLOWER,
            "suspicious_effect_cornflower",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.JUMP, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_LILY_OF_THE_VALLEY = register("flower_boost_lily_of_the_valley", () -> new FlowerModifier(
            Items.LILY_OF_THE_VALLEY,
            "suspicious_effect_lily_of_the_valley",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.POISON, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_OXEYE_DAISY = register("flower_boost_oxeye_daisy", () -> new FlowerModifier(
            Items.OXEYE_DAISY,
            "suspicious_effect_oxeye_daisy",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.REGENERATION, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_POPPY = register("flower_boost_poppy", () -> new FlowerModifier(
            Items.POPPY,
            "suspicious_effect_poppy",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_ORANGE_TULIP = register("flower_boost_orange_tulip", () -> new FlowerModifier(
            Items.ORANGE_TULIP,
            "suspicious_effect_orange_tulip",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.WEAKNESS, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_PINK_TULIP = register("flower_boost_ping_tulip", () -> new FlowerModifier(
            Items.PINK_TULIP,
            "suspicious_effect_pink_tulip",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.WEAKNESS, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_RED_TULIP = register("flower_boost_red_tulip", () -> new FlowerModifier(
            Items.RED_TULIP,
            "suspicious_effect_red_tulip",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.WEAKNESS, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_WHITE_TULIP = register("flower_boost_white_tulip", () -> new FlowerModifier(
            Items.WHITE_TULIP,
            "suspicious_effect_white_tulip",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.WEAKNESS, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLOWER_BOOST_WITHER_ROSE = register("flower_boost_wither_rose", () -> new FlowerModifier(
            Items.WITHER_ROSE,
            "suspicious_effect_wither_rose",
            new Color(0x27E25C),
            new MobEffectInstance(MobEffects.WITHER, 100),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> GLISTERING_HEAL_SECOND = register("glistering_heal_second", () -> new HealingModifier(
            CSModifier.ModifierType.OFF_HAND_BOOST_ITEM,
            Items.GLISTERING_MELON_SLICE,
            "glistering_healer",
            new Color(0xFFF97E),
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>(),
            2f
    ));

    public static final RegistryObject<CSModifier> KING_BOOST = register("king_boost", () -> new GoldNuggetModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.GOLD_NUGGET,
            "king",
            new Color(0xFFB700),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> GRAVEL_MAIN = register("gravel_main", () -> new GravelModifier(
            CSModifier.ModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
            Items.GRAVEL,
            "gravel_ranged",
            new Color(0x3B3C3D),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> LEATHER_BOOST = register("leather_boost", () -> new LeatherModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.LEATHER,
            "leather_armor",
            new Color(0x703923),
            List.of(ModModifiers.RABBIT_HIDE_BOOST)
    ));

    public static final RegistryObject<CSModifier> MAGMA_BOOST = register("magma_boost", () -> new MagmaCreamModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.MAGMA_CREAM,
            "critical_damage",
            new Color(0xFF6200),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> PAPER_BOOST = register("paper_boost", () -> new PaperModifier(
            CSModifier.ModifierType.INF_BOOST_COSMETIC,
            Items.PAPER,
            "cape",
            new Color(0xDEE1E8),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> RABBIT_HIDE_BOOST = register("rabbit_hide_boost", () -> new LeatherModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.RABBIT_HIDE,
            "rabbit_armor",
            new Color(0x9B734A),
            List.of(ModModifiers.LEATHER_BOOST)
    ));

    public static final RegistryObject<CSModifier> SCUTE_BOOST = register("scute_boost", () -> new ScuteModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.SCUTE,
            "diver",
            new Color(0x0DBD41),
            List.of(ModModifiers.LILYPAD_BOOST)
    ));

    public static final RegistryObject<CSModifier> SEEDS_MAIN = register("seeds_main", () -> new WheatSeedsModifier(
            CSModifier.ModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
            Items.WHEAT_SEEDS,
            "seeds_ranged",
            new Color(0x30812A),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> STICK_MAIN = register("stick_main", () -> new StickModifier(
            CSModifier.ModifierType.MAIN_HAND,
            Items.STICK,
            "damage_boost",
            new Color(0x5D350E),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> WOOL_BOOST = register("wool_boost", () -> new WoolModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.WHITE_WOOL,
            "knockback_resistance",
            new Color(0xFFFFFF),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FLINT_BOOST = register("flint_boost", () -> new FlintModifier(
            CSModifier.ModifierType.INF_BOOST_COMBINED,
            Items.FLINT,
            "flint_damage_boost",
            new Color(0x626262),
            List.of(ModModifiers.IRON_BOOST)
    ));

    public static final RegistryObject<CSModifier> IRON_BOOST = register("iron_boost", () -> new IronNuggetModifier(
            CSModifier.ModifierType.INF_BOOST_COMBINED,
            Items.IRON_NUGGET,
            "iron_damage_boost",
            new Color(0xAFAFAF),
            List.of(ModModifiers.FLINT_BOOST)
    ));

    public static final RegistryObject<CSModifier> REDSTONE_ANY = register("redstone_any", () -> new RedstoneModifier(
            CSModifier.ModifierType.ANY_HAND_BOOST_ITEM,
            Items.REDSTONE,
            "blinding",
            new Color(0xFF0000),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> SLIME_ANY = register("slime_any", () -> new SlimeModifier(
            CSModifier.ModifierType.ANY_HAND_BOOST_ITEM,
            Items.SLIME_BALL,
            "sticker",
            new Color(0x69AF11),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> GLOWSTONE_BOOST = register("glowstone_boost", () -> new GlowstoneDustModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.GLOWSTONE_DUST,
            "glowing",
            new Color(0xE5BE5F),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> MILK = register("milk", () -> new MilkBucketModifier(
            CSModifier.ModifierType.CANCEL,
            Items.MILK_BUCKET,
            "cancel",
            new Color(0xE9EEF1),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> GUNPOWDER_BOOST = register("gunpowder_boost", () -> new GunpowderModifier(
            CSModifier.ModifierType.BOOST_ITEM,
            Items.GUNPOWDER,
            "explosive",
            new Color(0x5D5A5A),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> COAL_BOOST = register("coal_boost", () -> new CoalModifier(
            CSModifier.ModifierType.OFF_HAND_INF_BOOST_COMBINED,
            Items.COAL,
            "fiery_fuel",
            new Color(0x3A3A3A),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> LILYPAD_BOOST = register("lilypad_boost", () -> new LilypadModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.LILY_PAD,
            "water_walker",
            new Color(0x355D03),
            List.of(ModModifiers.SCUTE_BOOST)
    ));

    public static final RegistryObject<CSModifier> GLASS_PANE_BOOST = register("glass_pane_boost", () -> new GlassPaneModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.GLASS_PANE,
            "glasses",
            new Color(0xFFFFFF),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> DIAMOND_BOOST = register("diamond_boost", () -> new DiamondModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.DIAMOND,
            "queen",
            new Color(0x0EC9AF),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> SHEAR_BOTH = register("shear_both", () -> new ShearsModifier(
            CSModifier.ModifierType.BOTH_HANDS,
            Items.SHEARS,
            "scissor_hands",
            new Color(0xC5BDBB),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> ENDEREYE_BOOST = register("endereye_boost", () -> new EnderEyeModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.ENDER_EYE,
            "allseeing_sight",
            new Color(0xff8000),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> SUGAR_BOOST = register("sugar_boost", () -> new SugarModifier(
            CSModifier.ModifierType.INF_EFFECT,
            Items.SUGAR,
            "speedey",
            new Color(0xECEEEF),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> EGG_BOOST = register("egg_boost", () -> new EggModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.EGG,
            "egg_cover",
            new Color(0xD9BCA3),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> FIRECHARGE_MAIN = register("firecharge_main", () -> new FireChargeModifier(
            CSModifier.ModifierType.MAIN_HAND_BOOST_ITEM,
            Items.FIRE_CHARGE,
            "fiery_hands",
            new Color(0x9D3907),
            true,
            CSAPI.Settings.stackablesLimit,
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> WART_BOOST = register("wart_boost", () -> new NetherWartModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.NETHER_WART,
            "crazy_warted",
            new Color(0xa62530),
            new ArrayList<>()
    ));

    public static final RegistryObject<CSModifier> MUSHROOM_RED = register("mushroom_red", () -> new MushroomModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.RED_MUSHROOM,
            "red_shroomer",
            new Color(0xdf1212),
            List.of(ModModifiers.MUSHROOM_BROWN)
    ));
    public static final RegistryObject<CSModifier> MUSHROOM_BROWN = register("mushroom_brown", () -> new MushroomModifier(
            CSModifier.ModifierType.INF_BOOST,
            Items.BROWN_MUSHROOM,
            "brown_shroomer",
            new Color(0x8f6c54),
            List.of(ModModifiers.MUSHROOM_RED)
    ));

    private static <T extends CSModifier> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return MODIFIERS.register(name, supplier);
    }

    public static void register() {

    }
}
