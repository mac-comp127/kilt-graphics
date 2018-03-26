# COMP 124 Queue Practice

In this activity, you will practice using the queue methods. 

- Start by copying the queuePractice package from the activityStarterCode/queuePractice 
package to your personal repository.

- Note  the QueueDemo class is what you will be working on. This class has a queue of 
String objects that loosely represent people that might be waiting in line. You will 
notice that this code is using a JOptionPane GUI element to let you interact with the queue, 
adding names to it, peeking, removing, etc.

- Look for TODO comments for items for you to complete. Note that you will do the ‘peek’ first.

    - You will notice that in order to run, you will need to add a main method. The main 
    method will need to create an instance of the MaintainQueue class and then call p
    rocessCustomers. Start by creating a main method so that you can run the class.

    - Run the main once you have made it. Study the code for processCustomers() with 
    the code running. Can you see how each choice the user picks in the GUI is mapped 
    to the switch statement?

    - Study the code given for manipulating the queue in various ways.

    - Once you see how the code works, try to complete the “peek” under case 1: show 
    who is next in line. Look up the java Queue interface documentation.

    - Then work on adding the feature of displaying all people in line.

## If you finish early, here are some optional enhancements:

-   Assume the customer queue represents the line at an ice cream shop. Create an 
IceCreamCone class that contains a stack representing scoops of different ice cream flavors.

- Have a toString() for the IceCreamCone class so that you can list out each cone 
that a customer orders for the next step:

- Modify your customer queue to contain IceCreamCone objects representing the choices 
for that specific customer.

- Create a graphical interface drawing the cones and the order in line.

