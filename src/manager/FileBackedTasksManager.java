package manager;


import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileBackedTasksManager extends InMemoryTasksManager {

    File file = new File("Tasks.csv");


    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);

        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);

        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);

        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        return super.getTasks();
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return super.getEpics();
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return super.getSubtasks();
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        return super.getEpicSubtasks(epicId);
    }

    @Override
    public Task getTask(int taskId) {
        return super.getTask(taskId);
    }

    @Override
    public Epic getEpic(int epicId) {
        return super.getEpic(epicId);
    }

    @Override
    public Subtask getSubtask(int subtaskId) {
        return super.getSubtask(subtaskId);
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(file);

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
        writer.close();

    }


    public void loadFromFile() throws IOException {
        Path filePath = Path.of("Tasks.csv");
        String content = Files.readString(filePath);
        fromString(content);
    }

    public void fromString(String value) {
        List<String> tasksString = new ArrayList<>();

        String[] lines = value.split("\n");

        Collections.addAll(tasksString, lines);

        int i;

        for (String s :tasksString){

            List<Integer> SubtaskId = new ArrayList<>();

            if (s.contains("[")){
                String[] idSub =  s.substring(s.indexOf("[")+1, s.lastIndexOf("]")).replace(", ", " ").split(" ");

                for (String u :idSub){
                    i = Integer.parseInt (u);
                    SubtaskId.add(i);
                }

                String[] epic = s.split(",");
                Epic epicNew = new Epic(Integer.parseInt (epic[0]), epic[2], epic[4], Status.fromString(epic[3]), SubtaskId);
                addEpic(epicNew);
            }

            String[] task = s.split(",");

            if (task[1].equals("TASK")) {
                Task task1 = new Task(task[2], task[4], Status.fromString(task[3]), Integer.parseInt (task[0]));
                addTask(task1);
            }

            if (task[1].equals("SUBTASK")) {
                Subtask subtask = new Subtask(Integer.parseInt (task[0]), task[2], task[3], Status.fromString(task[3]), Integer.parseInt (task[5]));
                addSubtask(subtask);
            }
        }

    }
}


