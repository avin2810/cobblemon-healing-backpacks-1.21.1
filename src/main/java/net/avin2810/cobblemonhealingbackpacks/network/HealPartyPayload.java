package net.avin2810.cobblemonhealingbackpacks.network;

import net.avin2810.cobblemonhealingbackpacks.CobblemonHealingBackpacks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Empty C2S payload telling the server to heal the player's Cobblemon party.
 */
public record HealPartyPayload() implements CustomPayload {

    // ID used to register this payload type
    public static final CustomPayload.Id<HealPartyPayload> ID =
            new CustomPayload.Id<>(
                    Identifier.of(CobblemonHealingBackpacks.MOD_ID, "heal_party")
            );

    // No data, so just use unit()
    public static final PacketCodec<RegistryByteBuf, HealPartyPayload> CODEC =
            PacketCodec.unit(new HealPartyPayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
