package manager;

import task.Status;
import task.Task;
import task.Epic;
import task.Subtask;

import java.time.LocalDateTime;
import java.util.*;


public class InMemoryTaskManager implements TaskManager {

    private int generatedId;

    private final Map<Integer, Task> tasks = new HashMap<>();

    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private final Map<Integer, Epic> epics = new HashMap<>();
    private final InMemoryHistoryManager history = new InMemoryHistoryManager();
    private Map<Integer, Task> tasks1;

    public HistoryManager historyReturn(){
        return history;
    }
    public void addSubtaskIn (Subtask subtask){
        subtask.setId(generatedId);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtaskId(generatedId);
        generatedId += 1;
    }

    public List <Task> getPrioritizedTasks (){
        List <Task> sortTasks = new ArrayList<>(tasks.values());
        sortTasks.addAll(subtasks.values());
        sortTasks.sort(Comparator.comparing(Task::getDate));
        return sortTasks;
    }

    public void taskTracker (){
        LocalDateTime localDateTime = LocalDateTime.now();
       for(Task task :getPrioritizedTasks()){
           if (!localDateTime.isBefore(task.getCompletionTime())){
               System.out.println("2 Задачи не могут выполняться одновременно");
           }
           localDateTime = task.getCompletionTime();
       }
    }

    @Override
    public void addTask(Task task) {
        task.setCompletionTime(getEndTime(task.getTimePerTask(), task.getDate()));
        task.setId(generatedId);
        tasks.put(task.getId(), task);
        generatedId += 1;
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setStatus(Status.NEW);
        epic.setId(generatedId);
        epics.put(epic.getId(), epic);
        generatedId += 1;

    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setCompletionTime(getEndTime(subtask.getTimePerTask(), subtask.getDate()));
        subtask.setId(generatedId);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtaskId(generatedId);
        updateEpicStatus(epics.get(subtask.getEpicId()));
        epics.get(subtask.getEpicId()).setDate(getEndTimeForEpic(subtask));
        epics.get(subtask.getEpicId()).setTimePerTask(epics.get(subtask.getEpicId()).getTimePerTask() + subtask.getTimePerTask());
        epics.get(subtask.getEpicId()).setCompletionTime(getEndTimeCompletionTimeForEpic(subtask));
        generatedId += 1;
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (int i = 0; i < epics.get(epicId).getSubtaskIds().size(); i++) {
            arrSubtasks.add(subtasks.get(epics.get(epicId).getSubtaskIds().get(i)));
        }
        return arrSubtasks;
    }

    @Override
    public Task getTask(int taskId) {
        history.add(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public Epic getEpic(int epicId) {
        history.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Subtask getSubtask(int subtaskId) {

        history.add(subtasks.get(subtaskId));
        return subtasks.get(subtaskId);
    }

    @Override
    public ArrayList<Task> getTasks() {
        ArrayList<Task> arrTasks = new ArrayList<>();
        for (Integer key : tasks.keySet()) {
            arrTasks.add(tasks.get(key));
        }

        return arrTasks;
    }

    @Override
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> arrEpics = new ArrayList<>();
        for (Integer key : epics.keySet()) {
            arrEpics.add(epics.get(key));
        }
        return arrEpics;
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> arrSubtasks = new ArrayList<>();
        for (Integer key : subtasks.keySet()) {
            arrSubtasks.add(subtasks.get(key));
        }
        return arrSubtasks;
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.get(epic.getId()).setName(epic.getName());
        epics.get(epic.getId()).setDescription(epic.getDescription());
    }

    @Override
    public void deleteTasks() {
        for (Integer integer : tasks.keySet()) {
            history.remove(integer);
        }
        tasks.clear();
    }

    @Override
    public void deleteSubtasks() {
        for (Integer key : epics.keySet()) {
            epics.get(key).clearSubtaskIds();
            updateEpicStatus(epics.get(key));
        }
        for (Integer integer : subtasks.keySet()) {
            history.remove(integer);
        }
        subtasks.clear();
    }

    @Override
    public void deleteEpics() {
        for (Integer integer : subtasks.keySet()) {
            history.remove(integer);
        }
        subtasks.clear();
        for (Integer integer : epics.keySet()) {
            history.remove(integer);
        }
        epics.clear();
    }

    @Override
    public void deleteTask(int taskId) {
        tasks.remove(taskId);
        history.remove(taskId);
    }

    @Override
    public void deleteSubtask(int subtaskId) {
        epics.get(subtasks.get(subtaskId).getEpicId()).removeSubtaskId(subtaskId);
        updateEpicStatus(epics.get(subtasks.get(subtaskId).getEpicId()));
        subtasks.remove(subtaskId);
        history.remove(subtaskId);
    }

    @Override
    public void deleteEpic(int epicId) {
        for (Integer key : epics.get(epicId).getSubtaskIds()) {
            subtasks.remove(key);
            history.remove(key);
        }
        epics.remove(epicId);
        history.remove(epicId);
    }

    private void updateEpicStatus(Epic epic) {
        boolean isNew = false;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            if (subtasks.get(epic.getSubtaskIds().get(i)).getStatus().equals(Status.NEW)) {
                isNew = true;
            } else {
                isNew = false;
                break;
            }
        }

        boolean isDone = false;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            if (subtasks.get(epic.getSubtaskIds().get(i)).getStatus().equals(Status.DONE)) {
                isDone = true;
            } else {
                isDone = false;
                break;
            }
        }
        if (isNew || epic.getSubtaskIds().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else if (isDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }

    public LocalDateTime getEndTime(int time, LocalDateTime date){
        return date.plusMinutes(time);
    }

    public LocalDateTime getEndTimeForEpic (Subtask subtask){
        LocalDateTime dateEpic = subtask.getDate();
        for (Integer subtaskEpic : epics.get(subtask.getEpicId()).getSubtaskIds()) {
            if (subtasks.get(subtaskEpic).getDate().isBefore(subtask.getDate())){
                dateEpic = subtasks.get(subtaskEpic).getDate();
            }
        }
        return dateEpic;
    }

    public LocalDateTime getEndTimeCompletionTimeForEpic (Subtask subtask){
        LocalDateTime dateEpic = subtask.getCompletionTime();
        for (Integer subtaskEpic : epics.get(subtask.getEpicId()).getSubtaskIds()) {
            if (subtasks.get(subtaskEpic).getCompletionTime().isAfter(subtask.getCompletionTime())){
                dateEpic = subtasks.get(subtaskEpic).getCompletionTime();
            }
        }
        return dateEpic;
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
    public int hashCode() {
        return Objects.hash(tasks, subtasks, epics, history.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InMemoryTaskManager that = (InMemoryTaskManager) o;
        return generatedId == that.generatedId
                && tasks.equals(that.tasks)
                && subtasks.equals(that.subtasks)
                && epics.equals(that.epics)
                && history.equals(that.history);
    }


}
