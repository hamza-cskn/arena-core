package mc.obliviate.arenacore.bukkit.task;

import mc.obliviate.arenacore.bukkit.BukkitArenaCore;
import mc.obliviate.arenacore.match.task.ITaskScheduler;
import org.bukkit.Bukkit;

public class BukkitTaskSchedulerImpl implements ITaskScheduler {

    @Override
    public void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(BukkitArenaCore.getInstance(), runnable);
    }

    @Override
    public void async(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(BukkitArenaCore.getInstance(), runnable);
    }

    @Override
    public void delaySync(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLater(BukkitArenaCore.getInstance(), runnable, delay);
    }

    @Override
    public void delayAsync(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(BukkitArenaCore.getInstance(), runnable, delay);
    }

    @Override
    public void periodicSync(Runnable runnable, long delay, long period) {
        Bukkit.getScheduler().runTaskTimer(BukkitArenaCore.getInstance(), runnable, delay, period);
    }

    @Override
    public void periodicAsync(Runnable runnable, long delay, long period) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(BukkitArenaCore.getInstance(), runnable, delay, period);
    }
}
