package mc.obliviate.arenacore.bukkit.configuration;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import mc.obliviate.arenacore.base.position.Position;
import mc.obliviate.arenacore.configuration.Configuration;
import mc.obliviate.arenacore.util.Preconditions;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BukkitConfigurationImpl implements Configuration {

    private final ConfigurationSection section;

    private BukkitConfigurationImpl(ConfigurationSection section) {
        this.section = section;
    }

    public static BukkitConfigurationImpl toBase(ConfigurationSection section) {
        Preconditions.checkNotNull(section, "section cannot be null.");
        return new BukkitConfigurationImpl(section);
    }

    public static ConfigurationSection toBukkit(Configuration section) {
        Preconditions.checkNotNull(section, "section cannot be null.");
        Preconditions.checkArgument(section instanceof BukkitConfigurationImpl, "section is not convertable to bukkit section.");
        return ((BukkitConfigurationImpl) section).bukkit();
    }

    private ConfigurationSection bukkit() {
        return section;
    }

    @NotNull
    @Override
    public Set<String> getKeys(boolean depth) {
        return section.getKeys(depth);
    }

    @NotNull
    @Override
    public String getName() {
        return section.getName();
    }

    @Nullable
    @Override
    public Configuration getRoot() {
        return BukkitConfigurationImpl.toBase(section.getRoot());
    }

    @Override
    public boolean isSet(String key) {
        return section.isSet(key);
    }

    @Nullable
    @Override
    public Configuration getParent() {
        return null;
    }

    @Nullable
    @Override
    public Object get(@NotNull String var1) {
        return null;
    }

    @Nullable
    @Override
    public Object get(@NotNull String var1, @Nullable Object var2) {
        return null;
    }

    @Override
    public void set(@NotNull String var1, @Nullable Object var2) {

    }

    @NotNull
    @Override
    public Configuration createSection(@NotNull String var1) {
        return null;
    }

    @NotNull
    @Override
    public Configuration createSection(@NotNull String var1, @NotNull Map<?, ?> var2) {
        return null;
    }

    @Nullable
    @Override
    public String getString(@NotNull String var1) {
        return null;
    }

    @Nullable
    @Override
    public String getString(@NotNull String var1, @Nullable String var2) {
        return null;
    }

    @Override
    public int getInt(@NotNull String var1) {
        return 0;
    }

    @Override
    public int getInt(@NotNull String var1, int var2) {
        return 0;
    }

    @Override
    public boolean getBoolean(@NotNull String var1) {
        return false;
    }

    @Override
    public boolean getBoolean(@NotNull String var1, boolean var2) {
        return false;
    }

    @Override
    public double getDouble(@NotNull String var1) {
        return 0;
    }

    @Override
    public double getDouble(@NotNull String var1, double var2) {
        return 0;
    }

    @Override
    public long getLong(@NotNull String var1) {
        return 0;
    }

    @Override
    public long getLong(@NotNull String var1, long var2) {
        return 0;
    }

    @NotNull
    @Override
    public List<String> getStringList(@NotNull String var1) {
        return null;
    }

    @Nullable
    @Override
    public BaseItem getItem(@NotNull String var1) {
        return null;
    }

    @Nullable
    @Override
    public BaseItem getItem(@NotNull String var1, @Nullable BaseItem var2) {
        return null;
    }

    @Override
    public boolean isItem(@NotNull String var1) {
        return false;
    }

    @Nullable
    @Override
    public Position getPosition(@NotNull String var1) {
        return null;
    }

    @Nullable
    @Override
    public Position getPosition(@NotNull String var1, @Nullable Position var2) {
        return null;
    }

    @Nullable
    @Override
    public Configuration getConfigurationSection(@NotNull String var1) {
        return null;
    }

    @Override
    public boolean isConfigurationSection(@NotNull String var1) {
        return false;
    }

    @Nullable
    @Override
    public Configuration getDefaultSection() {
        return null;
    }
}
