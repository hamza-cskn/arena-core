package mc.obliviate.arenacore.match.task;

import mc.obliviate.arenacore.task.Task;
import mc.obliviate.arenacore.task.TaskGroup;
import mc.obliviate.arenacore.task.TaskManager;

import java.util.*;

public class MatchTaskGroup implements TaskGroup {

    private static final List<MatchTaskGroup> TASK_GROUPS = new ArrayList<>();
    private final Map<String, Task> tasks = new HashMap<>();

    public MatchTaskGroup() {
        MatchTaskGroup.TASK_GROUPS.add(this);
    }

    private void registerTask(String taskName, Task task) {
        tasks.put(taskName, task);
    }

    /**
     * task names defines purpose of task
     * and task manager can cancel tasks using
     * their names.
     * <p>
     * task manager cancels tasks with checking their
     * first part of names. for example,
     * <p>
     * <b>TASK 1:</b> round.end-timer-task<br>
     * <b>TASK 2:</b> round.ending-task<br>
     * <b>TASK 3:</b> round.scoreboard-task
     * <p>
     * when you call cancelTasks("round")
     * task manager checks first part of
     * the tasks, and it will cancel all of them.
     * <p>
     * when you call cancelTasks("round.e")
     * task manager will cancel all tasks
     * except "round.scoreboard-task"
     */
    public Task newTask(String taskName) {
        Task MatchTask = TaskManager.getInstance().createTask();
        registerTask(taskName, MatchTask);
        return MatchTask;
    }

    public void cancelTask(String prefix) {
        tasks.forEach((taskName, task) -> {
            if (taskName.startsWith(prefix)) {
                task.cancel();
            }
        });
    }

    public void terminate() {
        this.cancel();
        MatchTaskGroup.TASK_GROUPS.remove(this);
    }

    @Override
    public Map<String, Task> getTasks() {
        return Collections.unmodifiableMap(tasks);
    }

    public static List<MatchTaskGroup> getTaskGroupSet() {
        return MatchTaskGroup.TASK_GROUPS;
    }
}
