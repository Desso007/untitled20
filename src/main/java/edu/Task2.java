package edu;
import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {
    public static BigInteger calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input should be a non-negative integer.");
        }

        return LongStream.rangeClosed(1, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static void main(String[] args) {
        int number = 10;
        BigInteger factorial = calculateFactorial(number);
        System.out.println("Factorial of " + number + " is: " + factorial);
    }
}
