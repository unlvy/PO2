import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayJUnitTests {

    private final double eps = 1e-9;

    @Test
    void ArrayITest() {
        ArrayI a1 = new ArrayI(1, 2, 3, 4, 5, 6);
        ArrayI a2 = new ArrayI(6, 5, 4, 3, 2, 1);
        assertEquals(a1.avg(), a2.avg(), eps);
        assertEquals(a1.stdDeviation(), a2.stdDeviation(), eps);

        ArrayI a3 = new ArrayI(2, 4, 4, 4, 5, 5, 7 ,9);
        assertEquals(5d, a3.avg(), eps);
        assertEquals(2d, a3.stdDeviation(), eps);
    }

    @Test
    void ArrayIITest() {
        ArrayII a1 = new ArrayII(1, 2, 3, 4, 5, 6);
        ArrayII a2 = new ArrayII(6, 5, 4, 3, 2, 1);
        assertEquals(a1.avg(), a2.avg(), eps);
        assertEquals(a1.stdDeviation(), a2.stdDeviation(), eps);

        ArrayII a3 = new ArrayII(2, 4, 4, 4, 5, 5, 7 ,9);
        assertEquals(5d, a3.avg(), eps);
        assertEquals(2d, a3.stdDeviation(), eps);
    }

    @Test
    void ArrayIIITest() {
        ArrayIII<Integer> a1 = new ArrayIII<Integer>(1, 2, 3, 4, 5, 6);
        ArrayIII<Integer> a2 = new ArrayIII<Integer>(6, 5, 4, 3, 2, 1);
        assertEquals(a1.avg(), a2.avg(), eps);
        assertEquals(a1.stdDeviation(), a2.stdDeviation(), eps);

        ArrayIII<Integer> a3 = new ArrayIII<Integer>(2, 4, 4, 4, 5, 5, 7 ,9);
        assertEquals(5d, a3.avg(), eps);
        assertEquals(2d, a3.stdDeviation(), eps);
    }

    @AfterAll
    static void performanceTests() {
        for (int i = 0; i < 3; i++) {

            ArrayI a1 = new ArrayI(100000000, 100);
            long start1 = System.currentTimeMillis();
            a1.stdDeviation();
            long end1 = System.currentTimeMillis();

            ArrayII a2 = new ArrayII(100000000, 100);
            long start2 = System.currentTimeMillis();
            a2.stdDeviation();
            long end2 = System.currentTimeMillis();

            ArrayIII a3 = new ArrayIII(100000000, 100);
            long start3 = System.currentTimeMillis();
            a3.stdDeviation();
            long end3 = System.currentTimeMillis();

            System.out.println((i + 1) + ". [Performance] int: " + (end1 - start1) + ", Integer: " + (end2 - start2)  + ", generics: " + (end3 - start3));

        }
    }


}
