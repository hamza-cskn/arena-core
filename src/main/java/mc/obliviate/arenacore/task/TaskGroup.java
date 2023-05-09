package mc.obliviate.arenacore.task;

import java.util.Map;

public interface TaskGroup {

	Task newTask(String taskName);

	Map<String, Task> getTasks();

	default void cancel() {
		getTasks().values().forEach(Task::cancel);
	}

}
