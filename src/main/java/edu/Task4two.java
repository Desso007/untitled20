package edu;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Task4two {

    public static void main(String[] args) {
        int totalSimulations = 1000000;
        int numThreads = Runtime.getRuntime().availableProcessors();
        long startTime = System.currentTimeMillis();
        double piApproximation = calculatePiParallel(totalSimulations, numThreads);
        long endTime = System.currentTimeMillis();

        System.out.println("Pi approximation: " + piApproximation);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }

    private static double calculatePiParallel(int totalSimulations, int numThreads) {
        int circleCount = (int) IntStream.range(0, totalSimulations)
                .parallel()
                .mapToDouble(i -> {
                    double x = ThreadLocalRandom.current().nextDouble(-1, 1);
                    double y = ThreadLocalRandom.current().nextDouble(-1, 1);
                    double distance = Math.sqrt(x * x + y * y);
                    return (distance <= 1) ? 1 : 0;
                })
                .sum();

        return 4.0 * circleCount / totalSimulations;
    }
}
