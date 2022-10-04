package task;
import java.util.ArrayList;
import java.util.Objects;
public class Epic extends Task {
    protected ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
    }

    public ArrayList<Integer> getIdSubTask() {
        return subtaskIds;
    }

    public void addSubtasksId(int id){
        subtaskIds.add(id);
    }

    public void removeSubtask(int id) {
        subtaskIds.remove(Integer.valueOf(id));
    }

    public void clearIdSubTask() {
        subtaskIds.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Epic epic = (Epic) o;
        Task task = (Task) o;
        return getId() == task.getId()
                && Objects.equals(getName(), task.getName())
                && Objects.equals(getDescription(), task.getDescription())
                && Objects.equals(getStatus(), task.getStatus())
                && Objects.equals(subtaskIds, epic. subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getStatus(), getId(),  subtaskIds);


    }

    public String toString() {
        return "Task{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", id=" + getId() +
                ", epicId=" +  subtaskIds +
                '}';
    }
}


