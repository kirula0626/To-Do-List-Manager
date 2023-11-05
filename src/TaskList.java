/*
AS2021617
//J.A.D.S.K Nanayakkara
*/


import java.util.List;

public interface TaskList {
    void addTask(Task task);
    boolean removeTask(int taskId) throws IllegalArgumentException;
    void markTaskAsCompleted(int taskId) throws IllegalArgumentException;
    List<Task> getAllTasks();
    List<Task> getCompletedTasks();
    List<Task> getTasksDueToday();
}





