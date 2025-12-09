package net.avin2810.cobblemonhealingbackpacks;

import net.avin2810.cobblemonhealingbackpacks.upgrades.CobblemonHealUpgradeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;

public final class HealUpgradeHelper {

    private HealUpgradeHelper() {
        // utility class – no instances
    }

    public static boolean playerHasHealUpgrade(PlayerEntity player) {
        // Check main inventory
        for (ItemStack stack : player.getInventory().main) {
            if (hasHealUpgradeInStack(stack)) {
                return true;
            }
        }

        // Check offhand too
        if (hasHealUpgradeInStack(player.getOffHandStack())) {
            return true;
        }

        return false;
    }

    private static boolean hasHealUpgradeInStack(ItemStack stack) {
        if (!(stack.getItem() instanceof BackpackItem)) {
            return false;
        }

        // This is the official way SB accesses backpack data in your version
        IBackpackWrapper wrapper = BackpackWrapper.fromStack(stack);

        // Upgrade handler is already on the wrapper – just query our type
        return !wrapper.getUpgradeHandler()
                .getTypeWrappers(CobblemonHealUpgradeItem.TYPE)
                .isEmpty();
        // or: wrapper.getUpgradeHandler().hasUpgrade(CobblemonHealUpgradeItem.TYPE);
        // if IntelliJ offers that method. Either is fine.
    }
}
