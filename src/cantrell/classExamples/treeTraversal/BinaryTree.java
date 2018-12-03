package cantrell.classExamples.treeTraversal;

import java.util.List;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

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

    public void add(T newValue) {
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
            subtree.add(newValue);
        }
        return subtree;
    }

    public void processInOrder(Processor<T> processor) {
        if(leftSubtree != null) {
            leftSubtree.processInOrder(processor);
        }

        processor.process(value);

        if(rightSubtree != null) {
            rightSubtree.processInOrder(processor);
        }
    }

    public boolean contains(T searchValue) {
        BinaryTree<T> curNode = this;

        while(curNode != null) {
            if(searchValue.equals(curNode.value)) {
                return true;
            }

            if(searchValue.compareTo(curNode.value) > 0) {
                curNode = curNode.rightSubtree;
            } else {
                curNode = curNode.leftSubtree;
            }
        }
        return false;
    }

    /**
     * Alternative approach:
     */
    public boolean containsRecursive(T searchValue) {
        if(searchValue.equals(value)) {
            return true;
        }

        if(searchValue.compareTo(value) > 0) {
            if(rightSubtree == null) {
                return false;
            }
            return rightSubtree.containsRecursive(searchValue);
        } else {
            if(leftSubtree == null) {
                return false;
            }
            return leftSubtree.containsRecursive(searchValue);
        }
    }

}


