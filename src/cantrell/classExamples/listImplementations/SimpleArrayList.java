package cantrell.classExamples.listImplementations;

import java.util.Arrays;


public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] array = (E[]) new Object[10];
    private int size = 0;

    @Override
    public void add(E element) {
        if(size == array.length) {
            array = Arrays.copyOf(array, array.length * 3 / 2);
        }
        array[size] = element;
        size++;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleIterator<E> iterator() {
        return new SimpleArrayListIterator(0);
    }

    private class SimpleArrayListIterator implements SimpleIterator<E> {
        private int position;

        public SimpleArrayListIterator(int initialPosition) {
            position = initialPosition;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public E next() {
            position++;
            return array[position - 1];
        }
    }
}
