package cantrell.classExamples.listImplementations;

public interface SimpleList<E> {

    void add(E element);

    E get(int index);

    int size();

    SimpleIterator<E> iterator();
}
