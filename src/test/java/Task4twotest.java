

import edu.Task4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task4twotest {

    @Test
    void testCalculatePi() {
        int totalSimulations = 1000000;
        double delta = 0.01;

        Task4 task4 = new Task4();
        double piApproximation = task4.calculatePi(totalSimulations);

        assertEquals(Math.PI, piApproximation, delta);
    }
}