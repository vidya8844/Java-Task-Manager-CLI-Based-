package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TaskReminder {

    // This method schedules a reminder for a given task
    public static void scheduleReminder(Task task) {
        // Get the due date of the task
        LocalDate dueDate = task.getDueDate();
        // Get the number of days between the current date and the task's due date
        long daysUntilDue = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);

        // If the task is due in 3 or fewer days, schedule a reminder
        if (daysUntilDue <= 3) {
            // Print a reminder message to the console (this can be extended to show a pop-up)
            System.out.println("Reminder: Task '" + task.getDescription() + "' is due in " + daysUntilDue + " days.");
        }
    }
}

