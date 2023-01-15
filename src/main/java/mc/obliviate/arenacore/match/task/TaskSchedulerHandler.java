package mc.obliviate.arenacore.match.task;

import mc.obliviate.arenacore.util.Preconditions;

public class TaskSchedulerHandler {

    private static ITaskScheduler instance;

    public static void setInstance(ITaskScheduler instance) {
        TaskSchedulerHandler.instance = instance;
    }

    public static ITaskScheduler getInstance() {
        Preconditions.checkNotNull(instance, "TaskSchedulerHandler instance is not initialized!");
        return instance;
    }

}
