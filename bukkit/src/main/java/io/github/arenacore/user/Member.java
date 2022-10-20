package io.github.arenacore.user;

import io.github.arenacore.match.Match;
import org.bukkit.entity.Player;

public class Member extends User implements IUser {

    private final Match match;

    Member(final Player player, Match match) {
        super(player);
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public static class Builder {

        private final User user;

        public Builder(User user) {
            this.user = user;
        }

        public Player getPlayer() {
            return user.getPlayer();
        }

        public User getUser() {
            return user;
        }

    }
}
