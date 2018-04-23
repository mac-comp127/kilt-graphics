package kluver.examples.TreeDrawing;

import activityStarterCode.treePractice.BinarySearchTree;
import comp124graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunTreeDraw {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = generateRandomBST(20);

        // draw the tree
        TreeDraw td = new TreeDraw(bst);
        System.out.println(td.getBounds());

        // create a window sized the the drawing.
        CanvasWindow cw = new CanvasWindow("Test", (int)td.getWidth(), (int)td.getHeight());
        cw.add(td);


    }

    private static BinarySearchTree<Integer> generateRandomBST(int size) {
        // generate a list with numbers 1, 2, 3, 4, ... size
        List<Integer> numbers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
        // use a static method to shuffle these numbers.
        Collections.shuffle(numbers);

        // add the numbers in random order, creating a random BST.
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i : numbers) {
            bst.add(i);
        }
        return bst;
    }
}
