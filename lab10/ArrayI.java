import java.util.Random;

public class ArrayI {

    private int size;
    private int[] array;

    public ArrayI(int N, int k) {
        size = N;
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(k + 1);
        }
    }

    public ArrayI(int a, int b, int c, int... values) {
        size = values.length + 3;
        array = new int[size];
        array[0] = a;
        array[1] = b;
        array[2] = c;
        int i = 3;
        for (int val : values) {
            array[i++] = val;
        }
    }

    public double avg() {
        double avg = 0.0;
        for (int val : array) {
            avg += val;
        }
        return avg / size;
    }

    public double stdDeviation() {
        double avg = avg();
        double sum = 0.0;
        for (int val : array) {
            sum += val *  val;
        }
        return Math.sqrt(sum/size - avg * avg);
    }

}
