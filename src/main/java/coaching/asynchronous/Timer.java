package coaching.asynchronous;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer {

    private static int counterGlobal = 0 ;

    public static void main(String[] args) throws InterruptedException {
        var counter = new AtomicInteger(0);

        var t1 = Thread.ofPlatform().unstarted(() -> {
            for (int i=0; i<1000; i++) {
                counter.addAndGet(1);
                counterGlobal++;
            }
        });

        var t2 = Thread.ofPlatform().unstarted(() -> {
            for (int i=0; i<1000; i++) {
                counter.addAndGet(1);
                var tmp = counterGlobal+1;
                counterGlobal = tmp;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
        System.out.println(counterGlobal);










        var list = new ArrayList<Integer>();

        List.of("1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5").parallelStream()
                .map(Integer::valueOf)
                .forEach(list::add);

        System.out.println(list.size());










    }

    private final Instant startTime = Instant.now();
    private final AtomicBoolean reached = new AtomicBoolean(false);

    public Timer(Duration timeToWait) {
        Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reached.set(true);
        });
    }

    public Timer() {}

    public boolean hasReached() {
        return reached.get();
    }

    public boolean hasReachedWithCheck() {
        var now = Instant.now();
        return now.toEpochMilli() > startTime.toEpochMilli() + 1000*60*60*24*10;
    }

}
