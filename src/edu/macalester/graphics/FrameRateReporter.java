package edu.macalester.graphics;

public class FrameRateReporter {
    public static boolean enabled = false;

    private int framesSinceLastReport = 0;
    private long timeOfLastReport = System.currentTimeMillis();

    public void tick() {
        if (!enabled) {
            return;
        }

        framesSinceLastReport++;
        long curTime = System.currentTimeMillis();
        if(curTime - timeOfLastReport > 200) {
            double rate = (double) framesSinceLastReport / (curTime - timeOfLastReport) * 1000;
            System.out.printf("%2.1f fps ", rate);
            for (int n = 0; n < Math.round(rate); n++) {
                System.out.print("🟦");
            }
            System.out.println();
            framesSinceLastReport = 0;
            timeOfLastReport = curTime;
        }
    }
}
