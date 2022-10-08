package manager;

import task.Task;
import task.Epic;
import task.Subtask;

import java.util.*;


public class TaskManager {

    private int generatedId;

    private final HashMap<Integer, Task> tasks = new HashMap<>();

    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public int addTask(Task task) {
        task.setId(generatedId);
        tasks.put(task.getId(), task);
        generatedId += 1;
        return generatedId;
    }

    public int addEpic(Epic epic) {
        epic.setStatus("NEW");
        epic.setId(generatedId);
        epics.put(epic.getId(), epic);
        generatedId += 1;
        return generatedId;

    }

    public int addSubtask(Subtask subtask) {
        subtask.setId(generatedId);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId() - 1).addSubtaskId(generatedId);
        checkEpicStatus(epics.get(subtask.getEpicId() - 1));
        generatedId += 1;
        return generatedId;
    }


    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (int i = 0; i < epics.get(epicId).getSubtaskIds().size(); i++) {
            arrSubtasks.add(subtasks.get(epics.get(epicId).getSubtaskIds().get(i)));
        }
        return arrSubtasks;
    }

    public ArrayList<Task> getTask(int taskId) {
        ArrayList<Task> arrTask = new ArrayList<>();
        arrTask.add(tasks.get(taskId));
        return arrTask;
    }

    public ArrayList<Epic> getEpic(int epicId) {
        ArrayList<Epic> arrEpic = new ArrayList<>();
        arrEpic.add(epics.get(epicId));
        return arrEpic;
    }

    public ArrayList<Subtask> getSubtask(int subtaskId) {
        ArrayList<Subtask> arrSubtask = new ArrayList<>();
        arrSubtask.add(subtasks.get(subtaskId));
        return arrSubtask;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> arrTasks = new ArrayList<>();
        for (Integer key : tasks.keySet()) {
            arrTasks.add(tasks.get(key));
        }
        return arrTasks;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> arrEpics = new ArrayList<>();
        for (Integer key : epics.keySet()) {
            arrEpics.add(epics.get(key));
        }
        return arrEpics;
    }

    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (Integer key : subtasks.keySet()) {
            arrSubtasks.add(subtasks.get(key));
        }
        return arrSubtasks;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);

    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        checkEpicStatus(epics.get(subtask.getEpicId()));
    }

    public void updateEpic(Epic epic, Epic epicNew) {
        epics.get(epic.getId()).setName(epicNew.getName());
        epics.get(epic.getId()).setDescription(epicNew.getDescription());
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteSubtasks() {
        for (Integer key : epics.keySet()) {
            epics.get(key).clearSubtaskIds();
            checkEpicStatus(epics.get(key));
        }
        subtasks.clear();
    }

    public void deleteEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public void deleteSubtask(int subtaskId) {
        int keiFor = 0;
        for (Integer Key : epics.get(subtasks.get(subtaskId).getEpicId() - 1).getSubtaskIds()) {
            if (Key == subtaskId) {
                epics.get(subtasks.get(subtaskId).getEpicId() - 1).getSubtaskIds().remove(keiFor);
                checkEpicStatus(epics.get(subtasks.get(subtaskId).getEpicId() - 1));
                break;
            }
            keiFor++;
        }

        subtasks.remove(subtaskId);
    }

    public void deleteEpic(int epicId) {
        for (Integer Key : epics.get(epicId).getSubtaskIds()) {
            subtasks.remove(Key);
        }
        epics.remove(epicId);
    }

    private void checkEpicStatus(Epic epic) {
        boolean isNew = false;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            if (subtasks.get(epic.getSubtaskIds().get(i)).getStatus().equals("NEW")) {
                isNew = true;
            } else {
                isNew = false;
                break;
            }
        }

        boolean isDone = false;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            if (subtasks.get(epic.getSubtaskIds().get(i)).getStatus().equals("DONE")) {
                isDone = true;
            } else {
                isDone = false;
                break;
            }
        }
        if (isNew || epic.getSubtaskIds().isEmpty()) {
            epic.setStatus("NEW");
        } else if (isDone) {
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", subTasks=" + subtasks +
                ", epics=" + epics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskManager that = (TaskManager) o;
        return Objects.equals(tasks, that.tasks)
                && Objects.equals(subtasks, that.subtasks)
                && Objects.equals(epics, that.epics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, subtasks, epics);
    }
}
