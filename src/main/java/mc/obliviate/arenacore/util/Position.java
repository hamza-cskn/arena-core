package mc.obliviate.arenacore.util;

public class Position {

    private String world;
    private int x;
    private int y;
    private int z;
    private float yaw = 0f;
    private float pitch = 0f;

    public Position(Position position) {
        this.world = position.world;
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
        this.yaw = position.yaw;
        this.pitch = position.pitch;
    }

    public Position(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(String world, int x, int y, int z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
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
}
