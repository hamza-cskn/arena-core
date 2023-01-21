package mc.obliviate.arenacore.bukkit.util;

import mc.obliviate.arenacore.base.scoreboard.BaseScoreboard;
import mc.obliviate.arenacore.bukkit.task.BukkitTaskSchedulerImpl;
import mc.obliviate.arenacore.util.ColorUtil;
import mc.obliviate.arenacore.util.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Thanks to  <a href="https://github.com/hakan-krgn/">Hakan Kargın</a> for the scoreboard implementation
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class BukkitScoreboardImpl implements BaseScoreboard {

    private static final Map<UUID, BukkitScoreboardImpl> SCOREBOARD_MAP = new HashMap<>();
    private final UUID uid;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private boolean terminated = false;

    private String title = "";
    private int updateInterval = 0;

    /**
     * Creates new Instance of this class.
     *
     * @param uid UID of player
     */
    BukkitScoreboardImpl(UUID uid) {
        this.uid = Preconditions.checkNotNull(uid, "uuid cannot be null");
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager(), "scoreboard manager cannot be null.").getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("board", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(this.title);
        BukkitScoreboardImpl.deleteIfPresent(uid);
        SCOREBOARD_MAP.put(uid, this);
    }

    public static void deleteIfPresent(UUID uuid) {
        BukkitScoreboardImpl.getScoreboard(uuid).ifPresent(BukkitScoreboardImpl::delete);
    }

    public static Optional<BukkitScoreboardImpl> getScoreboard(UUID uuid) {
        return Optional.ofNullable(SCOREBOARD_MAP.get(uuid));
    }

    public static Map<UUID, BukkitScoreboardImpl> getScoreboardMap() {
        return Collections.unmodifiableMap(SCOREBOARD_MAP);
    }

    /**
     * Checks if scoreboard still exist for player
     *
     * @return if scoreboard still exist for player, return true
     */
    @Override
    public boolean isExist() {
        return this.equals(SCOREBOARD_MAP.get(uid));
    }

    /**
     * Gets UUID of player.
     *
     * @return UUID of player.
     */
    public UUID getUID() {
        return this.uid;
    }

    /**
     * Gets player.
     *
     * @return Player.
     */
    @Override
    public BukkitPlayerImpl player() {
        return BukkitPlayerImpl.toBase(Bukkit.getPlayer(this.uid));
    }

    public Optional<BukkitPlayerImpl> findPlayer() {
        return Optional.of(this.player());
    }

    /**
     * Gets title of scoreboard.
     *
     * @return Title of scoreboard.
     */
    @Override
    public String title() {
        return this.title;
    }

    /**
     * Sets title of scoreboard.
     *
     * @param title Title.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl title(String title) {
        this.title = Preconditions.checkNotNull(title, "title cannot be null");
        this.objective.setDisplayName(this.title);
        return this;
    }

    /**
     * Gets update interval of scoreboard.
     *
     * @return Update interval of scoreboard.
     */
    @Override
    public int getUpdateInterval() {
        return this.updateInterval;
    }

    /**
     * Sets update interval of scoreboard.
     *
     * @param updateInterval Update interval.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
        return this;
    }

    /**
     * Gets text of line.
     *
     * @param line Line.
     * @return Text of line.
     */
    @Override
    public String line(int line) {
        return this.getTeam(line).getPrefix();
    }

    /**
     * Sets line of scoreboard to text.
     *
     * @param line Line number.
     * @param text Text.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl line(int line, String text) {
        Preconditions.checkNotNull(text, "text cannot be null");
        String first = text.substring(0, Math.min(16, text.length()));
        String second = null;
        if (first.endsWith("&")) {
            first = first.substring(0, first.length() - 1);
            second = "&";
        }
        this.getTeam(line).setPrefix(ColorUtil.getInstance().color(first));
        if (text.length() > 16) {

            String lineText = (second == null ? ColorUtil.getInstance().lastColorChar(first) : second) + text.substring(16, Math.min(30, text.length()));
            this.getTeam(line).setSuffix(ColorUtil.getInstance().color(lineText));
        }
        return this;
    }

    /**
     * Sets lines of scoreboard to lines.
     *
     * @param lines List of lines.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl line(List<String> lines) {
        Preconditions.checkNotNull(lines, "lines cannot be null");
        for (int i = 1; i <= 16; i++)
            if (lines.size() >= i) this.line(i, lines.get(i - 1));
            else this.removeLine(i);
        return this;
    }

    /**
     * Sets lines of scoreboard to lines.
     *
     * @param lines List of lines.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl line(String... lines) {
        return this.line(Arrays.asList(lines));
    }

    /**
     * Removes line from scoreboard.
     *
     * @param line Line number.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl removeLine(int line) {
        Team currentTeam = this.scoreboard.getTeam("line_" + line);
        if (currentTeam == null) {
            return this;
        }

        this.scoreboard.resetScores(currentTeam.getEntries().iterator().next());
        currentTeam.unregister();
        return this;
    }

    /**
     * When the time is up, scoreboard will
     * remove automatically.
     *
     * @param time     Time.
     * @param timeUnit Time unit (TimeUnit.SECONDS, TimeUnit.HOURS, etc.)
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl expire(int time, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit, "time unit cannot be null");
        BukkitTaskSchedulerImpl.scheduler().delaySync(this::delete, timeUnit.toMillis(time) / 50);
        return this;
    }

    /**
     * When the time is up, scoreboard will
     * remove automatically.
     *
     * @param duration Duration.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl expire(Duration duration) {
        Preconditions.checkNotNull(duration, "duration cannot be null!");
        BukkitTaskSchedulerImpl.scheduler().delaySync(this::delete, duration.toMillis() / 50);
        return this;
    }

    /**
     * When the time is up, scoreboard will
     * remove automatically.
     *
     * @param ticks Ticks.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl expire(int ticks) {
        BukkitTaskSchedulerImpl.scheduler().delaySync(this::delete, ticks);
        return this;
    }

    /**
     * Shows the scoreboard to player.
     */
    @Override
    public BukkitScoreboardImpl show() {
        this.findPlayer().ifPresent(player -> {
            if (BukkitPlayerImpl.toBukkit(player).getScoreboard().equals(this.scoreboard))
                return;

            BukkitPlayerImpl.toBukkit(player).setScoreboard(this.scoreboard);
        });
        return this;
    }


    /**
     * Once every updateInterval ticks,
     * it will trigger.
     *
     * @param consumer Callback.
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl update(Consumer<BaseScoreboard> consumer) {
        Preconditions.checkNotNull(consumer, "consumer cannot be null");

        if (this.updateInterval > 0 && !this.terminated) {
            consumer.accept(this);
            BukkitTaskSchedulerImpl.scheduler().delaySync(() -> this.update(consumer), this.updateInterval);
        }
        return this;
    }

    /**
     * Deletes scoreboard.
     *
     * @return Instance of this class.
     */
    @Override
    public BukkitScoreboardImpl delete() {
        this.findPlayer().ifPresent(player -> {
            BukkitPlayerImpl.toBukkit(player).setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            this.terminated = true;
            BukkitScoreboardImpl.SCOREBOARD_MAP.remove(this.uid);
            //HScoreboardHandler.getContent().remove(this.uid);
        });
        return this;
    }


    /**
     * Gets or creates team object.
     *
     * @param line Line.
     * @return Team.
     */
    private Team getTeam(int line) {
        Team currentTeam = this.scoreboard.getTeam("line_" + line);
        if (currentTeam != null) {
            return currentTeam;
        }

        Team newTeam = this.scoreboard.registerNewTeam("line_" + line);
        newTeam.setAllowFriendlyFire(true);
        newTeam.setCanSeeFriendlyInvisibles(false);
        if (newTeam.getEntries().size() > 0) {
            newTeam.removeEntry(newTeam.getEntries().iterator().next());
        }

        String teamEntry = line >= 10 ? "§" + new String[]{"a", "b", "c", "d", "e", "f"}[line - 10] : "§" + line;
        newTeam.addEntry(teamEntry);

        this.objective.getScore(teamEntry).setScore(16 - line);
        return newTeam;
    }
}