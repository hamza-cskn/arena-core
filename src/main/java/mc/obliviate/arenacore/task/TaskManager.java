package mc.obliviate.arenacore.task;

import mc.obliviate.arenacore.util.Preconditions;

public abstract class TaskManager {

	private static TaskManager instance;

	public TaskManager() {
		TaskManager.instance = this;
	}

	public static TaskManager getInstance() {
		Preconditions.checkNotNull(instance, "TaskHandler instance is not initialized!");
		return instance;
	}

	abstract public Task createTask();

	abstract public TaskGroup createTaskGroup();

}
