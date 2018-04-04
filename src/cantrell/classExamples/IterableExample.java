package cantrell.classExamples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IterableExample {
    /**
     * Takes two already sorted lists of Strings as input, and returns a new list that contains all the
     * strings from both lists, in sorted order.
     */
    public static List<String> mergeSorted(Iterable<String> list0, Iterable<String> list1) {
        List<String> result = new ArrayList<>();
        PeekingIterator<String>
            iter0 = new PeekingIterator<>(list0.iterator()),
            iter1 = new PeekingIterator<>(list1.iterator());

        while(iter0.hasNext() && iter1.hasNext()) {
            String
                item0 = iter0.peek(),
                item1 = iter1.peek();

            if(item0.compareTo(item1) < 0) {
                result.add(item0);
                iter0.next();
            } else {
                result.add(item1);
                iter1.next();
            }
        }

        while(iter0.hasNext()) {
            result.add(iter0.next());
        }
        while(iter1.hasNext()) {
            result.add(iter1.next());
        }

        return result;
    }
}

class PeekingIterator<T> implements Iterator<T> {

    private final Iterator<T> iter;
    private T next;

    PeekingIterator(Iterator<T> iter) {
        this.iter = iter;
        advanceIter();
    }

    public T peek() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public T next() {
        T result = next;

        advanceIter();

        return result;
    }

    private void advanceIter() {
        if(iter.hasNext()) {
            next = iter.next();
        } else {
            next = null;
        }
    }
}


















