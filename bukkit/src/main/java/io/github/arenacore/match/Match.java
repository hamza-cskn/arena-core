package io.github.arenacore.match;

import com.google.common.base.Preconditions;
import io.github.arenacore.match.spectator.MatchSpectatorManager;
import io.github.arenacore.user.User;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Match implements IMatch {

    private final Set<User> users = new HashSet<>();
    private final StateSeries stateSeries = new StateSeries();
    private final String matchUniqueId = UUID.randomUUID().toString().split("-")[0];
    private final MatchSpectatorManager matchSpectatorManager = new MatchSpectatorManager(this);

    @Override
    public boolean joinAsMember(User user) {
        Preconditions.checkState(!stateSeries.getStarted(), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ") because match state is not null.");
        if (users.contains(user)) return false;
        Preconditions.checkState(users.add(user), "User " + user.getPlayer().getName() + " could not joined to " + this + "(" + matchUniqueId + ").");
        broadcast("Player " + user.getPlayer().getName() + " joined to the match. ");
        return true;
    }

    @Override
    public boolean joinAsSpectator(User user) {
        return this.matchSpectatorManager.spectate(user);
    }

    @Override
    public void leave(User user, MatchLeaveReason reason) {
        users.remove(user);
        if (this.users.size() == 0) {
            stateSeries.skip();
        } else {
            broadcast("Player " + user.getPlayer().getName() + " left from the match. ");
        }
        if (reason != MatchLeaveReason.DISCONNECT) {
            user.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            user.getPlayer().sendMessage("You left from match.");
        }
    }

    public void start() {
        stateSeries.start();
    }

    public void finish() {
        stateSeries.end();
    }

    public void addNextState(State state) {
        stateSeries.addNext(state);
    }

    public final void broadcast(String... message) {
        this.users.forEach(user -> user.getPlayer().sendMessage(message));
        Arrays.stream(message).forEach(Bukkit.getLogger()::info);
    }

    public MatchSpectatorManager getSpectatorManager() {
        return matchSpectatorManager;
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getId() {
        return matchUniqueId;
    }

    public StateSeries getStateSeries() {
        return stateSeries;
    }
}
