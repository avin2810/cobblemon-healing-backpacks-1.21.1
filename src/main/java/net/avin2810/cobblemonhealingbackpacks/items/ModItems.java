package net.avin2810.cobblemonhealingbackpacks.items;

import net.avin2810.cobblemonhealingbackpacks.CobblemonHealingBackpacks;
import net.avin2810.cobblemonhealingbackpacks.upgrades.CobblemonHealUpgradeItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item COBBLEMON_HEAL_UPGRADE;

    private static Item register(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(CobblemonHealingBackpacks.MOD_ID, name),
                item
        );
    }

    public static void registerModItems() {
        CobblemonHealingBackpacks.LOGGER.info("Registering Mod Items for " + CobblemonHealingBackpacks.MOD_ID);

        COBBLEMON_HEAL_UPGRADE = register(
                "cobblemon_heal_upgrade",
                // 🔽 use the Sophisticated Backpacks upgrade item here
                new CobblemonHealUpgradeItem()
        );

        // So you can see it in creative
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
                entries.add(COBBLEMON_HEAL_UPGRADE)
        );
    }
}
