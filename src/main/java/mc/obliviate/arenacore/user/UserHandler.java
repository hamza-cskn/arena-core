package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.match.Match;
import mc.obliviate.arenacore.util.Preconditions;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class UserHandler {

	private static UserHandler instance;

	public UserHandler() {
		UserHandler.instance = this;
	}

	public static UserHandler getInstance() {
		Preconditions.checkNotNull(instance, "UserManager instance is not initialized!");
		return instance;
	}

	abstract public void loadUser(UUID uuid);

	abstract public void switchTo(UUID playerUniqueId, User user);

	abstract public Member switchMember(User user, Match match);

	abstract public User switchUser(User user);

	abstract public PureSpectator switchPureSpectator(User user, Match match);

	abstract public SemiSpectator switchSemiSpectator(User user, Match match);

	abstract public Optional<Member> findMember(UUID playerUniqueId);

	abstract public Optional<Spectator> findSpectator(UUID playerUniqueId);

	abstract public User getUser(UUID playerUniqueId);

	abstract public boolean isMember(UUID playerUniqueId);

	abstract public boolean isSpectator(UUID playerUniqueId);

	abstract public Map<UUID, User> getUserMap();


}
