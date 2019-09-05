package activityStarterCode.listPractice;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsText;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bjackson on 3/2/2016.
 */
public class Nim extends CanvasWindow implements MouseListener {

    public static final int NUM_COINS = 11;
    public static final double COIN_DIAMETER = 75;
    public static final double COINS_Y = 100;
    public static final double STARTING_X = 20;
    public static final double DISTANCEBETWEEN = 10;
    public static final String PLAYER1_TEXT = "Player 1's turn:";
    public static final String PLAYER2_TEXT = "Player 2's turn:";

    private GraphicsText label;
    private ArrayList<Ellipse> coins;
    private int selectedIndex; //the index into the coins array for the coin that was selected when the mouse is pressed.
                               // It contains the value of -1 if a coin is not selected with the mouse press.


    /**
     * Initialize instance variables
     */
    public Nim(){
        super("Nim", 1000, 375);
        selectedIndex = -1;
        label = new GraphicsText(PLAYER1_TEXT, (float)STARTING_X, 50.0f);
        label.setFont(new Font("SanSerif", Font.PLAIN, 24));
        add(label);

        //TODO: initialize the coins ArrayList.


        createCoins();
//        addMouseListener(this);
    }

    /**
     * Creates a row of Ellipse objects representing coins, placed DISTANCEBETWEEN apart. The circle's size should be determined
     * by the COIN_DIAMETER instance variable. The placement of the first circle's upper left corner is determined by the values
     * of the STARTING_X and COINS_Y instance variables. The circles should be added to the arraylist and the canvas.
     */
    private void createCoins(){
        //TODO: Complete the method

    }

    /**
     * Changes the value of the label based on who's turn it currently is.
     */
    private void advanceTurn(){
        if (label.getText().equals(PLAYER1_TEXT)){
            label.setText(PLAYER2_TEXT);
        }
        else {
            label.setText(PLAYER1_TEXT);
        }
    }

    /**
     * Checks for two conditions:
     * (1) The current player removed all the coins in which case they lose.
     * (2) There is only one coin left meaning the current player has won.
     * @return true if one of the players wins (i.e. there are zero or one coins left)
     */
    private boolean checkForWin(){
        //current player removed last coin
        if (coins.size() == 0){
            label.setText("You Lose!");
            return true;
        }
        else if (coins.size() == 1){
            if (label.getText().equals(PLAYER1_TEXT)){
                label.setText("Player 1 wins!");
            }
            else {
                label.setText("Player 2 wins!");
            }
            return true;
        }
        return false;
    }

    /**
     * Sets the fill color of the coins in the coins array from [index, coins.size()) to color.
     * @param index starting index to set the color
     * @param color color to set
     */
    private void colorCoins(int index, Color color){
        //TODO: Set the fill color for the coins in the array from index to the end of the list

    }

    /**
     * Removed all the coins in the coins array from [index, coins.size())
     * @param index starting index
     */
    private void removeCoins(int index){
        Iterator<Ellipse> iter = coins.listIterator(index);
        while(iter.hasNext()){
            Ellipse oval = iter.next();
            remove(oval); // remove oval from canvas
            iter.remove(); // remove oval from array list
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Responds to mouse press down events
     * @param event
     */
    @Override
    public void mousePressed(MouseEvent event){
        //TODO: If the mouse was clicked on a coin object, find the index of that coin in the coins arraylist.
        // If the index is within the last three coins in the arraylist, then set selectedIndex to be the index you have found,
        // and color the coins red.

    }

    /**
     * Responds to mouse button released events
     * @param event
     */
    @Override
    public void mouseReleased(MouseEvent event){
        //TODO: If an index was selected, remove the coins, set selectedIndex back to -1, and check if anyone has won.
        // If nobody has one so far then advance to the next player's turn.

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args){
        Nim game = new Nim();
    }

}
