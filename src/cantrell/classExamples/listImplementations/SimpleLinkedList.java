package cantrell.classExamples.listImplementations;


public class SimpleLinkedList<E> implements SimpleList<E> {
    private Node<E> head, tail;
    private int size;

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setPrev(tail);
        if(tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;

        size++;
    }

    @Override
    public E get(int index) {
        Node<E> curNode = head;
        for(; index > 0; index--) {
            curNode = curNode.getNext();
        }
        return curNode.getElement();
    }

    @Override
    public int size() {
        return size;
    }

    public SimpleIterator<E> iterator() {
        return new SimpleLinkedListIterator<>(head);
    }

    private static class Node<E> {
        private E element;
        private Node<E> prev, next;

        public Node(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private static class SimpleLinkedListIterator<E> implements SimpleIterator<E> {
        private Node<E> node;

        public SimpleLinkedListIterator(Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E element = node.getElement();
            node = node.getNext();
            return element;
        }
    }
}


