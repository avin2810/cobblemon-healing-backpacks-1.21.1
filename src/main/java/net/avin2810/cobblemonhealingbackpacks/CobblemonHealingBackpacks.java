package net.avin2810.cobblemonhealingbackpacks;

import net.avin2810.cobblemonhealingbackpacks.items.ModItems;
import net.avin2810.cobblemonhealingbackpacks.network.HealPartyPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CobblemonHealingBackpacks implements ModInitializer {
	public static final String MOD_ID = "cobblemon-healing-backpacks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();

		// Register our C2S payload type
		PayloadTypeRegistry.playC2S().register(HealPartyPayload.ID, HealPartyPayload.CODEC);

		// Handle incoming heal requests from client
		ServerPlayNetworking.registerGlobalReceiver(HealPartyPayload.ID, (payload, context) -> {
			ServerPlayerEntity player = context.player();
			context.server().execute(() -> healParty(player));
		});
	}

	public static void healParty(ServerPlayerEntity player) {
		if (player == null || player.getServer() == null) {
			return;
		}

		if (!HealUpgradeHelper.playerHasHealUpgrade(player)) {
			LOGGER.info("Player {} tried to heal without Cobblemon heal upgrade.",
					player.getGameProfile().getName());
			return;
		}
		String playerName = player.getName().getString();
		if (HealUpgradeHelper.playerHasHealUpgrade(player)) {
			healParty(player);
		}

		LOGGER.info("CobblemonHealingBackpacks: healed party for {}", playerName);
	}
}
