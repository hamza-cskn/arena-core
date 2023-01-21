package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.inventory.BasePlayerInventory;
import mc.obliviate.arenacore.base.player.BasePlayer;
import mc.obliviate.arenacore.base.position.Position;
import mc.obliviate.arenacore.util.Preconditions;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlayerImpl implements BasePlayer {

    private final Player player;

    private BukkitPlayerImpl(Player player) {
        this.player = player;
    }

    public static BukkitPlayerImpl toBase(Player player) {
        Preconditions.checkNotNull(player, "player cannot be null.");
        return new BukkitPlayerImpl(player);
    }

    public static Player toBukkit(BasePlayer player) {
        Preconditions.checkNotNull(player, "player cannot be null.");
        Preconditions.checkArgument(player instanceof BukkitPlayerImpl, "player is not able to convertable to bukkit player.");
        return ((BukkitPlayerImpl) player).bukkit();
    }


    private Player bukkit() {
        return player;
    }

    @Override
    public UUID uuid() {
        return player.getUniqueId();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadein, int stay, int fadeout) {

    }

    @Override
    public void sendAction(String message) {

    }

    @Override
    public boolean teleport(Position position) {
        return player.teleport(BukkitPositionImpl.toLocation(position));
    }

    @Override
    public Position position() {
        return BukkitPositionImpl.toPosition(player.getLocation());
    }

    @Override
    public BasePlayerInventory inventory() {
        return null;
    }

    @Override
    public float experience() {
        return player.getExp();
    }

    @Override
    public void experience(float newExperience) {
        player.setExp(newExperience);
    }

    @Override
    public double health() {
        return player.getHealth();
    }

    @Override
    public void health(double newHealth) {
        player.setHealth(newHealth);
    }

    @Override
    public void flight(boolean mode) {
        if (!player.getAllowFlight()) player.setAllowFlight(true);
        player.setFlying(mode);
    }

    @Override
    public boolean flight() {
        return player.isFlying();
    }

    @Override
    public boolean equals(Object obj) {
        return player.equals(obj);
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

}
