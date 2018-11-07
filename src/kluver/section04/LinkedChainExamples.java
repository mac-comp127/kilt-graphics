package kluver.section04;

import javaFoundations4thEd.ch14.jsjf.LinearNode;

public class LinkedChainExamples {
    public static LinearNode<Integer> makeChain() {
        LinearNode<Integer> l1 = new LinearNode<>(1);
        LinearNode<Integer> l2 = new LinearNode<>(2);
        LinearNode<Integer> l3 = new LinearNode<>(3);
        LinearNode<Integer> l4 = new LinearNode<>(4);
        LinearNode<Integer> l5 = new LinearNode<>(5);
        LinearNode<Integer> l6 = new LinearNode<>(6);
        LinearNode<Integer> l7 = new LinearNode<>(7);
        LinearNode<Integer> l8 = new LinearNode<>(8);
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(l6);
        l6.setNext(l7);
        l7.setNext(l8);
        return l1;
    }

    public static int countChain(LinearNode<Integer> chain) {
        // approach: count how many times we have to call getNext()
        // before we hit null.

        int count = 0;
        while(chain != null) {
            chain = chain.getNext();
            count = count + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countChain(makeChain()));
    }
}
