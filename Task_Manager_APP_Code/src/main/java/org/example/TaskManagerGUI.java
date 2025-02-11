package org.example;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class TaskManagerGUI extends Application {
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private TableView<Task> taskTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Manager");

        // Create Task Table
        taskTable = new TableView<>();
        taskTable.setItems(tasks);

        // Set up Table columns
        TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Task, String> dueDateColumn = new TableColumn<>("Due Date");
        dueDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDueDate().toString()));

        TableColumn<Task, String> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));

        TableColumn<Task, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        taskTable.getColumns().addAll(descriptionColumn, dueDateColumn, priorityColumn, categoryColumn);

        // Create TextFields and Buttons for adding tasks
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter task description");

        TextField priorityField = new TextField();
        priorityField.setPromptText("Priority (Low, Medium, High)");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category (Work, Personal)");

        DatePicker dueDatePicker = new DatePicker();

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(e -> addTask(descriptionField, priorityField, categoryField, dueDatePicker));

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));
        layout.getChildren().addAll(
                new Label("Task Manager"),
                new HBox(10, descriptionField, priorityField, categoryField, dueDatePicker),
                addTaskButton,
                taskTable
        );

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add task to the table
    private void addTask(TextField descriptionField, TextField priorityField, TextField categoryField, DatePicker dueDatePicker) {
        String description = descriptionField.getText();
        String priority = priorityField.getText();
        String category = categoryField.getText();
        LocalDate dueDate = dueDatePicker.getValue();

        if (description.isEmpty() || priority.isEmpty() || category.isEmpty() || dueDate == null) {
            // Handle empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled.");
            alert.showAndWait();
        } else {
            Task newTask = new Task(description, dueDate, priority, category);
            tasks.add(newTask);

            // Clear input fields
            descriptionField.clear();
            priorityField.clear();
            categoryField.clear();
            dueDatePicker.setValue(null);

            // Optionally: Schedule reminder here (as previously explained)
            TaskReminder.scheduleReminder(newTask);
        }
    }
}

