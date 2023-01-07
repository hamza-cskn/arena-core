package io.github.arenacore.user;

import com.google.common.base.Preconditions;

public class UserHandler {

    public static IUserManager instance;

    public static void setInstance(IUserManager instance) {
        UserHandler.instance = instance;
    }

    public static IUserManager getInstance() {
        Preconditions.checkNotNull(instance, "UserHandler instance is not initialized!");
        return instance;
    }

}
