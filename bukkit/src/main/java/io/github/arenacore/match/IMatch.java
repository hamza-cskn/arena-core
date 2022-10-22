package io.github.arenacore.match;

import io.github.arenacore.match.spectator.MatchSpectatorManager;
import io.github.arenacore.match.task.MatchTaskManager;
import io.github.arenacore.match.reason.MatchLeaveReason;
import io.github.arenacore.user.IMember;
import io.github.arenacore.user.IUser;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;

import java.util.Set;

public interface IMatch {

    boolean joinAsMember(IUser user);

    boolean joinAsSpectator(IUser user);

    void leave(IMember member, MatchLeaveReason reason);

    boolean isReadyToStart();

    void start();

    void finish();

    void addNextState(State state);

    void broadcast(String... message);

    StateSeries getStateSeries();

    void setStateSeries(StateSeries stateSeries);

    MatchTaskManager getTaskManager();

    MatchSpectatorManager getSpectatorManager();

    Set<IMember> getMembers();

    String getId();
}
