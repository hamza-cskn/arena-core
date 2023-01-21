package mc.obliviate.arenacore.bukkit;

import mc.obliviate.arenacore.bukkit.task.BukkitTaskSchedulerImpl;
import mc.obliviate.arenacore.match.AbstractMatch;
import mc.obliviate.arenacore.match.reason.MatchLeaveReason;
import mc.obliviate.arenacore.match.task.TaskScheduler;
import mc.obliviate.arenacore.user.IMember;
import mc.obliviate.arenacore.user.UserHandler;
import mc.obliviate.arenacore.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class BukkitArenaCore extends JavaPlugin {

    public static BukkitArenaCore getInstance() {
        return getPlugin(BukkitArenaCore.class);
    }

    @Override
    public void onEnable() {
        getLogger().info("arena-core initialize process started.");
        Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer("You got kicked because initialize process isn't started."));
        TaskScheduler.setInstance(new BukkitTaskSchedulerImpl());

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler(priority = EventPriority.HIGH)
            public void onConnect(PlayerJoinEvent event) {
                UserHandler.getInstance().loadUser(event.getPlayer());
            }

            @EventHandler
            public void onDisconnect(PlayerQuitEvent event) {
                //I can't use it without variable. lol.
                Optional<IMember> var = UserHandler.getInstance().findMember(event.getPlayer().getUniqueId());
                var.ifPresent(member -> member.getMatch().leave(member, MatchLeaveReason.DISCONNECT));
            }
        }, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("arena-core unload process started.");
        AbstractMatch.getMatches().forEach(AbstractMatch::uninstall);
    }
}
