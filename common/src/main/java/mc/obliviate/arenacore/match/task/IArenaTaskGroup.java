package mc.obliviate.arenacore.match.task;

public interface IArenaTaskGroup extends IArenaTask {

    IArenaTask newTask(String taskName);

}
