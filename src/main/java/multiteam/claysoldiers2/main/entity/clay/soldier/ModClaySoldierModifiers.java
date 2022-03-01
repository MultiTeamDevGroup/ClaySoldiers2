package multiteam.claysoldiers2.main.entity.clay.soldier;

import multiteam.claysoldiers2.main.entity.clay.soldier.ClaySoldierAPI.ClaySoldierModifierType;
import multiteam.claysoldiers2.main.util.ExceptionUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static multiteam.claysoldiers2.main.Registration.MODIFIERS;

@SuppressWarnings("unused")
public final class ModClaySoldierModifiers {
    private static Supplier<IForgeRegistry<ClaySoldierModifier>> registry;

    private ModClaySoldierModifiers() {
        throw ExceptionUtils.utilityClassException();
    }

    public static final RegistryObject<ClaySoldierModifier> BLAZEROD_MAIN = register("blazerod_main", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.MAIN_HAND,
            Items.BLAZE_ROD,
            "damage_boost_fire_aspect",
            new Color(0xff8000),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> attacked.setSecondsOnFire(5),
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> BOWL_SECOND = register("bowl_second", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.OFF_HAND,
            Items.BOWL,
            "shield",
            new Color(0x4d2515),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> BRICK_BOOST = register("brick_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.BRICK,
            "heaviness",
            new Color(0xa63b26),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> CLAY_HEAL_SECOND = register("clay_heal_second", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.OFF_HAND_BOOST_ITEM,
            Items.CLAY_BALL,
            "clay_healer",
            new Color(0x9499a4),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FEATHER_BOOST = register("feather_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_EFFECT,
            Items.FEATHER,
            "slow_falling",
            new Color(0xe6edf5),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_ALLIUM = register("flower_boost_allium", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.ALLIUM,
            "suspicious_effect_allium",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty() && thisModifier != null) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_AZURE_BLUET = register("flower_boost_azure_bluet", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.AZURE_BLUET,
            "suspicious_effect_azure_bluet",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_BLUE_ORCHID = register("flower_boost_blue_orchid", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.BLUE_ORCHID,
            "suspicious_effect_blue_orchid",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.SATURATION, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_DANDELION = register("flower_boost_dandelion", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.DANDELION,
            "suspicious_effect_dandelion",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.SATURATION, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_CORNFLOWER = register("flower_boost_cornflower", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.CORNFLOWER,
            "suspicious_effect_cornflower",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.JUMP, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_LILY_OF_THE_VALLEY = register("flower_boost_lily_of_the_valley", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.LILY_OF_THE_VALLEY,
            "suspicious_effect_lily_of_the_valley",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_OXEYE_DAISY = register("flower_boost_oxeye_daisy", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.OXEYE_DAISY,
            "suspicious_effect_oxeye_daisy",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_POPPY = register("flower_boost_poppy", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.POPPY,
            "suspicious_effect_poppy",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_ORANGE_TULIP = register("flower_boost_orange_tulip", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.ORANGE_TULIP,
            "suspicious_effect_orange_tulip",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_PINK_TULIP = register("flower_boost_ping_tulip", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.PINK_TULIP,
            "suspicious_effect_pink_tulip",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_RED_TULIP = register("flower_boost_red_tulip", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.RED_TULIP,
            "suspicious_effect_red_tulip",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_WHITE_TULIP = register("flower_boost_white_tulip", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.WHITE_TULIP,
            "suspicious_effect_white_tulip",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLOWER_BOOST_WITHER_ROSE = register("flower_boost_wither_rose", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.EFFECT,
            Items.WITHER_ROSE,
            "suspicious_effect_wither_rose",
            new Color(0x27E25C),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    soldier.addEffect(new MobEffectInstance(MobEffects.WITHER, 100));
                    soldier.removeModifier(thisModifier);
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> GLISTERING_HEAL_SECOND = register("glistering_heal_second", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.OFF_HAND_BOOST_ITEM,
            Items.GLISTERING_MELON_SLICE,
            "glistering_healer",
            new Color(0xFFF97E),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> KING_BOOST = register("king_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.GOLD_NUGGET,
            "king",
            new Color(0xFFB700),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> GRAVEL_MAIN = register("gravel_main", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
            Items.GRAVEL,
            "gravel_ranged",
            new Color(0x3B3C3D),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> LEATHER_BOOST = register("leather_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.LEATHER,
            "leather_armor",
            new Color(0x703923),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> MAGMA_BOOST = register("magma_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.MAGMA_CREAM,
            "critical_damage",
            new Color(0xFF6200),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> PAPER_BOOST = register("paper_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST_COSMETIC,
            Items.PAPER,
            "cape",
            new Color(0xDEE1E8),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> RABBIT_HIDE_BOOST = register("rabbit_hide_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.RABBIT_HIDE,
            "rabbit_armor",
            new Color(0x9B734A),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> SCUTE_BOOST = register("scute_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.SCUTE,
            "diver",
            new Color(0x0DBD41),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> SEEDS_MAIN = register("seeds_main", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.MAIN_HAND_AMOUNT_BOOST_ITEM,
            Items.WHEAT_SEEDS,
            "seeds_ranged",
            new Color(0x30812A),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> STICK_MAIN = register("stick_main", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.MAIN_HAND,
            Items.STICK,
            "damage_boost",
            new Color(0x5D350E),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> WOOL_BOOST = register("wool_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.WHITE_WOOL,
            "knockback_resistance",
            new Color(0xFFFFFF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FLINT_BOOST = register("flint_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST_COMBINED,
            Items.FLINT,
            "flint_damage_boost",
            new Color(0x626262),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> IRON_BOOST = register("iron_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST_COMBINED,
            Items.IRON_NUGGET,
            "iron_damage_boost",
            new Color(0xAFAFAF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> REDSTONE_ANY = register("redstone_any", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.ANY_HAND_BOOST_ITEM,
            Items.REDSTONE,
            "blinding",
            new Color(0xFF0000),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> SLIME_ANY = register("slime_any", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.ANY_HAND_BOOST_ITEM,
            Items.SLIME_BALL,
            "sticker",
            new Color(0x69AF11),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> GLOWSTONE_BOOST = register("glowstone_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.GLOWSTONE_DUST,
            "glowing",
            new Color(0xE5BE5F),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> MILK = register("milk", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.CANCEL,
            Items.MILK_BUCKET,
            "cancel",
            new Color(0xE9EEF1),
            false,
            0,
            (soldier, thisModifier) -> {
                if (!soldier.getModifiers().isEmpty()) {
                    for (int i = 0; i < soldier.getModifiers().size(); i++) {
                        if (soldier.getModifiers().get(i) != null && soldier.getModifiers().get(i).getA().getModifierItem() != Items.MILK_BUCKET) {
                            ItemStack dropStack = new ItemStack(soldier.getModifiers().get(i).getA().getModifierItem());
                            if (soldier.getModifiers().get(i).getA().canBeStacked()) {
                                dropStack.setCount(soldier.getModifiers().get(i).getB());
                            }
                            soldier.getLevel().addFreshEntity(new ItemEntity(soldier.level, soldier.getX(), soldier.getY(), soldier.getZ(), dropStack));
                            soldier.removeModifier(soldier.getModifiers().get(i).getA());
                        } else {
                            return;
                        }
                    }

                    soldier.removeAllModifiers();
                    soldier.getLevel().addFreshEntity(new ItemEntity(soldier.level, soldier.getX(), soldier.getY(), soldier.getZ(), new ItemStack(Items.BUCKET)));
                    soldier.removeSoldier();
                }
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> GUNPOWDER_BOOST = register("gunpowder_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.BOOST_ITEM,
            Items.GUNPOWDER,
            "explosive",
            new Color(0x5D5A5A),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));

    public static final RegistryObject<ClaySoldierModifier> COAL_BOOST = register("coal_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.OFF_HAND_INF_BOOST_COMBINED,
            Items.COAL,
            "fiery_fuel",
            new Color(0x3A3A3A),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));

    public static final RegistryObject<ClaySoldierModifier> LILYPAD_BOOST = register("lilypad_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.LILY_PAD,
            "water_walker",
            new Color(0x355D03),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> GLASS_PANE_BOOST = register("glass_pane_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.GLASS_PANE,
            "glasses",
            new Color(0xFFFFFF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> DIAMOND_BOOST = register("diamond_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.DIAMOND,
            "queen",
            new Color(0x0EC9AF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> SHEAR_BOTH = register("shear_both", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.BOTH_HANDS,
            Items.SHEARS,
            "scissor_hands",
            new Color(0xC5BDBB),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> ENDEREYE_BOOST = register("endereye_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.ENDER_EYE,
            "allseeing_sight",
            new Color(0xff8000),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> SUGAR_BOOST = register("sugar_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_EFFECT,
            Items.SUGAR,
            "speedey",
            new Color(0xECEEEF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> EGG_BOOST = register("egg_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.EGG,
            "egg_cover",
            new Color(0xD9BCA3),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> FIRECHARGE_MAIN = register("firecharge_main", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.MAIN_HAND_BOOST_ITEM,
            Items.FIRE_CHARGE,
            "fiery_hands",
            new Color(0x9D3907),
            true,
            ClaySoldierAPI.Settings.stackablesLimit,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> WART_BOOST = register("wart_boost", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.INF_BOOST,
            Items.NETHER_WART,
            "crazy_warted",
            new Color(0xa62530),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            new ArrayList<>(),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> MUSHROOM_RED = register("mushroom_red", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.ANY_HAND_AMOUNT_BOOST_ITEM,
            Items.RED_MUSHROOM,
            "red_shroomer",
            new Color(0xFFFFFF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            java.util.List.of(Items.BROWN_MUSHROOM),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));
    public static final RegistryObject<ClaySoldierModifier> MUSHROOM_BROWN = register("mushroom_brown", () -> new ClaySoldierModifier(
            ClaySoldierModifierType.ANY_HAND_AMOUNT_BOOST_ITEM,
            Items.BROWN_MUSHROOM,
            "brown_shroomer",
            new Color(0xFFFFFF),
            false,
            0,
            (soldier, thisModifier) -> {
            },
            List.of(Items.RED_MUSHROOM),
            (self, thisModifier, attacked) -> {
            },
            (attacker, thisModifier, self) -> {
            }
    ));

    private static <T extends ClaySoldierModifier> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return MODIFIERS.register(name, supplier);
    }

    public static void register() {

    }
}
