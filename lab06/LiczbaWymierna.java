/** class representing rational number */
public class LiczbaWymierna extends Liczba {

    /** numerator */
    private int numerator;
    /** denominator */
    private int denominator;

    { numerator = 0; denominator = 1; }
    
    /** constructor */
    LiczbaWymierna() {}
    LiczbaWymierna(int n, int d) { numerator = n; denominator = (d != 0 ? d : 1); }

    @Override
    public double toDouble() { return (double)numerator / denominator; }

    @Override
    public String toString() { return numerator + "/" + denominator; }
    
    
}
