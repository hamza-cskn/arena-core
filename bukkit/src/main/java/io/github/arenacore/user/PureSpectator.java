package io.github.arenacore.user;

import io.github.arenacore.match.Match;
import org.bukkit.entity.Player;

/**
 * Purpose of this class
 * storing PURE SPECTATOR players.
 * <p>
 * PURE SPECTATORS
 * Spectator players from out of game,
 * not member.
 */
public class PureSpectator extends User implements ISpectator {

    private final Match match;

    PureSpectator(Player player, Match match) {
        super(player);
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public void spectate(Match match) {

    }

    @Override
    public void unspectate() {

    }
}
