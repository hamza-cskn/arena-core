package mc.obliviate.arenacore.base.inventory.backup;

import mc.obliviate.arenacore.base.player.BasePlayer;
import mc.obliviate.arenacore.base.inventory.item.BaseItem;

public class PlayerInventoryContainer {

    private final BaseItem[] inventoryContents;
    private final BaseItem[] armorContents;

    public PlayerInventoryContainer(BaseItem[] inventoryContents, BaseItem[] armorContents) {
        this.inventoryContents = inventoryContents;
        this.armorContents = armorContents;
    }

    public PlayerInventoryContainer(BasePlayer player) {
        this(player.inventory().items(), player.inventory().equipments());
    }

    public void load(BasePlayer player) {
        player.inventory().items(inventoryContents);
        player.inventory().equipments(armorContents);
    }
}
