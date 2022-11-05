package manager;

import task.Task;
import task.Epic;
import task.Subtask;

import java.util.*;


public interface TaskManager {

    public int addTask(Task task) ;

    public int addEpic(Epic epic) ;

    public int addSubtask(Subtask subtask);

    public ArrayList<Subtask> getEpicSubtasks(int epicId) ;

    public Task getTask(int taskId) ;

    public Epic getEpic(int epicId) ;

    public Subtask getSubtask(int subtaskId) ;

    public ArrayList<Task> getTasks() ;

    public ArrayList<Epic> getEpics();

    public ArrayList<Subtask> getSubtasks() ;

    public void updateTask(Task task) ;

    public void updateSubtask(Subtask subtask) ;

    public void updateEpic(Epic epic) ;

    public void deleteTasks() ;

    public void deleteSubtasks() ;
    public void deleteEpics() ;

    public void deleteTask(int taskId) ;

    public void deleteSubtask(int subtaskId) ;

    public void deleteEpic(int epicId) ;

    public List<Task> getHistory();


}
