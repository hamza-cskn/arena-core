package mc.obliviate.arenacore.user;

import mc.obliviate.arenacore.util.Preconditions;

public class UserHandler {

    private static IUserManager instance;

    public static void setInstance(IUserManager instance) {
        UserHandler.instance = instance;
    }

    public static IUserManager getInstance() {
        Preconditions.checkNotNull(instance, "UserHandler instance is not initialized!");
        return instance;
    }

}
