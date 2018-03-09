package activityStarterCode.stackPractice;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Created by bjackson on 3/31/15.
 */
public class ArithmeticCompiler {

    private static final List<Character> OPERATORS = Arrays.asList('+', '-', '*', '/');
    private static final List<Integer> PRECEDENCE = Arrays.asList(1, 1, 2, 2); // The precedence of the operators matches order in OPERATORS

    private Deque<Character> operands;
    private Deque<Character> operators;

    private char nextOperand;

    /**
     * Initializes instance variables
     */
    public ArithmeticCompiler(){
        //TODO: initialize the operators and operands stacks


        nextOperand = 'z';

    }

    /**
     * Given a mathematical expression  (e.g. a + b - x / y), this method compiles it into the order that it would be
     * evaluated.
     * @param expression to compile
     */
    public void compile(String expression){

        //loop over characters in expression
            //processCharacter
        for (int i =0; i < expression.length(); i++) {
            processCharacter(expression.charAt(i));
        }
        //TODO:
        // while the operators stack is not empty
            //printTableEntry
    }

    /**
     * Handles a single character during the compilation.
     * @param ch
     */
    private void processCharacter(char ch){
        //TODO:
        // if whitespace return     hint: use Character.isWhitspace(ch)
        // else if operand
            //push on operand stack
        //else if operator
            //while ch has a lower precedence than the operator on the top of the stack
                //printTableEntry
            //push ch on operator stack
        // else
            //print error
        // exit
    }

    /**
     * Prints a single row of the expression table
     */
    private void printTableEntry()
    {
        //TODO:
        //pop operator
        //pop operand1
        //pop operand2
        //print operator operand1 operand2 nextOperand
        //push nextOperand on operand stack
        //decrement nextOperand

    }
    /**
     * Returns the precedence of operator op
     * @param op
     * @return
     */
    private int precedence(char op){
        // Always good to check that the input is valid
        if (!isOperator(op)){
            throw new IllegalArgumentException("op is not an operator");
        }
        return PRECEDENCE.get(OPERATORS.indexOf(op));
    }

    /**
     * Tests whether ch is an operator
     * @param ch character to test
     * @return true if ch is an operator
     */
    private boolean isOperator(char ch){
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Tests whether ch is an operand (letter a-m)
     * @param ch character to test
     * @return true if ch is an operand
     */
    private boolean isOperand(char ch) {
        char chLower = Character.toLowerCase(ch);
        return chLower >= 'a' && chLower <= 'm';
    }


    public static void main(String[] args){
        ArithmeticCompiler compiler = new ArithmeticCompiler();

        //get an expression from the user
        //compile the expression
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter an expression: ");
        String expression = scan.nextLine();
        compiler.compile(expression);
    }
}