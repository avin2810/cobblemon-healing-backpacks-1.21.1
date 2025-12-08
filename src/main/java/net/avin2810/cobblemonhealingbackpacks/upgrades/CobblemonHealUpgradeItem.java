package net.avin2810.cobblemonhealingbackpacks.upgrades;

import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeType;

import java.util.List;

public class CobblemonHealUpgradeItem extends UpgradeItemBase<IUpgradeWrapper> {

    // NOTE: type is UpgradeType<IUpgradeWrapper>, not CobblemonHealUpgradeWrapper
    private static final UpgradeType<IUpgradeWrapper> TYPE =
            new UpgradeType<>(CobblemonHealUpgradeWrapper::new);

    public CobblemonHealUpgradeItem() {
        super(Config.SERVER.maxUpgradesPerStorage);
    }

    @Override
    public UpgradeType<IUpgradeWrapper> getType() {
        return TYPE;
    }

    @Override
    public List<UpgradeConflictDefinition> getUpgradeConflicts() {
        return List.of();
    }
}
