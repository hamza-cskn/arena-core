package io.github.arenacore.match;

import io.github.arenacore.user.User;

public interface IMatch {

    boolean joinAsMember(User user);

    boolean joinAsSpectator(User user);

    void leave(User user, MatchLeaveReason reason);

}
