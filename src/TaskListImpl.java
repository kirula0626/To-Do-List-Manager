/*
AS2021617
//J.A.D.S.K Nanayakkara
*/


import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskListImpl implements TaskList {
    private List<Task> tasks;

    public TaskListImpl() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(task);
        reassignTaskIds();
    }

    @Override
    public boolean removeTask(int taskId) {
        Iterator<Task> iterator = tasks.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == taskId) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        // Update task IDs after removal
        if (removed) {
            reassignTaskIds();
        }

        return removed;
    }


    @Override
    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setStatus(true);
                return;
            }
        }
        throw new IllegalArgumentException("Task with ID " + taskId + " not found");
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isStatus()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    @Override
    public List<Task> getTasksDueToday() {
        List<Task> tasksDueToday = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (task.getDueDate().isEqual(today)) {
                tasksDueToday.add(task);
            }
        }
        return tasksDueToday;
    }

    private void reassignTaskIds() {
        int taskIdCounter = 1;
        for (Task task : tasks) {
            task.setId(taskIdCounter++);
        }
    }

}
