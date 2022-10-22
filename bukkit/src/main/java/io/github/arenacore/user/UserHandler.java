package io.github.arenacore.user;

public class UserHandler {

    public static IUserManager instance;

    public static void setInstance(IUserManager instance) {
        UserHandler.instance = instance;
    }

    public static IUserManager getInstance() {
        return instance;
    }

}
