package com.mactso.hardernaturalhealing.config;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.hardernaturalhealing.Main;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;


//import net.minecraft.world.entity.player.Player;



import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MyConfig {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void setHealingPerSecond(double healingPerSecond) {
		MyConfig.healingPerSecond = healingPerSecond;
	}

	public static void setMaxBonusHitPointTotems(double maxBonusHitPointTotems) {
		MyConfig.maxBonusHitPointTotems = maxBonusHitPointTotems;
	}

	public static void setAttackHealingDelayTicks(int attackHealingDelayTicks) {
		MyConfig.attackHealingDelayTicks = attackHealingDelayTicks;
	}

	public static void setMinimumFoodHealingLevel(double minimumFoodHealingLevel) {
		MyConfig.minimumFoodHealingLevel = minimumFoodHealingLevel;
	}

	public static boolean setHealingExhaustionCost(double healingExhaustionCost) {
		MyConfig.healingExhaustionCost = healingExhaustionCost;
		return true;
	}

	public static void setWakeupHealingAmount(double wakeupHealingAmount) {
		MyConfig.wakeupHealingAmount = wakeupHealingAmount;
	}

	public static void setMinimumStarvationHealth(int minimumStarvationHealth) {
		MyConfig.minimumStarvationHealth = minimumStarvationHealth;
	}

	public static int setPeacefulHunger(boolean peacefulHunger) {
		MyConfig.peacefulHunger = peacefulHunger;
		return 1; // TODO ask lupin about this.
	}

	public static void setExtraExhaustionWhenHurt(double extraExhaustionWhenHurt) {
		MyConfig.extraExhaustionWhenHurt = extraExhaustionWhenHurt;
	}

	public static int getDebugLevel() {
		return debugLevel;
	}

	public static void setDebugLevel(int debugLevel) {
		MyConfig.debugLevel = debugLevel;
	}

	public static double getHealingPerSecond() {
		return healingPerSecond;
	}

	public static double getMinimumFoodHealingLevel() {
		return minimumFoodHealingLevel;
	}

	public static double getHealingExhaustionCost() {
		return healingExhaustionCost;
	}

	public static double getWakeupHealingAmount() {
		return wakeupHealingAmount;
	}
	
	public static int getAttackHealingDelayTicks() {
		return attackHealingDelayTicks;
	}
	
	public static double getMaxBonusHitPointTotems() {
		return maxBonusHitPointTotems;
	}	

	public static void debugMsg (int level, String dMsg) {
		if (debugLevel > level-1) {
			System.out.println("L"+level + ":" + dMsg);
		}
	}

	public static void debugMsg (int level, BlockPos pos, String dMsg) {
		if (debugLevel > level-1) {
			System.out.println("L"+level+" ("+pos.getX()+","+pos.getY()+","+pos.getZ()+"): " + dMsg);
		}
	}

	public static int debugLevel;
	private static double healingPerSecond;
	private static double maxBonusHitPointTotems;
	private static int attackHealingDelayTicks;
	private static double minimumFoodHealingLevel;
	private static double healingExhaustionCost;
	private static double wakeupHealingAmount;
	private static int minimumStarvationHealth;
	private static boolean peacefulHunger;
	private static double extraExhaustionWhenHurt;
	
	public static double getExtraExhaustionWhenHurt() {
		return extraExhaustionWhenHurt;
	}

	public static int getMinimumStarvationHealth() {
		return minimumStarvationHealth;
	}

	public static boolean isPeacefulHunger() {
		return peacefulHunger;
	}

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == MyConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	public static void pushDebugValue() {
		if (debugLevel > 0) {
			System.out.println("hardernaturalhealing debugLevel:" + MyConfig.debugLevel);
		}
		COMMON.debugLevel.set(MyConfig.debugLevel);
	}

	public static void bakeConfig() {

		debugLevel = COMMON.debugLevel.get();
		attackHealingDelayTicks = COMMON.attackHealingDelayTicks.get();
		extraExhaustionWhenHurt = COMMON.extraExhaustionWhenHurt.get();
		maxBonusHitPointTotems = COMMON.maxBonusHitPointTotems.get();
		healingPerSecond = COMMON.healingPerSecond.get();
		minimumFoodHealingLevel = COMMON.minimumFoodHealingLevel.get();
		healingExhaustionCost = COMMON.healingExhaustionCost.get();
		wakeupHealingAmount = COMMON.wakeupHealingAmount.get();
		minimumStarvationHealth = COMMON.minimumStarvationHealth.get();
		peacefulHunger = COMMON.peacefulHunger.get();
		if (debugLevel > 0) {
			System.out.println("HarderNaturalHealing Debug: " + debugLevel);
		}
	}

	public static class Common {

		public final IntValue debugLevel;
		public final IntValue attackHealingDelayTicks;
		public final IntValue maxBonusHitPointTotems;
		public final DoubleValue extraExhaustionWhenHurt;
		public final DoubleValue healingPerSecond;
		public final DoubleValue minimumFoodHealingLevel;
		public final DoubleValue healingExhaustionCost;
		public final DoubleValue wakeupHealingAmount;
		public final IntValue minimumStarvationHealth;
		public final BooleanValue peacefulHunger;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("Harder Natural Healing Control Values");

			debugLevel = builder.comment("Debug Level: 0 = Off, 1 = Log, 2 = Chat+Log")
					.translation(Main.MODID + ".config." + "debugLevel")
					.defineInRange("debugLevel", () -> 0, 0, 2);

			attackHealingDelayTicks = builder.comment("Attack Healing Delay in Ticks")
					.translation(Main.MODID + ".config." + "attackHealingDelayTicks")
					.defineInRange("attackHealingDelayTicks", () -> 200, 0, 3600);

			maxBonusHitPointTotems = builder.comment("Max Bonus HitPoint Totems")
					.translation(Main.MODID + ".config." + "maxBonusHitPointTotems")
					.defineInRange("maxBonusHitPointTotems", () -> 300, 0, 1200);

			
			healingPerSecond = builder.comment("healingPerSecond")
					.translation(Main.MODID + ".config." + "healingPerSecond")
					.defineInRange("healingPerSecond", () -> 0.25, 0.0, 10.0);

			minimumFoodHealingLevel = builder.comment("minimumFoodHealingLevel")
					.translation(Main.MODID + ".config." + "minimumFoodHealingLevel")
					.defineInRange("minimumFoodHealingLevel", () -> 16.0, 0.0, 22.0);

			healingExhaustionCost = builder.comment("healingExhaustionCost - Hunger exhausted per healing event.")
					.translation(Main.MODID + ".config." + "healingExhaustionCost")
					.defineInRange("healingExhaustionCost", () -> 1.0, 0.0, 10.0);

			wakeupHealingAmount = builder.comment("wakeupHealingAmount")
					.translation(Main.MODID + ".config." + "wakeupHealingAmount")
					.defineInRange("wakeupHealingAmount", () -> 4.0, 0.0, 10.0);
			
			extraExhaustionWhenHurt = builder.comment("extraExhaustionWhenHurt")
					.translation(Main.MODID + ".config." + "extraExhaustionWhenHurt")
					.defineInRange("extraExhaustionWhenHurt", () -> 0.0125, 0.0, 1.0);
					
			minimumStarvationHealth = builder.comment("minimum hit points for peaceful mode starvation.")
					.translation(Main.MODID + ".config." + "minimumStarvationHealth")
					.defineInRange("minimumStarvationHealth", () -> 0, 0 , 20);

			peacefulHunger = builder
					.comment("Can the player get hungry and maybe even starve to death in peaceful mode.")
					.translation(Main.MODID + ".config." + "peacefulHunger")
					.define("peacefulHunger", true);
			
			builder.pop();
		}
	}


	public static void sendBoldChat(ServerPlayer p, String chatMessage, TextColor textColor) {
		TextComponent component = new TextComponent (chatMessage);

		component.getStyle().withBold(true);
		component.getStyle().withColor(textColor);

		p.sendMessage(component, p.getUUID());
	}	
	
	public static void sendChat(ServerPlayer p, String chatMessage, TextColor textColor) {
		TextComponent component = new TextComponent (chatMessage);
		component.getStyle().withColor(textColor);
		p.sendMessage(component, p.getUUID());
	}

	// update config when changed by commands
	public static void pushPeacefulHunger() {
		COMMON.peacefulHunger.set(MyConfig.isPeacefulHunger());		
	}

	public static void pushHealingPerSecond() {
		COMMON.healingPerSecond.set(MyConfig.getHealingPerSecond());			
	}

	public static void pushAttackHealingDelayTicks() {
		COMMON.attackHealingDelayTicks.set(MyConfig.getAttackHealingDelayTicks());			
	}

	public static void pushMinimumFoodHealingLevel() {
		COMMON.minimumFoodHealingLevel.set(MyConfig.getMinimumFoodHealingLevel());			
	}

	public static void pushHealingExhaustionCost() {
		COMMON.healingExhaustionCost.set(MyConfig.getHealingExhaustionCost());			
	}
	
	public static void pushWakeupHealingAmount() {
		COMMON.wakeupHealingAmount.set(MyConfig.getWakeupHealingAmount());			
		
	}
	public static void pushExtraExhaustionWhenHurt() {
		COMMON.extraExhaustionWhenHurt.set(MyConfig.getExtraExhaustionWhenHurt());			
		
	}

	public static void pushMinimumStarvationHealth() {
		COMMON.minimumStarvationHealth.set(MyConfig.getMinimumStarvationHealth());			
	}


	
}
