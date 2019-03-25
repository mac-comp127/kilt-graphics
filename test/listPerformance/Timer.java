package listPerformance;

/**
 * A simple timer class. After creating a timer, one of the following
 * two patterns can be used:
 *
 *       Timer t = new Timer();
 *       t.start();
 *       ... Do some work ...
 *       double elapsedSeconds = t.stop();
 *
 *
 *       Timer t = new Timer();
 *       t.start();
 *       ... Do some timed work ...
 *       t.pause();
 *       ... Do some un-timed work ...
 *       t.resume();
 *       ... Do some timed work ...
 *      double elapsedSeconds = t.stop();
 *
 * Note that if you use the second pattern, with pauses, each pause
 * MUST be paired with a resume.
 *
 * @author Shilad
 */
public class Timer {
    private long startMillis = -1;
    private long elapsed = 0;

    public void Timer() {}

    /**
     * Start the timer.
     * This must be called before calling any other methods.
     */
    public void start() {
        startMillis = System.currentTimeMillis();
    }

    /**
     * Pause the timer.
     * This must be followed by a call to resume.
     */
    public void pause() {
        if (startMillis < 0) {
            throw new IllegalStateException("Timer was never started");
        }
        elapsed += System.currentTimeMillis() - startMillis;
        startMillis = -1;
    }

    /**
     * Resume the timer.
     * This must be preceded by a call to pause.
     */
    public void resume() {
        startMillis = System.currentTimeMillis();
    }

    /**
     * Stop the timer and return the total elapsed time.
     */
    public double stop() {
        if (startMillis < 0) {
            throw new IllegalStateException("Timer was never started");
        }
        double total = (elapsed + System.currentTimeMillis() - startMillis) / 1000.0;
        reset();
        return total;
    }

    /**
     * Reset the timer. Any running timers are forgotten.
     */
    public void reset() {
        elapsed = 0;
        startMillis = -1;

    }

}
