package kluver.examples.TreeDrawing;

import activityStarterCode.treePractice.BinaryTree;
import comp124graphics.Ellipse;
import comp124graphics.GraphicsGroup;
import comp124graphics.GraphicsText;
import comp124graphics.Line;

import java.awt.*;

public class TreeDraw extends GraphicsGroup {
    private static final int LABEL_WIDTH=40;
    private static final int LABEL_HEIGHT=40;
    private static final int HEIGHT_PADDING = 20;

    private int x = 0;

    private int maxDepth = 0;

    public <E> TreeDraw(BinaryTree<E> tree) {
        drawTree(tree, 0);


        /*
        // optional - draw lines to help show off how this works
        int height = (int) getHeight();
        int width = (int) getWidth();
        for(int lx = 0; lx<x; lx++) {
            Line line = new Line(LABEL_WIDTH/2+lx*LABEL_WIDTH, 0, LABEL_WIDTH/2+lx*LABEL_WIDTH, height);
            line.setStrokeColor(Color.gray);
            add(line);
        }

        for(int ly = 0; ly<=maxDepth; ly++) {
            Line line = new Line(0, LABEL_HEIGHT/2 + ly*(LABEL_HEIGHT+HEIGHT_PADDING), width,  LABEL_HEIGHT/2 + ly*(LABEL_HEIGHT+HEIGHT_PADDING));
            line.setStrokeColor(Color.gray);
            add(line);
        }*/
    }

    private <E> int drawTree(BinaryTree<E> tree, int depth) {
        maxDepth = Math.max(depth, maxDepth);
        if(tree != null) {
            /////// PART 1 - figure out the positions of this tree and it's left/ right subtree
            // left recursive call
            int leftX = drawTree(tree.getLeftSubtree(), depth + 1);

            // "visit" this node (find it's x position
            int centerX = x;
            x += 1;

            // right recursive call
            int rightX = drawTree(tree.getRightSubtree(), depth + 1);

            /////// PART 2 - have the sub-trees drawn
            // this is a side-effect of the earlier method calls.

            /////// PART 3 - figure out the positions of this tree and it's left/ right subtree
            // draw the left edge
            if(tree.getLeftSubtree() != null) {
                drawEdge(leftX, centerX, depth);
            }

            // draw the right edge
            if(tree.getRightSubtree() != null) {
                drawEdge(rightX, centerX, depth);
            }
            // draw the node
            drawNode(tree.getData().toString(), centerX, depth);
            return centerX;
        } else {
            return -1;
        }
    }

    private void drawNode(String s, int centerX, int depth) {
        int x = LABEL_WIDTH/2 + LABEL_WIDTH*centerX;
        int y = depth*(HEIGHT_PADDING+LABEL_HEIGHT) + LABEL_HEIGHT/2;
        Ellipse e = new Ellipse(x-LABEL_WIDTH/2,y-LABEL_HEIGHT/2, LABEL_WIDTH, LABEL_HEIGHT);
        e.setFilled(true);
        e.setFillColor(Color.white);
        add(e);

        GraphicsText gt = new GraphicsText(s, x, y);
        gt.move(-gt.getWidth()/2, gt.getHeight()/2);
        add(gt);
    }

    private void drawEdge(int childX, int centerX, int depth) {

        int x1 = LABEL_WIDTH/2 + LABEL_WIDTH*childX;
        int x2 = LABEL_WIDTH/2 + LABEL_WIDTH*centerX;
        int y1 = depth*(HEIGHT_PADDING+LABEL_HEIGHT) + HEIGHT_PADDING + LABEL_HEIGHT; // at depth+1
        int y2 = depth*(HEIGHT_PADDING+LABEL_HEIGHT) + LABEL_HEIGHT/2; // at depth
        Line l = new Line(x1,y1,x2,y2);
        add(l);
    }
}
