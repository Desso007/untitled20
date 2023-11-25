package edu;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int threadCount = 5;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final Counter Value: " + counter.get());

        if (counter.get() == threadCount * 1000) {
            System.out.println("Test passed: Counter is thread-safe.");
        } else {
            System.out.println("Test failed: Counter is not thread-safe.");
        }
    }
}
