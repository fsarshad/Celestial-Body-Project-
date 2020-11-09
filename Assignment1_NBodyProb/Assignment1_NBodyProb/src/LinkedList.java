// LinkedList is backed by a linked list of Node objects
public class LinkedList<T> implements List<T> {
    private Node<T> head;

    public LinkedList() {
        head = new Node<T>(null);
    }

    // Sets the tail's next node to a new node with the provided value
    @Override
    public void add(T obj) {
        if (head.getValue() == null) {
            head.setValue(obj);
        } else {
            Node<T> tail = head;

            while (tail.getNext() != null) {
                tail = tail.getNext();
            }

            tail.setNext(new Node<T>(obj));
        }
    }

    // Goes the the node at pos and sets that node's next node to a new node with value obj.
    // Then set that new node's next node to the oldNext.
    @Override
    public void add(T obj, int pos) {
        if (pos == 0) {
            Node<T> oldHead = head;
            head = new Node<T>(obj);
            head.setNext(oldHead);
        } else {
            int i = 0;
            Node<T> node = head;

            while (node.getNext() != null && pos-1 > i) {
                node = node.getNext();
                i++;
            }

            Node<T> newNode = new Node<T>(obj);
            Node<T> oldNext = node.getNext();
            node.setNext(newNode);
            newNode.setNext(oldNext);
        }
    }

    // Goes through the links one at a time, pos times, and returns the value at that node
    // (or null if there's not enough nodes)
    @Override
    public T get(int pos) {
        Node<T> node = head;

        for (int i = 0; i < pos; i++) {
            Node<T> next = node.getNext();

            if (next == null) {
                return null;
            }

            node = next;
        }

        return node.getValue();
    }

    // Goes through the links one at a time, pos times, and sets the previous node's
    // next node to the node 2 nodes down. skips the current node.
    @Override
    public T remove(int pos) {
        if (pos == 0) {
            T oldHeadValue = head.getValue();
            head = head.getNext();
            return oldHeadValue;
        } else {
            Node<T> prev = null;
            Node<T> node = head;

            for (int i = 0; i < pos; i++) {
                Node<T> next = node.getNext();

                if (next == null) {
                    return null;
                }

                prev = node;
                node = next;
            }

            T v = node.getValue();
            prev.setNext(node.getNext());
            return v;
        }
    }

    // This method is size

    public int size() {
        if (head.getValue() == null) {
            return 0;
        }

        int size = 0;
        Node<T> node = head;
        while (node != null) {
            node = node.getNext();
            size++;
        }

        return size;
    }


    // LinkedList is changed into a string representation
    public String toString() {
        StringBuilder s =  new StringBuilder("[");

        Node<T> node = head;
        while (node != null) {
            s.append(node.getValue());
            node = node.getNext();

            if (node != null) {
                s.append(", ");
            }
        }

        s.append("]");
        return s.toString();
    }


    // Node class with Node, setNext, GetNext, GetValue, SetValue
    class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
