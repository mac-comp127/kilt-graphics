package marsh.objects;

public class InboxMain {

    public static void main(String[] args) {

        Inbox in = new Inbox(10);

        while(!in.isEmpty()) {
            System.out.println("Resolving a task");
            in.resolveTask();
        }

        // bad practice! only works with public instance far declaration in Inbox
        //in.num = 10;

        // This one should work fine though!
        //in.setTasks(10);

        if(in.isEmpty()) {
            System.out.println("Inbox contains: " + in.getTasks() + " tasks.");
        }
    }
}
