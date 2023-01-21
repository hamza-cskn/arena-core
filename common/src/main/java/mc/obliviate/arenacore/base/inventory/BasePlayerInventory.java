package mc.obliviate.arenacore.base.inventory;

public interface BasePlayerInventory extends BaseInventory, BasePlayerEquipment {

    @Override
    default void clear() {
        BaseInventory.super.clear();
        BasePlayerEquipment.super.clear();
    }

}
