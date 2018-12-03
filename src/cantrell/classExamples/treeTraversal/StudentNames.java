package cantrell.classExamples.treeTraversal;

import java.util.ArrayList;
import java.util.List;

public class StudentNames {
    public static void main(String[] args) {
        BinaryTree<String> students = new BinaryTree<>("Haruhiko", null, null);
        students.add("Blake");
        students.add("Analeidi");
        students.add("Zhiyu");
        students.add("Lu");
        students.add("Chelsea");
        students.add("Sivhuo");
        students.add("Madison");
        students.add("Katherine");
        students.add("Ellen");
        students.add("Bao");
        students.add("Yiming");
        students.add("Duane");
        students.add("Lily");
        students.add("Ryan");
        students.add("Nana");

        // Using processInOrder() in handle the list in different ways:

        students.processInOrder(new Printer());

        System.out.println("-----> Making rainbow roster");
        students.processInOrder(new RainbowTextWindow("Students!!"));

        System.out.println("-----> Making list");
        List<String> nameList = new ArrayList<>();
        students.processInOrder(new ListProcessor<String>(nameList));
        System.out.println(nameList);
    }
}
