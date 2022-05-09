package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableList.toImmutableList;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

    public static void main(String[] args) {
        // The big method is called from various foreign places in the codebase
        bigUglyMethod(1, new Task(5));
        bigUglyMethod(2, new Task(4));
        bigUglyMethod(3, new Task(3));
        bigUglyMethod(4, new Task(2));
        bigUglyMethod(5, new Task(1));

        // TODO From my use-case #323, I call it too, to do more within:
        Task task = new Task(1);
        bigUglyMethod323(2, task);

    }

    static void bigUglyMethod(int b, Task task) {
        before(b, task);

        after(b);
    }

    static void bigUglyMethod323(int b, Task task) {
        before(b, task);

        System.out.println("Logic just for CR323 : " + task);

        after(b);
    }

    private static void after(int b) {
        System.out.println("More Complex Logic " + b);
        System.out.println("More Complex Logic " + b);
        System.out.println("More Complex Logic " + b);
    }

    private static void before(int b, Task task) {
        System.out.println("Complex Logic 1 " + task + " and " + b);
        System.out.println("Complex Logic 2 " + task);
        System.out.println("Complex Logic 3 " + task);
    }


    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

    // Lord gave us tests!
    public void bossLevelFluff323(List<Task> tasks, boolean cr323) {
        startAllTasks(tasks);

        for (Task task : tasks) {
            System.out.println("My Logic: " + task);
        }

        bossEnd(tasks);
    }

    public void bossLevelFluff(List<Task> tasks, boolean cr323) {
        startAllTasks(tasks);


        bossEnd(tasks);
    }

    private void bossEnd(List<Task> tasks) {
        int index = 0;
        for (Task task : tasks) {
            index++;
            System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
        }
        ImmutableList<Integer> taskIds = tasks.stream().map(Task::getId).collect(toImmutableList());
//        ImmutableList<Integer> taskIds = tasks.stream().map(Task::getId).collect(toImmutableList());
//        taskIds.add(1);


        System.out.println("Logic6 " + tasks.size());
        System.out.println("Task Ids: " + taskIds);
        System.out.println("Logic7");
    }

    private void startAllTasks(List<Task> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic3");
        for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();
        }
    }

    public void bossLevelNoFluff(List<Task> tasks) {
        System.out.println("Logic1");
        System.out.println("Logic2");
        System.out.println("Logic7 " + tasks);
        System.out.println("Logic7");
    }


}


class Task {
    private final int id;
    private boolean running;

    Task(int id) {
        this.id = id;
    }

    public void setRunning() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", running=" + running + '}';
    }
}