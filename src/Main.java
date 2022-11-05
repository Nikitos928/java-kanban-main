import manager.InMemoryTaskManager;
import task.Status;
import task.Task;
import task.Epic;
import task.Subtask;

public class Main {

    public static void main(String[] args) {




        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("название Такс 1", "Цель1", Status.NEW); // id 0
        Task task2 = new Task("название Таск 2", "Цель2", Status.NEW);// id 1
        final int taskId1 = taskManager.addTask(task1);
        final int taskId2 = taskManager.addTask(task2);



        Epic epic1 = new Epic("название Эпика 1", "Цель Эпика 1"); // id 2
        Epic epic2 = new Epic("название Эпика 2", "Цель Эпика 2"); // id 3
        Epic epic3 = new Epic("название Эпика 3", "Цель Эпика 3"); // id 4
        final int epicId1 = taskManager.addEpic(epic1);
        final int epicId2 = taskManager.addEpic(epic2);
        final int epicId3 = taskManager.addEpic(epic3);




        Subtask subtask1 = new Subtask("название Subtask 1", "Цель Subtask 1", Status.DONE, 2); // id 5
        Subtask subtask2 = new Subtask("название Subtask 2", "Цель Subtask 2", Status.NEW, 2); // id 6
        Subtask subtask3 = new Subtask("название Subtask 3", "Цель Subtask 3", Status.NEW, 3); // id 7
        Subtask subtask4 = new Subtask("название Subtask 4", "Цель Subtask 4", Status.NEW, 3); // id 8
        Subtask subtask5 = new Subtask("название Subtask 5", "Цель Subtask 5", Status.NEW, 4); // id 9
        Subtask subtask6 = new Subtask("название Subtask 6", "Цель Subtask 6", Status.NEW, 4); // id 10


        final Integer subtaskId1 = taskManager.addSubtask(subtask1);
        final Integer subtaskId2 = taskManager.addSubtask(subtask2);
        final Integer subtaskId3 = taskManager.addSubtask(subtask3);
        final Integer subtaskId4 = taskManager.addSubtask(subtask4);
        final Integer subtaskId5 = taskManager.addSubtask(subtask5);
        final Integer subtaskId6 = taskManager.addSubtask(subtask6);

        Task task3 = new Task("название Таск 3", "Цель3", Status.NEW);// id 11
        taskManager.addTask(task3);

        taskManager.getTask(0);
        taskManager.getTask(1);
        taskManager.getTask(11);

        taskManager.getEpic(2);
        //taskManager.getEpic(3);
       //taskManager.getEpic(4);
        //taskManager.getEpic(2);
        //taskManager.getEpic(3);

        //taskManager.getTask(0);

        //taskManager.getSubtask(5);
        //taskManager.getSubtask(5);
        //taskManager.getSubtask(7);
        //taskManager.getSubtask(6);

        //taskManager.removeHistory(6);
        taskManager.getHistory();









    }
}
