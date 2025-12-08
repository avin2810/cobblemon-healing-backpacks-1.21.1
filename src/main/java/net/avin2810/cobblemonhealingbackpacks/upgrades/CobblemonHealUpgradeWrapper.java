package net.avin2810.cobblemonhealingbackpacks.upgrades;

import net.minecraft.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;

import java.util.function.Consumer;

public class CobblemonHealUpgradeWrapper
        extends UpgradeWrapperBase implements IUpgradeWrapper {

    public CobblemonHealUpgradeWrapper(IStorageWrapper storage,
                                       ItemStack upgrade,
                                       Consumer<ItemStack> saveHandler) {
        super(storage, upgrade, saveHandler);
    }

    @Override
    public boolean canBeDisabled() {
        return false;
    }
}
