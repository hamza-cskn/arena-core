package mc.obliviate.arenacore.base.player;

import mc.obliviate.arenacore.base.inventory.BasePlayerInventory;
import mc.obliviate.arenacore.base.position.Position;

import java.util.UUID;

public interface BasePlayer {

    UUID uuid();

    void sendMessage(String message);

    void sendTitle(String title, String subtitle, int fadein, int stay, int fadeout);

    default void sendTitle(String title, String subtitle) {
        sendTitle(title, subtitle, 20, 20, 20);
    }

    void sendAction(String message);

    boolean teleport(Position to);

    Position position();

    BasePlayerInventory inventory();

    float experience();

    void experience(float newExperience);

    double health();

    void health(double newHealth);

    void flight(boolean newMode);

    boolean flight();
}
