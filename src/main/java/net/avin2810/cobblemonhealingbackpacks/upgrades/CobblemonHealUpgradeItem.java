package net.avin2810.cobblemonhealingbackpacks.upgrades;

import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeType;

import java.util.List;

public class CobblemonHealUpgradeItem extends UpgradeItemBase<CobblemonHealUpgradeWrapper> {

    // Upgrade type for our heal upgrade
    public static final UpgradeType<CobblemonHealUpgradeWrapper> TYPE =
            new UpgradeType<>(CobblemonHealUpgradeWrapper::new);

    public CobblemonHealUpgradeItem() {
        super(Config.SERVER.maxUpgradesPerStorage);
    }

    @Override
    public UpgradeType<CobblemonHealUpgradeWrapper> getType() {
        return TYPE;
    }

    @Override
    public List<UpgradeConflictDefinition> getUpgradeConflicts() {
        return List.of();
    }
}
