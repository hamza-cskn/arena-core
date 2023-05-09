package mc.obliviate.arenacore.base.position;

import mc.obliviate.arenacore.util.Preconditions;

import java.util.Objects;

public class Position extends Vector {

    protected String world;
    protected float yaw = 0f;
    protected float pitch = 0f;

    public Position(Position position) {
        super(position.x, position.y, position.z);
        this.world = position.world;
        this.yaw = position.yaw;
        this.pitch = position.pitch;
    }

    public Position(String world, double x, double y, double z) {
        super(x, y, z);
        this.world = world;
    }

    public Position(String world, double x, double y, double z, float yaw, float pitch) {
        super(x, y, z);
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Position addClone(int x, int y, int z) {
        return new Position(world, this.x + x, this.y + y, this.z + z, yaw, pitch);
    }

    public Position add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }


    public double distanceSquared(Position pos) {
        Preconditions.checkNotNull(pos, "cannot measure distance to a null location");
        Preconditions.checkNotNull(pos.getWorld(), "cannot measure distance to a null world");
        Preconditions.checkNotNull(getWorld(), "cannot measure distance to a null world");
        Preconditions.checkState(Objects.equals(pos.getWorld(), getWorld()), "cannot measure distance between " + getWorld() + " and " + pos.getWorld());
        return Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2) + Math.pow(z - pos.z, 2);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.x, this.x) == 0 &&
                Double.compare(position.y, this.y) == 0 &&
                Double.compare(position.z, this.z) == 0 &&
                Float.compare(position.yaw, yaw) == 0 &&
                Float.compare(position.pitch, pitch) == 0 &&
                Objects.equals(world, position.world);
    }
}
