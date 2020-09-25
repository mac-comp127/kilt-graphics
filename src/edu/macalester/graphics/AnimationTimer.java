package edu.macalester.graphics;

import java.util.function.DoubleConsumer;

/**
 * Simple timer that provides a more stable frame rate than Swing's built-in timer,
 * and passes a dt to its callback.
 */
class AnimationTimer implements Runnable {
    private final long targetInterval;
    private final DoubleConsumer callback;

    AnimationTimer(long targetInterval, DoubleConsumer callback) {
        this.targetInterval = targetInterval;
        this.callback = callback;
        
        Thread thread = new Thread(this, "animation timer");
        thread.setPriority((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2);
        thread.start();
    }

    @Override
    public void run() {
        long lastUpdate = System.currentTimeMillis() - targetInterval;
        while (true) {
            long curTime = System.currentTimeMillis();

            callback.accept((curTime - lastUpdate) / 1000.0);

            lastUpdate = curTime;
            try {
                Thread.sleep(Math.max(0, lastUpdate + targetInterval - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.err.println(getClass().getSimpleName() + " interrupted");
                return;
            }
        }
    }
}
