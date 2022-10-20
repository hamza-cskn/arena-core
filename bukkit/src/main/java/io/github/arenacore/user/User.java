package io.github.arenacore.user;

import org.bukkit.entity.Player;

public class User implements IUser {

    //player uuid, duel user
    private final Player player;

    User(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

}
