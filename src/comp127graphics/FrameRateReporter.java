package comp127graphics;

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
            System.out.printf("%2.1f fps\n", (double) framesSinceLastReport / (curTime - timeOfLastReport) * 1000);
            framesSinceLastReport = 0;
            timeOfLastReport = curTime;
        }
    }
}
