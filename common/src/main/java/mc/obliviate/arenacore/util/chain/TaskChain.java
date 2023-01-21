package mc.obliviate.arenacore.util.chain;

import mc.obliviate.arenacore.match.task.TaskScheduler;

import java.util.function.Function;


public class TaskChain {

    private final Function<TaskChain, Boolean> task;
    private TaskChain next;
    private final boolean sync;

    private TaskChain(Function<TaskChain, Boolean> task, boolean sync) {
        this.task = task;
        this.sync = sync;
    }

    public void addToTail(TaskChain task) {
        if (this.next == null) {
            this.next = task;
            return;
        }
        this.next.addToTail(task);
    }

    public void sync(Function<TaskChain, Boolean> task) {
        insertNext(task, true);
    }

    public void sync(Runnable task) {
        insertNext(task, true);
    }

    public void async(Function<TaskChain, Boolean> task) {
        insertNext(task, false);
    }

    public void async(Runnable task) {
        insertNext(task, false);
    }

    private void insertNext(Function<TaskChain, Boolean> task, boolean sync) {
        this.insertNext(new TaskChain(task, sync));
    }

    private void insertNext(Runnable task, boolean sync) {
        this.insertNext(new TaskChain(chain -> {
            task.run();
            return true;
        }, sync));
    }

    private void insertNext(TaskChain task) {
        task.next = this.next;
        this.next = task;
    }

    public void next() {
        if (this.next != null)
            this.next.execute();
    }

    public void execute() {
        if (this.sync) {
            TaskScheduler.getInstance().sync(this::runTask);
        } else {
            TaskScheduler.getInstance().async(this::runTask);
        }
    }

    public void runTask() {
        if (this.task.apply(this)) {
            this.next();
        }

    }
}

