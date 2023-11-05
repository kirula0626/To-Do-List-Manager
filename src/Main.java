/*
AS2021617
//J.A.D.S.K Nanayakkara
*/

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    private static int lastAssignedId = 0;

    public static void main(String[] args) {
        TaskList taskList = new TaskListImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    Task newTask = createTask(scanner);
                    taskList.addTask(newTask);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    int taskIdToComplete = getTaskIdFromUser(scanner, "Enter Task ID to mark as completed: ");
                    taskList.markTaskAsCompleted(taskIdToComplete);
                    System.out.println("Task marked as completed!");
                    break;
                case 3:
                    int taskIdToRemove = getTaskIdFromUser(scanner, "Enter Task ID to remove: ");
                    if (taskList.removeTask(taskIdToRemove)) {
                        System.out.println("Task removed successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                    break;
                case 4:
                    List<Task> allTasks = taskList.getAllTasks();
                    displayTasks(allTasks);
                    break;
                case 5:
                    List<Task> completedTasks = taskList.getCompletedTasks();
                    displayTasks(completedTasks);
                    break;
                case 6:
                    List<Task> tasksDueToday = taskList.getTasksDueToday();
                    displayTasks(tasksDueToday);
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add Task");
        System.out.println("2. Mark Task as Completed");
        System.out.println("3. Remove Task");
        System.out.println("4. List All Tasks");
        System.out.println("5. List Completed Tasks");
        System.out.println("6. List Tasks Due Today");
        System.out.println("7. Exit\n");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        int choice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }

    private static Task createTask(Scanner scanner) {
        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        boolean isValidDate = false;

        while (!isValidDate) {
            try {
                System.out.print("Enter Due Date (in the format dd/MM/yyyy): ");
                String dueDateInput = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate parsedDate = LocalDate.parse(dueDateInput, formatter);

                // Check if the parsed date is not a past date
                if (!parsedDate.isBefore(LocalDate.now())) {
                    dueDate = parsedDate;
                    isValidDate = true;
                } else {
                    System.out.println("Invalid due date. Please enter a future date.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        int taskId = generateUniqueTaskId();
        return new Task(taskId, title, description, dueDate, false);
    }

    private static int getTaskIdFromUser(Scanner scanner, String message) {
        int taskId = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(message);
            try {
                taskId = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return taskId;
    }

    private static void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (Task task : tasks) {
                task.displayTask();
            }
        }
    }

    public static int generateUniqueTaskId() {
        return ++lastAssignedId;
    }
}
