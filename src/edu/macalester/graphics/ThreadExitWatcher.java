package edu.macalester.graphics;

import java.util.LinkedList;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * In order to shield students from getting into thread hot water with Swing’s “multithreaded but
 * not thread safe” model, we make the main() method behave as if it were a single synchronous call
 * on the UI thread. This means:
 *
 * 1. CanvasWindows automatically draw() their contents when the main thread exits, and
 * 2. CanvasWindow even handlers and animations do not start until after the main thread exits.
 *
 * This class queues up those deferred tasks, and runs them on the Swing UI when it detects that
 * main() is done.
 */
class ThreadExitWatcher {
    private final Object lock = new Object();
    private final long watchedThreadID;
    private final String watchedThreadName;
    private Timer exitCheck;
    private LinkedList<Runnable> queuedTasks = new LinkedList<>();

    ThreadExitWatcher() {
        watchedThreadID = Thread.currentThread().getId();
        watchedThreadName = Thread.currentThread().getName();

        exitCheck = new Timer(100, e -> checkForThreadExit());
        exitCheck.setRepeats(true);
        exitCheck.start();
    }

    /**
     * Runs the given task on the AWT thread after the watched thread has exited (or immediately
     * if it already has).
     */
    void afterThreadExits(Runnable task) {
        synchronized (lock) {
            if (queuedTasks != null) {
                queuedTasks.add(task);
            } else {
                SwingUtilities.invokeLater(task);
            }
        }
    }

    private void checkForThreadExit() {
        synchronized (lock) {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                if (thread.getId() == watchedThreadID) {
                    return;
                }
            }
            exitCheck.stop();

            System.out.println(watchedThreadName + " has exited; running queued tasks");

            for (Runnable task : queuedTasks) {
                task.run();
            }
            queuedTasks = null;
        }
    }
}
