package coaching.asynchronous;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class PlatformVsVirtualThreads {

    private enum Type {
        PLATFORM,
        VIRTUAL
    }

    public static void main(String[] args) {
        final var numberOfThreads = 100000;
        execute(Thread::ofPlatform, numberOfThreads, Type.PLATFORM);
        System.out.println();
        execute(Thread::ofVirtual, numberOfThreads, Type.VIRTUAL);
    }

    private static void execute(Supplier<Thread.Builder> threadSupplier, int numberOfThreads, Type type) {
        var start = System.currentTimeMillis();
        System.out.println("Creating " + numberOfThreads + " " + type + " threads");
        var threads = IntStream.range(0, numberOfThreads)
                .mapToObj(i -> threadSupplier.get().unstarted(PlatformVsVirtualThreads::threadJob))
                .toList();
        var time = System.currentTimeMillis() - start;
        System.out.println("It took " + time + " ms");

        start = System.currentTimeMillis();
        System.out.println("Running with " + numberOfThreads + " " + type + " threads");
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        time = System.currentTimeMillis() - start;
        System.out.println("It took " + time + " ms");
    }

    private static void threadJob() {
        for (int i=0; i<100; i++) {
            if (i%25 == 0) {
                try {
                    Thread.sleep((int) (Math.random() * 100.0));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
