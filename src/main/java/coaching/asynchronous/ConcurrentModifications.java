package coaching.asynchronous;

import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentModifications {

    private static int unsafeCounter = 0;
    private static final AtomicInteger safeCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        var numberOfIterations = 1000;

        // Die beiden Threads werden erzeugt, aber noch nicht gestartet
        var counterThread1 = Thread.ofVirtual().unstarted(() -> incrementCounter(numberOfIterations));
        var counterThread2 = Thread.ofVirtual().unstarted(() -> incrementCounter(numberOfIterations));

        // Die beiden Threads werden gestartet
        counterThread1.start();
        counterThread2.start();

        // Es wird so lange gewartet, bis beide Threads fertig sind
        counterThread1.join();
        counterThread2.join();

        // Das Ergebnis wird ausgegeben
        System.out.println("unsafe counter: " + unsafeCounter);
        System.out.println("safe counter: " + safeCounter.get());
    }

    private static void incrementCounter(int numberOfIterations) {
        for (int i=0; i<numberOfIterations; i++) {
            unsafeCounter++;
            safeCounter.addAndGet(1);
        }
    }

}
