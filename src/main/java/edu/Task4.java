package edu;

import java.util.concurrent.ThreadLocalRandom;

public class Task4 {

    public static void main(String[] args) {
        int totalSimulations = 1000000;

        long startTime = System.currentTimeMillis();
        double piApproximation = calculatePi(totalSimulations);
        long endTime = System.currentTimeMillis();

        System.out.println("Pi approximation: " + piApproximation);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }

    public static double calculatePi(int totalSimulations) {
        int circleCount = 0;
        for (int i = 0; i < totalSimulations; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = ThreadLocalRandom.current().nextDouble(-1, 1);
            double distance = Math.sqrt(x * x + y * y);
            if (distance <= 1) {
                circleCount++;
            }
        }
        return 4.0 * circleCount / totalSimulations;
    }
}
