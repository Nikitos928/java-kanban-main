package manager;
import task.Task;

import java.util.*;

public interface HistoryManager {

    public void add(Task task);
    public List<Task> getHistory();
    void remove(int id);
}
