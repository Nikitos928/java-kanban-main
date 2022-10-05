import task.Task;
import task.Epic;
import task.Subtask;
import manager.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("название1", "Цель1", "NEW");
        Task task2 = new Task("название2", "Цель2", "NEW");
        final int taskId1 = taskManager.addTask(task1);
        final int taskId2 = taskManager.addTask(task2);



        Epic epic1 = new Epic("название Эпика 1", "Цель Эпика 1");
        Epic epic2 = new Epic("название Эпика 2", "Цель Эпика 2");
        Epic epic3 = new Epic("название Эпика 3", "Цель Эпика 3");
        final int epicId1 = taskManager.addEpic(epic1);
        final int epicId2 = taskManager.addEpic(epic2);
        final int epicId3 = taskManager.addEpic(epic3);



        Subtask subtask1 = new Subtask("название Subtask 1", "Цель Subtask 1", "DONE", epicId1);
        Subtask subtask2 = new Subtask("название Subtask 2", "Цель Subtask 2", "NEW", epicId1);
        Subtask subtask3 = new Subtask("название Subtask 3", "Цель Subtask 3", "NEW", epicId2);
        Subtask subtask4 = new Subtask("название Subtask 4", "Цель Subtask 4", "NEW", epicId2);
        Subtask subtask5 = new Subtask("название Subtask 5", "Цель Subtask 5", "NEW", epicId3);
        Subtask subtask6 = new Subtask("название Subtask 6", "Цель Subtask 6", "NEW", epicId3);

        final Integer subtaskId1 = taskManager.addSubTask(subtask1);
        final Integer subtaskId2 = taskManager.addSubTask(subtask2);
        final Integer subtaskId3 = taskManager.addSubTask(subtask3);
        final Integer subtaskId4 = taskManager.addSubTask(subtask4);
        final Integer subtaskId5 = taskManager.addSubTask(subtask5);
        final Integer subtaskId6 = taskManager.addSubTask(subtask6);
        System.out.println(taskManager.getEpics());






    }
}
