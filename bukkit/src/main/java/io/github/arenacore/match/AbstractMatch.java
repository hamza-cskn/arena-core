package io.github.arenacore.match;

import com.google.common.base.Preconditions;
import io.github.arenacore.match.reason.MatchLeaveReason;
import io.github.arenacore.match.spectator.MatchSpectatorManager;
import io.github.arenacore.match.task.TaskGroup;
import io.github.arenacore.user.IMember;
import io.github.arenacore.user.IUser;
import io.github.arenacore.user.UserHandler;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.util.*;

public abstract class AbstractMatch implements IMatch {

    private Duration updatePeriod = Duration.ofSeconds(1);
    private final Set<IMember> members = new HashSet<>();
    private StateSeries stateSeries;
    private final String matchUniqueId = UUID.randomUUID().toString().split("-")[0];
    private final MatchSpectatorManager matchSpectatorManager = new MatchSpectatorManager(this);
    private final TaskGroup taskGroup = new TaskGroup();

    @Override
    public boolean joinAsMember(IUser user) {
        Preconditions.checkNotNull(stateSeries);
        Preconditions.checkState(!stateSeries.getStarted(), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ") because match state is not null.");
        Preconditions.checkState(!(user instanceof IMember), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ") because they is member.");
        Preconditions.checkState(!this.isIn(user.getPlayer().getUniqueId()), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ") because they is in already.");
        final IMember member = UserHandler.getInstance().switchMember(user, this);
        Preconditions.checkState(members.add(member), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ").");
        if (this.isReadyToStart()) {
            this.start();
        }
        return true;
    }

    public boolean isIn(UUID playerUniqueId) {
        for (IMember m : members) {
            if (m.getPlayer().getUniqueId().equals(playerUniqueId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean joinAsSpectator(IUser user) {
        return this.matchSpectatorManager.spectate(user);
    }

    @Override
    public void leave(IMember member, MatchLeaveReason reason) {
        members.remove(member);
        UserHandler.getInstance().switchUser(member);
        if (this.members.size() == 0) {
            stateSeries.skip();
        }
    }

    @Override
    public void start() {
        stateSeries.start();
        taskGroup.newTask("heart-beat").every(1).run(stateSeries::update);
    }

    @Override
    public void finish() {
        new ArrayList<>(members).forEach(m -> leave(m, MatchLeaveReason.GAME_END));
        stateSeries.end();
    }

    @Override
    public void addNextState(State state) {
        stateSeries.addNext(state);
    }

    @Override
    public final void broadcast(String... message) {
        this.members.forEach(user -> user.getPlayer().sendMessage(message));
        Arrays.stream(message).forEach(Bukkit.getLogger()::info);
    }

    @Override
    public StateSeries getStateSeries() {
        return stateSeries;
    }

    @Override
    public void setStateSeries(StateSeries stateSeries) {
        this.stateSeries = stateSeries;
    }

    @Override
    public TaskGroup getTaskManager() {
        return taskGroup;
    }

    @Override
    public MatchSpectatorManager getSpectatorManager() {
        return matchSpectatorManager;
    }

    @Override
    public Set<IMember> getMembers() {
        return members;
    }

    @Override
    public String getId() {
        return matchUniqueId;
    }

    public Duration getUpdatePeriod() {
        return updatePeriod;
    }

    public void setUpdatePeriod(Duration updatePeriod) {
        this.updatePeriod = updatePeriod;
    }
}
