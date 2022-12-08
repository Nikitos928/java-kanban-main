package manager;


import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public Task getTask(int taskId) {
        Task task = super.getTask(taskId);
        save();
        return task;
    }

    @Override
    public Epic getEpic(int epicId) {
        Epic epic = super.getEpic(epicId);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtask(int subtaskId) {
        Subtask subtask = super.getSubtask(subtaskId);
        save();
        return subtask;
    }

    private String historyToString(HistoryManager manager) {
        String hestory = "";
        int x = 0;
        for (Task task : manager.getHistory()) {
            if (getHistory().size() - 1 == x) {
                hestory = hestory + Integer.toString(task.getId());
                x++;
            } else {
                hestory = hestory + Integer.toString(task.getId()) + ",";
                x++;
            }
        }
        return hestory;
    }

    private void save() {
        try {
            FileWriter writer = new FileWriter(file);
            try (writer) {
                if (getTasks().size() > 0) {
                    for (Task task : getTasks()) {
                        writer.write(task.getId() + "," +
                                Type.TASK + "," +
                                task.getName() + "," +
                                task.getStatus() + "," +
                                task.getDescription() + "\n");
                        writer.flush();
                    }
                }

                if (getEpics().size() > 0) {
                    for (Epic epic : getEpics()) {
                        writer.write(epic.getId() + "," +
                                Type.EPIC + "," + epic.getName() + "," +
                                epic.getStatus() + "," +
                                epic.getDescription() + "," +
                                epic.getSubtaskIds() + "\n");
                        writer.flush();
                    }
                }

                if (getSubtasks().size() > 0) {
                    for (Subtask subtask : getSubtasks()) {
                        writer.write(subtask.getId() + "," +
                                Type.SUBTASK + "," +
                                subtask.getName() + "," +
                                subtask.getStatus() + "," +
                                subtask.getDescription() + "," +
                                subtask.getEpicId() + "\n");
                        writer.flush();
                    }
                }
                if (historyReturn().getHistory().size() != 0) {
                    writer.write("\n");
                    writer.write(historyToString(historyReturn()));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file);
        Path filePath = Path.of(file.getPath());
        try {
            String content = Files.readString(filePath);
            List<String> tasksString = new ArrayList<>();
            String[] lines = content.split("\n");
            Collections.addAll(tasksString, lines);
            for (String taskString : tasksString) {

                if (taskString.length() != 0) {
                    String[] type = taskString.split(",");
                    if (type[1].equals(Type.TASK.toString())) {
                        fileBackedTaskManager.addTask(fromString(taskString));

                    }
                    if (type[1].equals(Type.EPIC.toString())) {
                        fileBackedTaskManager.addEpic((Epic) fromString(taskString));
                    }
                    if (type[1].equals(Type.SUBTASK.toString())) {
                        fileBackedTaskManager.addSubtask((Subtask) fromString(taskString));
                    }
                }
            }
            if (!tasksString.get(tasksString.size() - 1).contains("SUBTASK")
                    && !tasksString.get(tasksString.size() - 1).contains("TASK")
                    && !tasksString.get(tasksString.size() - 1).contains("EPIC")) {
                for (Integer taskId : historyFromString(content)) {

                    for (Task task : fileBackedTaskManager.getTasks()) {
                        if (task.getId() == taskId) {
                            fileBackedTaskManager.getTask(taskId);
                        }
                    }

                    for (Epic epic : fileBackedTaskManager.getEpics()) {
                        if (epic.getId() == taskId) {
                            fileBackedTaskManager.getEpic(taskId);
                        }
                    }

                    for (Subtask subtask : fileBackedTaskManager.getSubtasks()) {
                        if (subtask.getId() == taskId) {
                            fileBackedTaskManager.getSubtask(taskId);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileBackedTaskManager;
    }

    private static Task fromString(String value) {
        List<Integer> subtaskId = new ArrayList<>();
        if (value.contains("[")) {
            String[] epic = value.split(",");
            return new Epic(Integer.parseInt(epic[0]), epic[2], epic[4], Status.fromString(epic[3]), subtaskId);
        }

        String[] task = value.split(",");

        if (task[1].equals(Type.TASK.toString())) {
            return new Task(task[2], task[4], Status.fromString(task[3]), Integer.parseInt(task[0]));
        } else {
            return new Subtask(
                    Integer.parseInt(task[0]),
                    task[2],
                    task[4],
                    Status.fromString(task[3]),
                    Integer.parseInt(task[5]));
        }
    }

    private static List<Integer> historyFromString(String value) {
        List<String> tasksString = new ArrayList<>();
        List<Integer> fromHistoriTaskId = new ArrayList<>();
        String[] lines = value.split("\n");
        Collections.addAll(tasksString, lines);
        String[] histori = tasksString.get(tasksString.size() - 1).split(",");
        for (String historiSplit : histori) {
            fromHistoriTaskId.add(Integer.parseInt(historiSplit));
        }
        return fromHistoriTaskId;
    }

}


