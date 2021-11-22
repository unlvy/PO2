import java.util.Arrays;

/** class representing list that can store only Liczba objects */
public class ListaLiczb extends Lista {

    @Override
    protected Object[] initArray(int size) {
        return new Liczba[size];
    }
    
    /** 
     * returns minimum value 
     * @return min
     * */
    public Liczba min() {
        if (size == 0) return null; 
        Liczba min = (Liczba)elements[0];
        double minVal = min.toDouble();
        for (int i = 1; i < size; i++) {
            if (((Liczba)elements[i]).toDouble() < minVal) {
                min = (Liczba)elements[i];
                minVal = min.toDouble();
            } 
        }
        return min;
    }
    
    /** 
     * returns maximum value 
     * @return max
     * */
    public Liczba max() {
    if (size == 0) return null; 
    Liczba max = (Liczba)elements[0];
    double maxVal = max.toDouble();
    for (int i = 1; i < size; i++) {
        if (((Liczba)elements[i]).toDouble() > maxVal) {
            max = (Liczba)elements[i];
            maxVal = max.toDouble();
        } 
    }
    return max;
    }   
    
    /**
     * returns list as array of doubles
     * @return double[]
     * */
    public double[] array() {
        double[] result = new double[size];
        for (int i = size - 1; i >= 0; i--) {
            result[size - 1 - i] = ((Liczba)elements[i]).toDouble();
        }
        return result;    
    }
    
    /**
     * sorts the list in descending order
     * */
    public void sort() {
        if (size > 0) {
            Arrays.sort(elements, 0, size, (Object o1, Object o2) -> 
                {
                    Liczba l1 = (Liczba)o1; Liczba l2 = (Liczba)o2;
                    if (l1.toDouble() > l2.toDouble()) return 1;
                    else if (l1.toDouble() < l2.toDouble()) return -1;
                    else return 0;
                }
            );
        }
    }
}
