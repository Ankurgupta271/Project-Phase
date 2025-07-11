# Smart Task Scheduler

A JavaFX application to manage tasks with priorities and deadlines, featuring reminders and task persistence.

## Features

- Add tasks with title, priority (High, Medium, Low), and deadline.
- View all scheduled tasks in a list.
- Task reminders triggered on deadline.
- Tasks saved and loaded from a file (persistent storage).
- Simple and clean user interface with styling.

## Screenshots

### Adding a New Task

![Add Task](screenshots/1.png)

### Reminder Console Output

![Reminder Console](screenshots/2.png)

> **Note:** Screenshots are for illustration; update paths with your actual images.

## Requirements

- Java 17 or later
- JavaFX SDK 17 or later
- Gson library (for JSON serialization)
- Compatible OS (Windows, macOS, Linux)

## Setup & Running

### 1. Clone the repository

```bash
git clone <repository-url>
cd SmartTaskScheduler

```
### Compile And Run
```bash
javac --module-path "path_to_javafx_lib" --add-modules javafx.controls,javafx.fxml -cp "lib/gson-2.10.1.jar" -d bin src/**/*.java
java --module-path "path_to_javafx_lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib/gson-2.10.1.jar" TaskApp

