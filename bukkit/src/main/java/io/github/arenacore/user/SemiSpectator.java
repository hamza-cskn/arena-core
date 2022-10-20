package io.github.arenacore.user;

import io.github.arenacore.match.Match;
import org.bukkit.entity.Player;

/**
 * Purpose of this class
 * storing SEMI SPECTATOR players.
 * <p>
 * SEMI SPECTATORS
 * Spectator players from a game,
 * they are member.
 */
public class SemiSpectator extends User implements ISpectator {

    private final Match match;

    SemiSpectator(Player player, Match match) {
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
