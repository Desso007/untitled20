import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Task1test {
    @Test
    void testAtomicCounter() throws InterruptedException {
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

        assertEquals(threadCount * 1000, counter.get(),
                "Counter value should be equal to the total increments from all threads.");
    }
}
