package mc.obliviate.arenacore.base.inventory;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import mc.obliviate.arenacore.util.Preconditions;

import java.util.Arrays;
import java.util.List;

public interface BasePlayerEquipment {

    void helmet(BaseItem item);

    BaseItem helmet();

    void chestplate(BaseItem item);

    BaseItem chestplate();

    void leggings(BaseItem item);

    BaseItem leggings();

    void boots(BaseItem item);

    BaseItem boots();

    default BaseItem[] equipments() {
        return new BaseItem[]{helmet(), chestplate(), leggings(), boots()};
    }

    default void equipments(BaseItem[] items) {
        Preconditions.checkNotNull(items, "items cannot be null");
        Preconditions.checkArgument(items.length == 4, "length of items array must be 4");
        equipments(items[0], items[1], items[2], items[3]);
    }

    default void equipments(BaseItem helmet, BaseItem chestplate, BaseItem leggings, BaseItem boots) {
        helmet(helmet);
        chestplate(chestplate);
        leggings(leggings);
        boots(boots);
    }

    default void clear() {
        equipments(BaseItem.emptyItem(), BaseItem.emptyItem(), BaseItem.emptyItem(), BaseItem.emptyItem());
    }

}
