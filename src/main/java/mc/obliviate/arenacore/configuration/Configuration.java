package mc.obliviate.arenacore.configuration;

import mc.obliviate.arenacore.base.position.Cuboid;
import mc.obliviate.arenacore.base.position.Position;
import mc.obliviate.arenacore.base.position.Vector;
import mc.obliviate.arenacore.base.inventory.item.BaseItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Configuration {


	Set<String> getKeys(boolean var1);


	String getName();


	Configuration getRoot();

	boolean isSet(String key);


	Configuration getParent();


	Object get(String var1);


	Object get(String var1, Object var2);

	void set(String var1, Object var2);


	Configuration createSection(String var1);


	Configuration createSection(String var1, Map<?, ?> var2);


	String getString(String var1);


	String getString(String var1, String var2);

	int getInt(String var1);

	int getInt(String var1, int var2);

	boolean getBoolean(String var1);

	boolean getBoolean(String var1, boolean var2);

	double getDouble(String var1);

	double getDouble(String var1, double var2);

	long getLong(String var1);

	long getLong(String var1, long var2);


	List<String> getStringList(String var1);


	BaseItem getItem(String var1);


	BaseItem getItem(String var1, BaseItem var2);

	boolean isItem(String var1);


	default Position getPosition(String key) {
		Configuration section = getConfigurationSection(key);
		if (section == null) return null;
		if (!(section.isSet("world") && section.isSet("x") && section.isSet("y") && section.isSet("x"))) return null;

		final Vector vector = getVector(key);
		final double yaw = section.getDouble("yaw", 0);
		final double pitch = section.getDouble("pitch", 0);
		final String world = section.getString("world");

		return new Position(world, vector.getX(), vector.getY(), vector.getZ(), (float) yaw, (float) pitch);
	}

	default void setPosition(String key, Position position) {
		Configuration section = getConfigurationSection(key);
		section.set("world", position.getWorld());
		section.set("yaw", (double) position.getYaw());
		section.set("pitch", (double) position.getPitch());
		setVector(key, position);
	}


	default Vector getVector(String key) {
		Configuration section = getConfigurationSection(key);
		if (section == null) return null;
		if (!(section.isSet("x") && section.isSet("y") && section.isSet("x"))) return null;

		final double x = section.getDouble("x");
		final double y = section.getDouble("y");
		final double z = section.getDouble("z");

		return new Vector(x, y, z);
	}

	default void setVector(String key, Vector vector) {
		Configuration section = getConfigurationSection(key);
		section.set("x", vector.getX());
		section.set("y", vector.getY());
		section.set("z", vector.getZ());
	}


	default Cuboid getCuboid(String key) {
		Configuration section = getConfigurationSection(key);
		Vector point1 = section.getVector("point1");
		Vector point2 = section.getVector("point2");
		String world = section.getString("world");
		return new Cuboid(world, point1, point2);
	}

	default void setCuboid(String key, Cuboid cuboid) {
		Configuration section = getConfigurationSection(key);
		section.set("world", cuboid.world());
		section.createSection("point1").setVector(key, cuboid.point1());
		section.createSection("point2").setVector(key, cuboid.point2());
	}


	Position getPosition(String var1, Position var2);


	Configuration getConfigurationSection(String var1);

	boolean isConfigurationSection(String var1);


	Configuration getDefaultSection();
}
