package task;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private Status status;
    private int id;
    private LocalDateTime date;
    private int timePerTask;

    private LocalDateTime completionTime;




    public Task(String name, String description, Status status, LocalDateTime date, int timePerTask) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.date = date;
        this.timePerTask = timePerTask;
    }

    public Task(String name, String description, Status status, LocalDateTime date, int timePerTask, LocalDateTime completionTime) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.date = date;
        this.timePerTask = timePerTask;
        this.completionTime = completionTime;
    }
    public Task(String name, String description, Status status, LocalDateTime completionTime) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.completionTime = completionTime;
    }
    public Task(String name, String description, Status status, int id, LocalDateTime date, int timePerTask) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
        this.date = date;
        this.timePerTask = timePerTask;
    }
    public Task(String name, String description, Status status, int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;

    }
    public Task(int id, String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Task(String name, String description, Status aNew) {
        this.name = name;
        this.description = description;

    }

    public Task() {
    }

    public Task(String name, String description, Status status, int id, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTimePerTask() {
        return timePerTask;
    }

    public void setTimePerTask(int timePerTask) {
        this.timePerTask = timePerTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", timePerTask=" + timePerTask +
                ", completionTime=" + completionTime +
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
        Task task = (Task) o;
        return id == task.id
                && Objects.equals(name, task.name)
                && Objects.equals(description, task.description)
                && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, id);
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }
}









