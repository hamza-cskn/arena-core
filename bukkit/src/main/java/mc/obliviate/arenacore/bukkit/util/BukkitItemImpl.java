package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import mc.obliviate.arenacore.util.Preconditions;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BukkitItemImpl extends BaseItem {

    private final ItemStack item;

    private BukkitItemImpl(ItemStack item) {
        this.item = item;
    }

    public static BukkitItemImpl toBase(ItemStack item) {
        Preconditions.checkNotNull(item, "item stack cannot be null.");
        return new BukkitItemImpl(item);
    }

    public static BukkitItemImpl[] toBase(ItemStack[] items) {
        Preconditions.checkNotNull(items, "item stacks cannot be null.");
        final BukkitItemImpl[] result = new BukkitItemImpl[items.length];
        int i = 0;
        for (ItemStack item : items) {
            result[i] = BukkitItemImpl.toBase(item);
        }
        return result;
    }

    public static ItemStack toBukkit(BaseItem item) {
        Preconditions.checkNotNull(item, "base item cannot be null.");
        Preconditions.checkArgument(item instanceof BukkitItemImpl, "item is not able to convertable to bukkit item stack.");
        return ((BukkitItemImpl) item).bukkit();
    }

    public static ItemStack[] toBukkit(BaseItem[] items) {
        Preconditions.checkNotNull(items, "base items cannot be null.");
        Preconditions.checkArgument(items instanceof BukkitItemImpl[], "items is not able to convertable to bukkit item stack.");
        final ItemStack[] result = new ItemStack[items.length];
        int i = 0;
        for (BaseItem item : items) {
            result[i] = BukkitItemImpl.toBukkit(item);
        }
        return result;
    }
    public ItemStack bukkit() {
        return item;
    }

    @Override
    public BaseItem amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    @Override
    public int amount() {
        return item.getAmount();
    }

    @Override
    public BaseItem displayName(String displayName) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public String displayName() {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        return item.getItemMeta().getDisplayName();
    }

    @Override
    public BaseItem lore(Iterable<String> lore) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;
        if (lore instanceof List) {
            meta.setLore((List<String>) lore);
        } else {
            List<String> copy = new ArrayList<>();
            lore.forEach(copy::add);
            meta.setLore(copy);
        }
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public List<String> lore() {
        return item.getItemMeta() == null ? null : item.getItemMeta().getLore();
    }

}
