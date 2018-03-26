package activityStarterCode.queuePractice;

import javax.swing.JOptionPane;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Class to maintain a queue of customers and demonstrate use of queue methods
 * with a Java JOptionPane as an interface.
 *
 * @author Koffman & Wolfgang
 **/
public class QueueDemo {

    // Data Field
    private Queue<String> customers;

    // Constructor
    /** Create an empty queue. */
    public QueueDemo() {
        customers = new LinkedList<String>();
    }

    /**
     * Performs the operations selected on queue customers.
     * @pre  customers has been created.
     * @post customers is modified based on user selections.
     */
    public void processCustomers() {
        int choiceNum = 0;
        String[] choices = {
            "add", "peek", "remove", "size", "position", "quit"};

        // TODO: do case 1 below first!

        // TODO: add "show" to the array above just before quit
        // TODO: after show works, try adding 'remove by name' option

        // Perform all operations selected by user.
        while (choiceNum < choices.length - 1) {
            // Select the next operation.
            choiceNum = JOptionPane.showOptionDialog(null,
                    "Select an operation on customer queue",
                    "Queue menu",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    choices, choices[0]);

            // Process the current choice.
            try {
                String name;
                switch (choiceNum) {
                    case 0:
                        name = JOptionPane.showInputDialog("Enter new customer name");
                        customers.offer(name);
                        JOptionPane.showMessageDialog(null, "Customer " + name + " added to the queue");
                        break;
                    case 1:
                        // TODO: examine how showMeesageDialog is used above and below and display who is next in line
                        //       at the front of the queue (look up Java Queue interface documentation)
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Customer " + customers.remove() + " removed from the queue");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Size of queue is " + customers.size());
                        break;
                    case 4:
                        name = JOptionPane.showInputDialog("Enter customer name");
                        int countAhead = 0;
                        for (String nextName : customers) {
                            if (!nextName.equals(name)) {
                                countAhead++;
                            } else {
                                JOptionPane.showMessageDialog(null, "The number of customers ahead of " + name + " is " + countAhead);
                                break; // Customer found, exit loop.
                            }
                        }

                        // Check whether customer was found.
                        if (countAhead == customers.size()) {
                            JOptionPane.showMessageDialog(null, name + " is not in queue");
                        }
                        break;
                    // TODO: make the following case 6 and add new case 5 for 'show' using showCustomers()
                    case 5:
                        JOptionPane.showMessageDialog(null, "Leaving customer queue. " + "\nNumber of customers in queue is " + customers.size());
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid selection");
                        break;
                }
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(null, "The Queue is empty", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Write each customer in the queue to a string as a comma-separated list
     */
    private String showCustomers() {
        String custListing = "";

        // TODO:
        // You complete this part by iterating over the queue

        return custListing;
    }

    //TODO: Add a main method so that you can run your program. You will need to create a QueueDemo instance
    // and call the processCustomers method.
}

