/*
AS2021617
//J.A.D.S.K Nanayakkara
*/


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    // Constructor with parameters
    public Task(int taskId, String title, String description, LocalDate dueDate, boolean completed) {
        this.id = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed; // Default status is set to false (not completed)
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Invalid title. Title cannot be empty.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid description. Description cannot be empty.");
        }
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate != null && dueDate.isAfter(LocalDate.now())) {
            this.dueDate = dueDate;
        } else {
            throw new IllegalArgumentException("Invalid due date. Due date must be a future date.");
        }
    }

    public boolean isStatus() {
        return completed;
    }

    public void setStatus(boolean status) {
        this.completed = status;
    }

    public void displayTask() {
        System.out.println("Task id: " + id);
        System.out.println("Task title: " + title);
        System.out.println("Task description: " + description);
        System.out.println("Task due date: " + dueDate);
        System.out.println("Task status: " + (completed ? "Completed" : "Not Completed")+"\n");
    }


    public void updateTask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the task title: ");
        String newTitle = sc.nextLine();
        setTitle(newTitle);

        System.out.println("Enter the task description: ");
        String newDescription = sc.nextLine();
        setDescription(newDescription);

        System.out.println("Enter the task due date (dd/MM/yyyy): ");
        String dueDateString = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate newDueDate = LocalDate.parse(dueDateString, formatter);
            setDueDate(newDueDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.");
        }

        System.out.println("Is the task completed? (true/false): ");
        String statusInput = sc.nextLine();
        if ("true".equalsIgnoreCase(statusInput) || "false".equalsIgnoreCase(statusInput)) {
            setStatus(Boolean.parseBoolean(statusInput));
        } else {
            throw new IllegalArgumentException("Invalid input for task status. Please enter true or false.");
        }
    }
}
