package cantrell.classExamples.treeTraversal;

import java.util.List;

public class ListProcessor<T> implements Processor<T> {
    private List<T> list;

    public ListProcessor(List<T> list) {
        this.list = list;
    }

    @Override
    public void process(T value) {
        list.add(value);
    }
}
