package marsh.objects;

public class Inbox {

    //public int num = 0;
    private int num = 0;
    //public boolean isEmpty = true;
    private boolean isEmpty = true;

    public Inbox(int num) {
        this.num = num;
        if(this.num > 0) {
            isEmpty = false;
        }
    }

    public int getTasks() {
        return num;
    }

    public void setTasks(int num) {
        this.num = num;
        if(this.num > 0) {
            isEmpty = false;
        }

    }

    public void addTask() {
        num++;
        isEmpty = false;
    }

    public void resolveTask() {
        num--;
        if(this.num == 0) {
            isEmpty = true;
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
