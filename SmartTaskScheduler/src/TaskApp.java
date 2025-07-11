import model.Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.FileHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskApp extends Application {
    private TaskManager taskManager = new TaskManager();
    private ReminderService reminderService = new ReminderService();
    private ListView<String> listView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        List<Task> loadedTasks = FileHandler.loadTasks();
        for (Task task : loadedTasks) {
            taskManager.addTask(task);
            reminderService.scheduleReminder(task, () -> System.out.println("Reminder: " + task.getTitle()));
        }

        TextField titleField = new TextField();
        titleField.setPromptText("Task Title");

        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("1 (High)", "2 (Medium)", "3 (Low)");
        priorityBox.setValue("2 (Medium)");

        DatePicker datePicker = new DatePicker();
        TextField hourField = new TextField("12");
        TextField minuteField = new TextField("00");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            int priority = Integer.parseInt(priorityBox.getValue().substring(0, 1));
            LocalDateTime deadline = datePicker.getValue()
                    .atTime(Integer.parseInt(hourField.getText()), Integer.parseInt(minuteField.getText()));

            Task task = new Task(title, priority, deadline);
            taskManager.addTask(task);
            reminderService.scheduleReminder(task, () -> System.out.println("Reminder: " + task.getTitle()));
            refreshList();
        });

        VBox root = new VBox(10, titleField, priorityBox, datePicker, hourField, minuteField, addButton, listView);
        refreshList();

        Scene scene = new Scene(root, 400, 400);
        System.out.println(getClass().getResource("/style.css"));

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());





        primaryStage.setScene(scene);
        primaryStage.setTitle("Smart Task Scheduler");
        primaryStage.show();
    }

    private void refreshList() {
        listView.getItems().clear();
        taskManager.getAllTasks().forEach(task -> listView.getItems().add(task.toString()));
    }

    @Override
    public void stop() {
        FileHandler.saveTasks(new ArrayList<>(taskManager.getAllTasks()));
        reminderService.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
