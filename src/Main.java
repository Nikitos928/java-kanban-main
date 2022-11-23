
import manager.FileBackedTasksManager;
import manager.InMemoryTasksManager;
import task.Status;
import task.Task;
import task.Epic;
import task.Subtask;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();


        InMemoryTasksManager taskManager = new InMemoryTasksManager();
        /*Task task1 = new Task("название Такс 1", "Цель1", Status.NEW); // id 0
        Task task2 = new Task("название Таск 2", "Цель2", Status.NEW);// id 1

        taskManager.addTask(task1);
        taskManager.addTask(task2);



        Epic epic1 = new Epic("название Эпика 1", "Цель Эпика 1"); // id 2
        Epic epic2 = new Epic("название Эпика 2", "Цель Эпика 2"); // id 3
        Epic epic3 = new Epic("название Эпика 3", "Цель Эпика 3"); // id 4
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);




        Subtask subtask1 = new Subtask("название Subtask 1", "Цель Subtask 1", Status.DONE, 2); // id 5
        Subtask subtask2 = new Subtask("название Subtask 2", "Цель Subtask 2", Status.NEW, 2); // id 6
        Subtask subtask3 = new Subtask("название Subtask 3", "Цель Subtask 3", Status.NEW, 3); // id 7
        Subtask subtask4 = new Subtask("название Subtask 4", "Цель Subtask 4", Status.NEW, 3); // id 8
        Subtask subtask5 = new Subtask("название Subtask 5", "Цель Subtask 5", Status.NEW, 4); // id 9
        Subtask subtask6 = new Subtask("название Subtask 6", "Цель Subtask 6", Status.NEW, 4); // id 10
        Subtask subtask7 = new Subtask("название Subtask 7", "Цель Subtask 7", Status.NEW, 4);


        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);
        taskManager.addSubtask(subtask5);
        taskManager.addSubtask(subtask6);

        Task task3 = new Task("название Такс 3", "Цель3", Status.NEW); // id 0
        Task task4 = new Task("название Таск 4", "Цель4", Status.NEW);// id 1

        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);

        Epic epic4 = new Epic("название Эпика 4", "Цель Эпика 1");
        Epic epic5 = new Epic("название Эпика 5", "Цель Эпика 2");
        Epic epic6 = new Epic("название Эпика 6", "Цель Эпика 3");

        fileBackedTasksManager.addEpic(epic4);
        fileBackedTasksManager.addEpic(epic5);
        fileBackedTasksManager.addEpic(epic6);

       fileBackedTasksManager.addSubtask(subtask1);
        fileBackedTasksManager.addSubtask(subtask2);
       fileBackedTasksManager.addSubtask(subtask3);
        fileBackedTasksManager.addSubtask(subtask4);
       fileBackedTasksManager.addSubtask(subtask5);
*/


        /*for (Task task : taskManager.getSubtasks()){
            System.out.println(task);
        }
        for (Task task : taskManager.getTasks()){
            System.out.println(task);
        }
        for (Task task : taskManager.getEpics()){
            System.out.println(task);
        }*/


    }
}
