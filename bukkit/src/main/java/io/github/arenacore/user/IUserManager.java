package io.github.arenacore.user;

import io.github.arenacore.match.IMatch;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IUserManager {

    void loadUser(Player player);

    void switchTo(UUID playerUniqueId, IUser user);

    IMember switchMember(IUser user, IMatch match);

    IUser switchUser(IUser user);

    IPureSpectator switchPureSpectator(IUser user, IMatch match);

    ISemiSpectator switchSemiSpectator(IUser user, IMatch match);

    Optional<IMember> getMember(UUID playerUniqueId);

    ISpectator getSpectator(UUID playerUniqueId);

    IUser getUser(UUID playerUniqueId);

    boolean isMember(UUID playerUniqueId);

    boolean isSpectator(UUID playerUniqueId);

    Map<UUID, IUser> getUserMap();


}
