package manager;

import task.Task;

import java.util.*;


public class InMemoryHistoryManager implements HistoryManager {


    private Map<Integer, ArrayList<Node<Task>>> tasks = new LinkedHashMap<>();

    CustomLinkedList <Task> customLinkedList = new CustomLinkedList<>();


    public void add(Task task) {
        customLinkedList.addLast(task);
    }
    @Override
    public List<Task> getHistory() {

        System.out.println(customLinkedList.head.data);
        System.out.println(customLinkedList.tail.data);
        return null;
    }
    public class CustomLinkedList <T> {
        private Node<T> head;
        private Node<T> tail;
        private int size = 0;

        public void addLast(T element) {
            // Реализуйте метод
            final Node<T> l = tail;
            final Node<T> newNode = new Node<>(element);
            tail = newNode;
            if (l == null)
                head = newNode;
            else
                l.next = newNode;
            size++;
        }
        public List <T> getTasks(Node node) {
            List <T> t = new ArrayList<>();
        while (node != null){

            node = node.next;
        }

            return t;
        }
    }

    private class Node <T> {
        protected T data;
        protected Node<T> next;
        protected Node<T> prev;

        private Node (T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<T> node = (Node<T>) o;
            return Objects.equals(data, node.data) && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next, prev);
        }
    }


    public void remove(int id) {
        tasks.remove(id);
    }




    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryHistoryManager that = (InMemoryHistoryManager) o;
        return Objects.equals(tasks, that.tasks);
    }


}
