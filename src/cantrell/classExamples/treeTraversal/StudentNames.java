package cantrell.classExamples.treeTraversal;

public class StudentNames {
    public static void main(String[] args) {
        BinaryTree<String> students = new BinaryTree<>(
            "Ellen",
            new BinaryTree<String>("Bao", null, null),
            new BinaryTree<String>("Yiming", null, null));

        students.insertValue("Lu");
        students.insertValue("Kate");
        students.insertValue("Madison");
        students.insertValue("Sivhuo");
        students.insertValue("Duane");
        students.insertValue("Haruhiko");

        students.traverseInOrder();
    }
}
