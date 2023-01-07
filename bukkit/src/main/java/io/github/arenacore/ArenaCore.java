package io.github.arenacore;

import io.github.arenacore.match.reason.MatchLeaveReason;
import io.github.arenacore.user.UserHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaCore extends JavaPlugin {

    public static ArenaCore getInstance() {
        return ArenaCore.getPlugin(ArenaCore.class);
    }

    @Override
    public void onEnable() {
        getLogger().info("arena-core initialize process started.");
        Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer("You got kicked because initialize process isn't started."));
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler(priority = EventPriority.HIGH)
            public void onConnect(PlayerJoinEvent event) {
                UserHandler.getInstance().loadUser(event.getPlayer());
            }

            @EventHandler
            public void onDisconnect(PlayerQuitEvent event) {
                UserHandler.getInstance().getMember(event.getPlayer().getUniqueId()).ifPresent(member -> member.getMatch().leave(member, MatchLeaveReason.DISCONNECT));
            }
        }, this);
    }
}
