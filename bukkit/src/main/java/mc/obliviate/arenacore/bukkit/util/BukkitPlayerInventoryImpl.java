package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.inventory.BasePlayerInventory;
import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventoryImpl extends BukkitInventoryImpl implements BasePlayerInventory {

    private final PlayerInventory inventory;

    public BukkitPlayerInventoryImpl(PlayerInventory inventory) {
        super(inventory);
        this.inventory = inventory;
    }

    @Override
    public void helmet(BaseItem item) {
        inventory.setHelmet(BukkitItemImpl.toBukkit(item));
    }

    @Override
    public BaseItem helmet() {
        return BukkitItemImpl.toBase(inventory.getHelmet());
    }

    @Override
    public void chestplate(BaseItem item) {
        inventory.setChestplate(BukkitItemImpl.toBukkit(item));
    }

    @Override
    public BaseItem chestplate() {
        return BukkitItemImpl.toBase(inventory.getChestplate());
    }

    @Override
    public void leggings(BaseItem item) {
        inventory.setLeggings(BukkitItemImpl.toBukkit(item));
    }

    @Override
    public BaseItem leggings() {
        return BukkitItemImpl.toBase(inventory.getLeggings());
    }

    @Override
    public void boots(BaseItem item) {
        inventory.setBoots(BukkitItemImpl.toBukkit(item));
    }

    @Override
    public BaseItem boots() {
        return BukkitItemImpl.toBase(inventory.getBoots());
    }

    public PlayerInventory bukkit() {
        return inventory;
    }
}
