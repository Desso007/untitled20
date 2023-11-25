import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import edu.Task2;
import org.junit.jupiter.api.Test;
public class Task2test {
    private Task2 ParallelFactorial;

    @Test
    void testFactorialWithSmallNumber() {
        int number = 5;
        BigInteger expected = BigInteger.valueOf(120);
        assertEquals(expected, ParallelFactorial.calculateFactorial(number));
    }

    @Test
    void testFactorialWithZero() {
        int number = 0;
        BigInteger expected = BigInteger.ONE;
        assertEquals(expected, ParallelFactorial.calculateFactorial(number));
    }

    @Test
    void testFactorialWithLargeNumber() {
        int number = 20;
        BigInteger expected = new BigInteger("2432902008176640000");
        assertEquals(expected, ParallelFactorial.calculateFactorial(number));
    }

    @Test
    void testFactorialWithNegativeNumber() {
        int number = -1;
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> ParallelFactorial.calculateFactorial(number));
        assertEquals("Input should be a non-negative integer.", exception.getMessage());
    }
}
