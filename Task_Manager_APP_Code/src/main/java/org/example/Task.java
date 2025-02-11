package org.example;

import java.time.LocalDate;

public class Task {
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String category;

    // Constructor
    public Task(String description, LocalDate dueDate, String priority, String category) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }
}
