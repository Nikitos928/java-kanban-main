
import manager.FileBackedTaskManager;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import task.Status;
import task.Task;
import task.Epic;
import task.Subtask;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static manager.FileBackedTaskManager.loadFromFile;

public class Main {

    public static void main(String[] args) throws IOException {
        /*FileBackedTaskManager fileBackedTasksManager = new FileBackedTaskManager(new File("Tasks.csv"));



        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("название Такс 1", "Цель1", Status.NEW); // id 0
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

        taskManager.getSubtask(5);
        taskManager.getSubtask(6);
        taskManager.getSubtask(7);
        taskManager.getSubtask(8);
        taskManager.getSubtask(9);

        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);
        fileBackedTasksManager.addTask(task4);



        Epic epic4 = new Epic("название Эпика 4", "Цель Эпика 1");
        Epic epic5 = new Epic("название Эпика 5", "Цель Эпика 2");
        Epic epic6 = new Epic("название Эпика 6", "Цель Эпика 3");


        fileBackedTasksManager.addEpic(epic4);
        fileBackedTasksManager.addEpic(epic5);

        Subtask subtask8 = new Subtask("название Subtask 6", "Цель Subtask 6", Status.NEW, 3); // id 10
        Subtask subtask9 = new Subtask("название Subtask 7", "Цель Subtask 7", Status.NEW, 3);
        Subtask subtask10 = new Subtask("название Subtask 6", "Цель Subtask 6", Status.NEW, 4); // id 10
        Subtask subtask11 = new Subtask("название Subtask 7", "Цель Subtask 7", Status.NEW, 4);

        fileBackedTasksManager.addSubtask(subtask8);
        fileBackedTasksManager.addSubtask(subtask9);
        fileBackedTasksManager.addSubtask(subtask10);
        fileBackedTasksManager.addSubtask(subtask11);

        fileBackedTasksManager.getTask(0);
        fileBackedTasksManager.getTask(1);
        fileBackedTasksManager.getTask(2);
        fileBackedTasksManager.getEpic(3);
        fileBackedTasksManager.getEpic(4);

        fileBackedTasksManager.getSubtask(5);
        fileBackedTasksManager.getSubtask(6);
        fileBackedTasksManager.getSubtask(7);
*/
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("название Такс 1", "Цель1", Status.NEW,
                LocalDateTime.of(2021, 9, 1, 9, 10, 0), 90); // id 0
        Task task2 = new Task("название Таск 2", "Цель2", Status.NEW,
                LocalDateTime.of(2021, 10, 1, 9, 10, 0), 90);// id 1

        taskManager.addTask(task1);
        taskManager.addTask(task2);


        Epic epic1 = new Epic("название Эпика 1", "Цель Эпика 1"); // id 2
        Epic epic2 = new Epic("название Эпика 2", "Цель Эпика 2"); // id 3
        Epic epic3 = new Epic("название Эпика 3", "Цель Эпика 3"); // id 4
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);


        Subtask subtask1 = new Subtask("название Subtask 1", "Цель Subtask 1", Status.DONE, 2 ,
                LocalDateTime.of(2021, 12, 1, 9, 10, 0), 90); // id 5
        Subtask subtask2 = new Subtask("название Subtask 2", "Цель Subtask 2", Status.NEW, 3,
                LocalDateTime.of(2021, 11, 1, 9, 10, 0), 90); // id 6
        Subtask subtask3 = new Subtask("название Subtask 3", "Цель Subtask 3", Status.NEW, 4,
                LocalDateTime.of(2021, 11, 1, 9, 10, 0), 90); // id 7

        Subtask subtask4 = new Subtask("название Subtask 3", "Цель Subtask 3", Status.NEW, 4,
                LocalDateTime.of(2021, 1, 1, 9, 10, 0), 90); // id 7

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);

        for (Task task : taskManager.getPrioritizedTasks()) {
            System.out.println(task.getDate());
        }
        taskManager.taskTracker();

    }
}
