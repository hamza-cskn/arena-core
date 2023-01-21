package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.position.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BukkitPositionImpl extends Position {

    public BukkitPositionImpl(Position position) {
        super(position);
    }

    public BukkitPositionImpl(Location location) {
        super(BukkitPositionImpl.toPosition(location));
    }

    public BukkitPositionImpl(String world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public BukkitPositionImpl(String world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    public static Position toPosition(Location loc) {
        return new Position(loc.getWorld().getName(),
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getYaw(),
                loc.getPitch());
    }

    public static Location toLocation(Position pos) {
        return new Location(Bukkit.getWorld(pos.getWorld()),
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                pos.getYaw(),
                pos.getPitch());
    }

}
