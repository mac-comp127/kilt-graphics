package cantrell.classExamples.treeTraversal;

public class Printer implements Processor<String> {
    @Override
    public void process(String value) {
        System.out.println(value);
    }
}
