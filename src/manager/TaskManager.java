package manager;
import task.Task;
import task.Epic;
import task.Subtask;
import java.util.*;


public class TaskManager {
    private int generatedId;

    private final HashMap<Integer, Task> tasks = new HashMap<>();

    private final HashMap<Integer, Subtask> subTasks = new HashMap<>();

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

    public int addSubTask(Subtask subtask) {
        subtask.setId(generatedId);
        subTasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()-1).addSubtasksId(generatedId);
        checkEpicStatus(epics.get(subtask.getEpicId()-1));
        generatedId += 1;
        return generatedId;
    }



    public void getEpicSubtasks(Epic epic) {
        for (int i = 0; i < epic.getIdSubTask().size(); i++) {
            System.out.println(subTasks.get(epic.getIdSubTask().get(i)));
        }
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Task getEpic(int epicId) {
        return epics.get(epicId);
    }

    public Task getSubTask(int subtaskId) {
        return subTasks.get(subtaskId);
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }
    public HashMap<Integer, Subtask> getSubTasks() {
        return subTasks;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);

    }

    public void updateSubTask(Subtask subtask) {
        subTasks.put(subtask.getId(), subtask);
        checkEpicStatus(epics.get(subtask.getEpicId()));
    }

    public void updateTask(Epic epic) {
        epics.put(epic.getId(), epic);
        checkEpicStatus(epic);

    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteSubtasks() {
        subTasks.clear();

    }

    public void deleteEpics() {
        epics.clear();
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public void deleteSubtasks(int subtaskId) {
        subTasks.remove(subtaskId);
    }

    public void DeleteEpic(int epicId) {
        epics.remove(epicId);
    }

    private void checkEpicStatus(Epic epic) {
        boolean isNew = false;
        for (int i = 0; i < epic.getIdSubTask().size(); i++) {
            if (subTasks.get(epic.getIdSubTask().get(i)).getStatus().equals("NEW")) {
                isNew = true;
            } else {
                isNew = false;
                break;
            }
        }

        boolean isDone = false;
        for (int i = 0; i < epic.getIdSubTask().size(); i++) {
            if (subTasks.get(epic.getIdSubTask().get(i)).getStatus().equals("DONE")) {
                isDone = true;
            } else {
                isDone = false;
                break;
            }
        }
        if (isNew || epic.getIdSubTask().isEmpty()) {

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
                ", subTasks=" + subTasks +
                ", epics=" + epics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;}

        TaskManager that = (TaskManager) o;
        return Objects.equals(tasks, that.tasks)
                && Objects.equals(subTasks, that.subTasks)
                && Objects.equals(epics, that.epics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, subTasks, epics);
    }
}
