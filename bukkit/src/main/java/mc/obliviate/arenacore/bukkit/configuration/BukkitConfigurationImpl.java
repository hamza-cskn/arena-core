package mc.obliviate.arenacore.bukkit.configuration;

import mc.obliviate.arenacore.base.inventory.item.BaseItem;
import mc.obliviate.arenacore.base.position.Position;
import mc.obliviate.arenacore.configuration.Configuration;
import mc.obliviate.arenacore.util.Preconditions;
import org.bukkit.configuration.ConfigurationSection;

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

    
    @Override
    public Set<String> getKeys(boolean depth) {
        return section.getKeys(depth);
    }

    
    @Override
    public String getName() {
        return section.getName();
    }

    
    @Override
    public Configuration getRoot() {
        return BukkitConfigurationImpl.toBase(section.getRoot());
    }

    @Override
    public boolean isSet(String key) {
        return section.isSet(key);
    }

    
    @Override
    public Configuration getParent() {
        return null;
    }

    
    @Override
    public Object get( String var1) {
        return null;
    }

    
    @Override
    public Object get( String var1,  Object var2) {
        return null;
    }

    @Override
    public void set( String var1,  Object var2) {

    }

    
    @Override
    public Configuration createSection( String var1) {
        return null;
    }

    
    @Override
    public Configuration createSection( String var1,  Map<?, ?> var2) {
        return null;
    }

    
    @Override
    public String getString( String var1) {
        return null;
    }

    
    @Override
    public String getString( String var1,  String var2) {
        return null;
    }

    @Override
    public int getInt( String var1) {
        return 0;
    }

    @Override
    public int getInt( String var1, int var2) {
        return 0;
    }

    @Override
    public boolean getBoolean( String var1) {
        return false;
    }

    @Override
    public boolean getBoolean( String var1, boolean var2) {
        return false;
    }

    @Override
    public double getDouble( String var1) {
        return 0;
    }

    @Override
    public double getDouble( String var1, double var2) {
        return 0;
    }

    @Override
    public long getLong( String var1) {
        return 0;
    }

    @Override
    public long getLong( String var1, long var2) {
        return 0;
    }

    
    @Override
    public List<String> getStringList( String var1) {
        return null;
    }

    
    @Override
    public BaseItem getItem( String var1) {
        return null;
    }

    
    @Override
    public BaseItem getItem( String var1,  BaseItem var2) {
        return null;
    }

    @Override
    public boolean isItem( String var1) {
        return false;
    }

    
    @Override
    public Position getPosition( String var1) {
        return null;
    }

    
    @Override
    public Position getPosition( String var1,  Position var2) {
        return null;
    }

    
    @Override
    public Configuration getConfigurationSection( String var1) {
        return null;
    }

    @Override
    public boolean isConfigurationSection( String var1) {
        return false;
    }

    
    @Override
    public Configuration getDefaultSection() {
        return null;
    }
}
