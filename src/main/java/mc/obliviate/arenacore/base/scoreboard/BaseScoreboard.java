package mc.obliviate.arenacore.base.scoreboard;

import mc.obliviate.arenacore.base.player.BasePlayer;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public interface BaseScoreboard {

    boolean isExist();

    BasePlayer player();

    String title();

    BaseScoreboard title(String title);

    int getUpdateInterval();

    BaseScoreboard setUpdateInterval(int updateInterval);

    String line(int line);

    BaseScoreboard line(int line, String text);

    BaseScoreboard line(List<String> lines);

    BaseScoreboard line(String... lines);

    BaseScoreboard removeLine(int index);

    BaseScoreboard expire(int time, TimeUnit timeUnit);

    BaseScoreboard expire(Duration duration);

    BaseScoreboard expire(int ticks);

    BaseScoreboard show();

    BaseScoreboard update(Consumer<BaseScoreboard> consumer);

    BaseScoreboard delete();
}
