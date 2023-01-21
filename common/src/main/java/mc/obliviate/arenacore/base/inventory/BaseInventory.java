package mc.obliviate.arenacore.base.inventory;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;

import java.util.Map;

public interface BaseInventory {

    void set(int index, BaseItem item);

    BaseItem get(int index);

    BaseItem[] items();

    Map<Integer, BaseItem> itemMap();

    void items(BaseItem... items);

    default void clear() {
        itemMap().forEach((slot, item) -> set(slot, BaseItem.emptyItem()));
    }

}
