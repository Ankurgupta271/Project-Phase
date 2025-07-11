import java.util.PriorityQueue;
import model.Task;

public class TaskManager {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public Task getNextTask() {
        return taskQueue.peek();
    }

    public Task removeTask() {
        return taskQueue.poll();
    }

    public PriorityQueue<Task> getAllTasks() {
        return new PriorityQueue<>(taskQueue);
    }

    public void clearTasks() {
        taskQueue.clear();
    }
}
