package cantrell.classExamples.treeTraversal;

import java.util.ArrayList;

class BinaryTree<T extends Comparable<T>> {
    private T value;
    private BinaryTree<T> leftSubtree, rightSubtree;

    public BinaryTree(T value, BinaryTree<T> leftSubtree, BinaryTree<T> rightSubtree) {
        this.value = value;
        this.leftSubtree = leftSubtree;
        this.rightSubtree = rightSubtree;
    }

    public T getValue() {
        return value;
    }

    public BinaryTree<T> getLeftSubtree() {
        return leftSubtree;
    }

    public BinaryTree<T> getRightSubtree() {
        return rightSubtree;
    }

    public void insertValue(T newValue) {
        if(newValue.compareTo(value) > 0) {
            rightSubtree = insertValueInSubtree(newValue, rightSubtree);
        } else {
            leftSubtree = insertValueInSubtree(newValue, leftSubtree);
        }
    }

    private BinaryTree<T> insertValueInSubtree(T newValue, BinaryTree<T> subtree) {
        if(subtree == null) {
            subtree = new BinaryTree<>(newValue, null, null);
        } else {
            subtree.insertValue(newValue);
        }
        return subtree;
    }

    public void traverseInOrder() {
        if(leftSubtree != null) {
            leftSubtree.traverseInOrder();
        }

        System.out.println(value);

        if(rightSubtree != null) {
            rightSubtree.traverseInOrder();
        }
    }
}


