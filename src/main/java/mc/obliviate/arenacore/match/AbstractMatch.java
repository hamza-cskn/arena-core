package mc.obliviate.arenacore.match;

import mc.obliviate.arenacore.match.reason.MatchLeaveReason;
import mc.obliviate.arenacore.match.spectator.ISpectatorManager;
import mc.obliviate.arenacore.match.spectator.MatchSpectatorManager;
import mc.obliviate.arenacore.match.team.MatchTeamManager;
import mc.obliviate.arenacore.user.IMember;
import mc.obliviate.arenacore.user.IUser;
import mc.obliviate.arenacore.user.UserHandler;
import mc.obliviate.arenacore.util.Preconditions;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateSeries;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMatch implements IMatch {

    private static final Set<AbstractMatch> MATCHES = new HashSet<>();
    private final Set<IMember> members = new HashSet<>();
    private StateSeries stateSeries;
    private final String matchUniqueId = UUID.randomUUID().toString().split("-")[0];
    private final ISpectatorManager matchSpectatorManager = new MatchSpectatorManager(this);
    private final MatchTeamManager matchTeamManager = new MatchTeamManager(this);
    private Set<UUID> alltimePlayers;

    public AbstractMatch() {
        AbstractMatch.MATCHES.add(this);
    }

    public static Set<AbstractMatch> getMatches() {
        return MATCHES;
    }

    @Override
    public boolean joinAsMember(IUser user) {
        Preconditions.checkNotNull(stateSeries);
        Preconditions.checkState(!stateSeries.getStarted(), "User " + user.getUniqueId() + " could not joined to " + this + "(" + matchUniqueId + ") because match state is not null.");
        Preconditions.checkState(!(user instanceof IMember), "User " + user.getUniqueId() + " could not joined to " + this + "(" + matchUniqueId + ") because they is member.");
        Preconditions.checkState(!this.isIn(user.getUniqueId()), "User " + user.getUniqueId() + " could not joined to " + this + "(" + matchUniqueId + ") because they is in already.");
        final IMember member = UserHandler.getInstance().switchMember(user, this);
        Preconditions.checkState(members.add(member), "User " + user.getUniqueId() + " could not joined to " + this + "(" + matchUniqueId + ").");
        if (this.isReadyToStart()) {
            this.start();
        }
        return true;
    }

    public boolean isIn(UUID playerUniqueId) {
        for (IMember member : members) {
            if (member.getUniqueId().equals(playerUniqueId)) {
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
        Preconditions.checkState(alltimePlayers == null, "You cannot start this game. It has registered players before.");
        stateSeries.start();
        alltimePlayers = Collections.unmodifiableSet(members.stream().map(IUser::getUniqueId).collect(Collectors.toSet()));
    }

    /**
     * This method must be called at end of the game. Kicks all players from the game.
     */
    @Override
    public void uninstall() {
        new ArrayList<>(members).forEach(m -> leave(m, MatchLeaveReason.GAME_END));
        stateSeries.end();
        AbstractMatch.MATCHES.remove(this);
    }

    @Override
    public void addNextState(State state) {
        stateSeries.addNext(state);
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
    public ISpectatorManager getSpectatorManager() {
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

}
