package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.match.IMatch;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IUserManager<P> {

    void loadUser(P player);

    void switchTo(UUID playerUniqueId, IUser user);

    IMember switchMember(IUser user, IMatch match);

    IUser switchUser(IUser user);

    IPureSpectator switchPureSpectator(IUser user, IMatch match);

    ISemiSpectator switchSemiSpectator(IUser user, IMatch match);

    Optional<IMember> findMember(UUID playerUniqueId);

    Optional<ISpectator> findSpectator(UUID playerUniqueId);

    IUser getUser(UUID playerUniqueId);

    boolean isMember(UUID playerUniqueId);

    boolean isSpectator(UUID playerUniqueId);

    Map<UUID, IUser> getUserMap();
}
