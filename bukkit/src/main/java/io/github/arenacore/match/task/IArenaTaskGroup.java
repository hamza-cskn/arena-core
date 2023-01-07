package io.github.arenacore.match.task;

public interface IArenaTaskGroup extends IArenaTask {

    IArenaTask newTask(String taskName);

}
