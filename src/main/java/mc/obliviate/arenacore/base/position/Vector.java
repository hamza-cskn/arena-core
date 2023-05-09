package mc.obliviate.arenacore.base.position;

import mc.obliviate.arenacore.util.Preconditions;

public class Vector {

    protected double x;
    protected double y;
    protected double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getBlockX() {
        return (int) x;
    }

    public int getBlockY() {
        return (int) y;
    }

    public int getBlockZ() {
        return (int) z;
    }


    public double distance(Vector o) {
        return Math.sqrt(distanceSquared(o));
    }

    public double distanceSquared(Vector pos) {
        Preconditions.checkNotNull(pos, "cannot measure distance to a null location");
        return Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2) + Math.pow(z - pos.z, 2);
    }

}
