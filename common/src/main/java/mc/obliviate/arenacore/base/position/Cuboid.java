package mc.obliviate.arenacore.base.position;


import mc.obliviate.arenacore.util.Preconditions;

import java.util.*;

public class Cuboid {

    /**
     * Purpose of class,
     * storing blocks as a cuboid
     * <p>
     * Thanks to Tristiisch74 for the <a href="https://www.spigotmc.org/threads/region-cuboid.329859/">code</a>.
     */

    private final Vector point1;
    private final Vector point2;
    private final String world;

    public Cuboid(final Position point1, final Position point2) {
        this(point1.getWorld(), point1, point2);
    }

    public Cuboid(final String world, final Vector point1, final Vector point2) {
        Preconditions.checkNotNull(point1, "point1 cannot be null");
        Preconditions.checkNotNull(point2, "point2 cannot be null");
        Preconditions.checkNotNull(world, "world cannot be null");
        if (point1 instanceof Position && point2 instanceof Position) {
            Position pos1 = (Position) point1;
            Position pos2 = (Position) point2;
            Preconditions.checkState(world != null && Objects.equals(pos1.getWorld(), pos2.getWorld()), "worlds of points must be in same world.");
        }
        this.point1 = point1;
        this.point2 = point2;
        this.world = world;
    }

    public List<Position> blockList() {
        final ArrayList<Position> bL = new ArrayList<>(this.totalBlockCount());
        for (int x = this.xMin(); x <= this.xMax(); ++x) {
            for (int y = this.yMin(); y <= this.yMax(); ++y) {
                for (int z = this.zMin(); z <= this.zMax(); ++z) {
                    bL.add(new Position(world, x, y, z));
                }
            }
        }
        return bL;
    }

    public Position center() {
        return new Position(this.world,
                (this.xMax() - this.xMin()) / 2f + this.xMin(),
                (this.yMax() - this.yMin()) / 2f + this.yMin(), (this.zMax() - this.zMin()) / 2f + this.zMin());
    }

    public double distance() {
        return this.point1().distance(this.point2());
    }

    public double distanceSquared() {
        return this.point1().distanceSquared(this.point2());
    }

    public Vector point1() {
        return point1;
    }

    public Vector point2() {
        return point2;
    }

    public int totalBlockCount() {
        return this.yWidth() * this.xWidth() * this.zWidth();
    }

    public int xWidth() {
        return this.xMax() - this.xMin() + 1;
    }

    public int yWidth() {
        return this.yMax() - this.yMin() + 1;
    }

    public int zWidth() {
        return this.zMax() - this.zMin() + 1;
    }

    public boolean isIn(final Position loc) {
        if (loc == null) return false;
        return Objects.equals(loc.getWorld(), this.world) &&
                loc.getBlockX() >= this.xMin() &&
                loc.getBlockX() <= this.xMax() &&
                loc.getBlockY() >= this.yMin() &&
                loc.getBlockY() <= this.yMax() &&
                loc.getBlockZ() >= this.zMin() &&
                loc.getBlockZ() <= this.zMax();
    }

    public int xMin() {
        return Math.min(point1.getBlockX(), point2.getBlockX());
    }

    public int xMax() {
        return Math.max(point1.getBlockX(), point2.getBlockX());
    }

    public int yMin() {
        return Math.min(point1.getBlockY(), point2.getBlockY());
    }

    public int yMax() {
        return Math.max(point1.getBlockY(), point2.getBlockY());
    }

    public int zMin() {
        return Math.min(point1.getBlockZ(), point2.getBlockZ());
    }

    public int zMax() {
        return Math.max(point1.getBlockZ(), point2.getBlockZ());
    }

    public String world() {
        return world;
    }

}
