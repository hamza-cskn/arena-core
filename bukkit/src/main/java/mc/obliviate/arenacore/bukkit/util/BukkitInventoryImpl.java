package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.inventory.BaseInventory;
import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BukkitInventoryImpl implements BaseInventory {

    private final Inventory inventory;

    public BukkitInventoryImpl(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void set(int index, BaseItem item) {
        inventory.setItem(index, BukkitItemImpl.toBukkit(item));
    }

    @Override
    public BaseItem get(int index) {
        return BukkitItemImpl.toBase(inventory.getItem(index));
    }

    @Override
    public BaseItem[] items() {
        return BukkitItemImpl.toBase(inventory.getContents());
    }

    @Override
    public Map<Integer, BaseItem> itemMap() {
        Map<Integer, BaseItem> result = new HashMap<>();
        Arrays.stream(inventory.getContents()).forEach(item -> result.put(result.size(), BukkitItemImpl.toBase(item)));
        return result;
    }

    @Override
    public void items(BaseItem... items) {
        inventory.setContents(BukkitItemImpl.toBukkit(items));
    }

    private Inventory bukkitInventory() {
        return inventory;
    }
}
