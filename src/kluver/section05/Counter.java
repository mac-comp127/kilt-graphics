package kluver.section05;

public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public int click() {
        this.count = this.count + 1;
        return this.getCount();
    }

    public void reset() {
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        c.setCount(13);
        for (int i = 0; i < 42; i++) {
            c.click();
        }
        System.out.println(c.getCount());

    }
}








