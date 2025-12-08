package net.avin2810.cobblemonhealingbackpacks.network;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.avin2810.cobblemonhealingbackpacks.CobblemonHealingBackpacks;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class HealPacket {

    /**
     * The actual payload type we send C2S.
     * It has no data, it just means "please heal my party".
     */
    public record HealC2SPayload() implements CustomPayload {
        // Identifier for the payload, like exploration:say_potato in the docs :contentReference[oaicite:0]{index=0}
        public static final Identifier HEAL_ID =
                Identifier.of(CobblemonHealingBackpacks.MOD_ID, "heal_party");

        // CustomPayload Id
        public static final CustomPayload.Id<HealC2SPayload> ID =
                new CustomPayload.Id<>(HEAL_ID);

        // Codec – we send no data, so use unit()
        public static final PacketCodec<RegistryByteBuf, HealC2SPayload> CODEC =
                PacketCodec.unit(new HealC2SPayload());

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    private HealPacket() {}

    /**
     * Called from your main mod initializer on BOTH SIDES.
     *
     * CobblemonHealingBackpacks.onInitialize():
     *   HealPacket.register();
     */
    public static void register() {
        // 1) Register payload type for C2S channel (both client & server) :contentReference[oaicite:1]{index=1}
        PayloadTypeRegistry.playC2S().register(HealC2SPayload.ID, HealC2SPayload.CODEC);

        // 2) Register server-side handler
        ServerPlayNetworking.registerGlobalReceiver(HealC2SPayload.ID, (payload, context) -> {
            MinecraftServer server = context.server();
            ServerPlayerEntity player = context.player();

            server.execute(() -> healPlayerParty(server, player));
        });
    }

    /**
     * Client-side helper: send the "heal" payload to the server.
     * Call this from your HealUpgradeScreen button.
     */
    public static void send() {
        ClientPlayNetworking.send(new HealC2SPayload());
    }

    /**
     * Run the Cobblemon heal command on the server.
     */
    private static void healPlayerParty(MinecraftServer server, ServerPlayerEntity player) {
        ServerCommandSource src = player.getCommandSource()
                .withLevel(4)     // op-level so it always succeeds
                .withSilent();

        String cmd = "healpokemon " + player.getGameProfile().getName();

        CommandDispatcher<ServerCommandSource> dispatcher =
                server.getCommandManager().getDispatcher();

        try {
            dispatcher.execute(cmd, src);
        } catch (CommandSyntaxException e) {
            CobblemonHealingBackpacks.LOGGER.error(
                    "Failed to execute healpokemon for {}",
                    player.getName().getString(),
                    e
            );
            player.sendMessage(Text.literal(
                    "[Cobblemon Healing Backpacks] Failed to heal your party – see server log."
            ), false);
        }
    }
}
