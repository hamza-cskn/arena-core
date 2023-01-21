package mc.obliviate.arenacore.match;

import mc.obliviate.arenacore.match.reason.MatchLeaveReason;
import mc.obliviate.arenacore.match.spectator.ISpectatorManager;
import mc.obliviate.arenacore.match.task.IArenaTaskGroup;
import mc.obliviate.arenacore.user.IMember;
import mc.obliviate.arenacore.user.IUser;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;

import java.util.Set;

public interface IMatch {

    boolean joinAsMember(IUser user);

    boolean joinAsSpectator(IUser user);

    void leave(IMember member, MatchLeaveReason reason);

    default void leave(IMember member) {
        leave(member, MatchLeaveReason.UNKNOWN);
    }

    boolean isReadyToStart();

    void start();

    void uninstall();

    void addNextState(State state);

    void broadcast(String... message);

    StateSeries getStateSeries();

    void setStateSeries(StateSeries stateSeries);

    IArenaTaskGroup getTaskManager();

    ISpectatorManager getSpectatorManager();

    String getId();
}
