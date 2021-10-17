public class Task00 {

    /**
     * fields
     */
    private int n;
    private int k;
    private double[] arr;
    private final int BAR_CHART_WIDTH = 10;

    /**
     * constructor
     */
    Task00(int n, int k) {
        this.n = n > 0 ? n : 1;
        this.k = k > 0 ? k : 1;
        arr = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                arr[i] += Math.random();
            }
        }
    }

    /**
     * methods
     */

    public double average() {
        double sum = 0.0;
        for (var arg : arr) {
            sum += arg;
        }
        return sum / n;
    }

    public double max() {
        double max = arr[0];
        for (var arg : arr) {
            if (max < arg) max = arg;
        }
        return max;
    }

    public double min() {
        double min = arr[0];
        for (var arg : arr) {
            if (min > arg) min = arg;
        }
        return min;
    }

    public void printArray() {
        for (double arg : arr) {
            System.out.print("[" + arg + "] ");
        }
        System.out.println();
    }

    public void printBarChart() {

        double max = max();
        double min = min();

        double dx = (max - min) / (BAR_CHART_WIDTH - 1);

        int[] occurrences = new int[BAR_CHART_WIDTH];
        for (double arg : arr) {
            occurrences[(int) ((arg - min) / dx)]++;
        }

        for (int i = 0; i < BAR_CHART_WIDTH; i++) {
            StringBuilder line = new StringBuilder(String.format("%2.3f - %2.3f: ", min + i * dx, min + (i + 1) * dx));
            for (int j = 0; j < occurrences[i]; j++) line.append("*");
            System.out.println(line.toString());
        }

    }

}


class Task00Demo {

    public static void main(String[] args) {
        Task00 test = new Task00(50, 5);
        System.out.println("Max: " + test.max() + ", min: " + test.min() + ", average: " + test.average());
        System.out.println("Bar chart:");
        test.printBarChart();
    }

}