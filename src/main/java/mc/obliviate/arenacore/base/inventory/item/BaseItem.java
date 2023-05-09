package mc.obliviate.arenacore.base.inventory.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseItem {
    
    private static BaseItem emptyItem = null;

    public static BaseItem getEmptyItem() {
        return emptyItem;
    }

    public static void setEmptyItem(BaseItem emptyItem) {
        BaseItem.emptyItem = emptyItem;
    }

    public abstract BaseItem amount(int amount);

    public abstract int amount();

    public abstract BaseItem displayName(String displayName);

    public abstract String displayName();

    public BaseItem appendLore(String... lore) {
        return appendLore(Arrays.asList(lore));
    }

    public BaseItem appendLore(Iterable<String> lore) {
        if (lore == null) return this;
        List<String> copy = new ArrayList<>(this.lore());
        lore.forEach(copy::add);
        return lore(copy);
    }

    public BaseItem insertLore(int index, String... lore) {
        return insertLore(index, Arrays.asList(lore));
    }

    public BaseItem insertLore(int index, Iterable<String> lore) {
        if (lore == null) return this;
        List<String> copy = new ArrayList<>(this.lore());
        for (String line : lore) {
            copy.add(index++, line);
        }
        return lore(copy);
    }

    public BaseItem removeLore(int index) {
        List<String> copy = new ArrayList<>(this.lore());
        copy.remove(index);
        return lore(copy);
    }

    public BaseItem lore(String... lore) {
        if (lore == null) return this;
        return lore(Arrays.asList(lore));
    }

    public abstract BaseItem lore(Iterable<String> lore);

    public abstract List<String> lore();
    
}
