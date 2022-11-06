package manager;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    Node<Task> head;
    Node<Task> tail;
    int size = 0;

    public void addLast(Task element) {
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, element, null);
        tail = newNode;
        if (l == null) {
            head = newNode;

        } else {
            l.next = newNode;
        }
        size++;
    }

    public List<Task> getTasks() {
        List<Task> y = new ArrayList<>();
        Node<Task> target = head.next;
        y.add(head.data);
        for (int i = 0; i < tasks.size()-1; i++) {
                y.add(0, target.data);
                target = target.next;


        }
        return y;
    }


    private Map<Integer, Node<Task>> tasks = new HashMap<>();

    @Override
    public void add(Task task) {
        if (tasks.containsKey(task.getId())) {
           removeNode(tasks.get(task.getId()));
           tasks.remove(task.getId());
           tasks.put(task.getId(), tail);
           addLast(task);

        } else {
            addLast(task);
            tasks.put(task.getId(), tail);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InMemoryHistoryManager that = (InMemoryHistoryManager) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tasks);
    }

    private void removeNode(Node<Task> node) {
        //node.prev.next = node.next;
        //node.next.prev = node.prev;
       // node.prev = tail;
        //node.next = null;



        if (node == null) {
            return;
        }
        if (node.prev == null) {
            node.next.prev = null;
            head = node.next;
        }
        if (node.next == null) {
            node.prev.next = null;
            tail = node.prev;
        }
        if (node.next != null && node.prev != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private class Node<T> {
        protected T data;
        protected Node<T> next;
        protected Node<T> prev;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }
}
