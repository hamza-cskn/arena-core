package mc.obliviate.arenacore.configuration;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import mc.obliviate.arenacore.base.position.Cuboid;
import mc.obliviate.arenacore.base.position.Position;
import mc.obliviate.arenacore.base.position.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Configuration {

    @NotNull
    Set<String> getKeys(boolean var1);

    @NotNull
    String getName();

    @Nullable
    Configuration getRoot();

    boolean isSet(String key);

    @Nullable
    Configuration getParent();

    @Nullable
    Object get(@NotNull String var1);

    @Nullable
    Object get(@NotNull String var1, @Nullable Object var2);

    void set(@NotNull String var1, @Nullable Object var2);

    @NotNull
    Configuration createSection(@NotNull String var1);

    @NotNull
    Configuration createSection(@NotNull String var1, @NotNull Map<?, ?> var2);

    @Nullable
    String getString(@NotNull String var1);

    @Nullable
    String getString(@NotNull String var1, @Nullable String var2);

    int getInt(@NotNull String var1);

    int getInt(@NotNull String var1, int var2);

    boolean getBoolean(@NotNull String var1);

    boolean getBoolean(@NotNull String var1, boolean var2);

    double getDouble(@NotNull String var1);

    double getDouble(@NotNull String var1, double var2);

    long getLong(@NotNull String var1);

    long getLong(@NotNull String var1, long var2);

    @NotNull
    List<String> getStringList(@NotNull String var1);

    @Nullable
    BaseItem getItem(@NotNull String var1);

    @Nullable
    BaseItem getItem(@NotNull String var1, @Nullable BaseItem var2);

    boolean isItem(@NotNull String var1);

    @Nullable
    default Position getPosition(@NotNull String key) {
        Configuration section = getConfigurationSection(key);
        if (section == null) return null;
        if (!(section.isSet("world") && section.isSet("x") && section.isSet("y") && section.isSet("x"))) return null;

        final Vector vector = getVector(key);
        final double yaw = section.getDouble("yaw", 0);
        final double pitch = section.getDouble("pitch", 0);
        final String world = section.getString("world");

        return new Position(world, vector.getX(), vector.getY(), vector.getZ(), (float) yaw, (float) pitch);
    }

    default void setPosition(@NotNull String key, @NotNull Position position) {
        Configuration section = getConfigurationSection(key);
        section.set("world", position.getWorld());
        section.set("yaw", (double) position.getYaw());
        section.set("pitch", (double) position.getPitch());
        setVector(key, position);
    }

    @Nullable
    default Vector getVector(@NotNull String key) {
        Configuration section = getConfigurationSection(key);
        if (section == null) return null;
        if (!(section.isSet("x") && section.isSet("y") && section.isSet("x"))) return null;

        final double x = section.getDouble("x");
        final double y = section.getDouble("y");
        final double z = section.getDouble("z");

        return new Vector(x, y, z);
    }

    default void setVector(@NotNull String key, @NotNull Vector vector) {
        Configuration section = getConfigurationSection(key);
        section.set("x", vector.getX());
        section.set("y", vector.getY());
        section.set("z", vector.getZ());
    }

    @Nullable
    default Cuboid getCuboid(@NotNull String key) {
        Configuration section = getConfigurationSection(key);
        Vector point1 = section.getVector("point1");
        Vector point2 = section.getVector("point2");
        String world = section.getString("world");
        return new Cuboid(world, point1, point2);
    }

    default void setCuboid(@NotNull String key, @NotNull Cuboid cuboid) {
        Configuration section = getConfigurationSection(key);
        section.set("world", cuboid.world());
        section.createSection("point1").setVector(key, cuboid.point1());
        section.createSection("point2").setVector(key, cuboid.point2());
    }

    @Nullable
    Position getPosition(@NotNull String var1, @Nullable Position var2);

    @Nullable
    Configuration getConfigurationSection(@NotNull String var1);

    boolean isConfigurationSection(@NotNull String var1);

    @Nullable
    Configuration getDefaultSection();
}
