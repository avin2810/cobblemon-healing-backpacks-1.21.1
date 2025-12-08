package net.avin2810.cobblemonhealingbackpacks.items;

import net.avin2810.cobblemonhealingbackpacks.CobblemonHealingBackpacks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CobblemonHealingBackpacks.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CobblemonHealingBackpacks.LOGGER.info("Registering Mod Items for " + CobblemonHealingBackpacks.MOD_ID);
    }
}
