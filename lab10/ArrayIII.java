import java.util.Random;

public class ArrayIII<T> {

    private int size;
    private T[] array;

    private static <T> T castToT(Object o) {
        return (T)o;
    }

    public ArrayIII(int N, int k) {
        size = N;
        array = (T[])new Object[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = castToT(random.nextInt(k + 1));
        }
    }

    public ArrayIII(int a, int b, int c, int... values) {
        size = values.length + 3;
        array = (T[])new Object[size];
        array[0] = castToT(a);
        array[1] = castToT(b);
        array[2] = castToT(c);
        int i = 3;
        for (int val : values) {
            array[i++] = castToT(val);
        }
    }

    public double avg() {
        double avg = 0.0;
        for (T val : array) {
            avg += (int)val;
        }
        return avg / size;
    }

    public double stdDeviation() {
        double avg = avg();
        double sum = 0.0;
        for (T val : array) {
            sum += (int)val * (int) val;
        }
        return Math.sqrt(sum/size - avg * avg);
    }

}
