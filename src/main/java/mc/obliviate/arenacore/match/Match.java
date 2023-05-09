package mc.obliviate.arenacore.match;

import mc.obliviate.arenacore.task.TaskGroup;
import mc.obliviate.arenacore.user.Member;
import mc.obliviate.arenacore.user.User;
import mc.obliviate.arenacore.match.reason.MatchLeaveReason;
import mc.obliviate.arenacore.match.spectator.SpectatorManager;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;

public interface Match {

    boolean joinAsMember(User user);

    boolean joinAsSpectator(User user);

    void leave(Member member, MatchLeaveReason reason);

    default void leave(Member member) {
        leave(member, MatchLeaveReason.UNKNOWN);
    }

    boolean isReadyToStart();

    void start();

    void uninstall();

    void addNextState(State state);

    void broadcast(String... message);

    StateSeries getStateSeries();

    void setStateSeries(StateSeries stateSeries);

    TaskGroup getTaskManager();

    SpectatorManager getSpectatorManager();

    String getId();
}
