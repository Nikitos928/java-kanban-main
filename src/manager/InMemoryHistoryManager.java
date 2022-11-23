package manager;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;
    private final Map<Integer, Node<Task>> tasks = new HashMap<>();

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
        int x = 0;
        List<Task> y = new ArrayList<>();
        if (head == null) {
            return y;
        }
        if (head.next == null) {
            return y;
        }
        Node<Task> target = head.next;

        if (x == 0) {
            y.add(head.data);
            x++;
        }
        for (int i = 0; i < tasks.size() - 1; i++) {
            y.add(target.data);
            target = target.next;
        }
        return y;
    }

    @Override
    public void add(Task task) {
        if (tasks.containsKey(task.getId())) {
            if (size == 1) {
                return;
            } else {
                removeNode(tasks.get(task.getId()));
                tasks.remove(task.getId());
                addLast(task);
                tasks.put(task.getId(), tail);
            }
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
        removeNode(tasks.get(id));
        tasks.remove(id);
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
        return Objects.hash(getHistory());
    }

    private void removeNode(Node<Task> node) {
        if (node == null) {
            return;
        }
        if (node.next != null) {
            if (node.prev == null) {
            node.next.prev = null;
            head = node.next;
        }
    }
        if (node.prev != null) {
            if (node.next == null) {
                node.prev.next = null;
                tail = node.prev;
            }
        }
        if (node.next != null && node.prev != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data)
                    && Objects.equals(next, node.next)
                    && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next, prev);
        }
    }
}
