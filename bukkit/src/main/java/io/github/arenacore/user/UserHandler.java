package io.github.arenacore.user;

import io.github.arenacore.match.Match;
import org.bukkit.entity.Player;

import java.util.*;

public class UserHandler {

    private static final Map<UUID, IUser> USER_MAP = new HashMap<>();

    public static void loadUser(Player player) {
        registerUser(player.getUniqueId(), new User(player));
    }

    private static void registerUser(UUID uuid, IUser user) {
        USER_MAP.put(uuid, user);
    }

    public static void switchTo(UUID playerUniqueId, IUser user) {
        registerUser(playerUniqueId, user);
    }

    public static Member switchMember(IUser user, Match match) {
        final Member member = new Member(user.getPlayer(), match);
        switchTo(user.getPlayer().getUniqueId(), member);
        return member;
    }

    public static User switchUser(IUser user) {
        final User rUser = new User(user.getPlayer());
        switchTo(user.getPlayer().getUniqueId(), rUser);
        return rUser;
    }

    public static PureSpectator switchPureSpectator(IUser user, Match match) {
        final PureSpectator pureSpectator = new PureSpectator(user.getPlayer(), match);
        switchTo(user.getPlayer().getUniqueId(), pureSpectator);
        return pureSpectator;
    }

    public static SemiSpectator switchSemiSpectator(IUser user, Match match) {
        final SemiSpectator semiSpectator = new SemiSpectator(user.getPlayer(), match);
        switchTo(user.getPlayer().getUniqueId(), semiSpectator);
        return semiSpectator;
    }

    public static Optional<Member> getMember(UUID playerUniqueId) {
        final IUser user = getUser(playerUniqueId);
        if (user instanceof Member) {
            return Optional.of((Member) user);
        }
        return Optional.empty();
    }

    public static PureSpectator getSpectator(UUID playerUniqueId) {
        final IUser user = getUser(playerUniqueId);
        if (user instanceof PureSpectator) {
            return (PureSpectator) user;
        }
        return null;
    }


    public static IUser getUser(UUID playerUniqueId) {
        return USER_MAP.get(playerUniqueId);
    }

    public static boolean isMember(UUID playerUniqueId) {
        return getUser(playerUniqueId) instanceof Member;
    }

    public static boolean isSpectator(UUID playerUniqueId) {
        return getUser(playerUniqueId) instanceof PureSpectator;
    }

    public static Map<UUID, IUser> getUserMap() {
        return Collections.unmodifiableMap(USER_MAP);
    }
}
