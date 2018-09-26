package kluver.section04;

public class CountingClicker {
    private int count;

    public CountingClicker() {
        count = 0;
    }

    public void reset() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public int click() {
        this.count = this.count + 1;
        return getCount();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        CountingClicker cc = new CountingClicker();
        cc.setCount(10);
        for (int i = 0; i < 11; i++) {
            cc.click();
        }
        System.out.print(cc.getCount());
    }
}




















