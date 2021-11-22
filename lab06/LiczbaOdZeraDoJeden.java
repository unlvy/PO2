/** class representing number that is >= 0 and <= 1 */
public class LiczbaOdZeraDoJeden extends Liczba {

    /** value */
    private double value;

    { value = 0.0; }
    
    /** constructors */
    LiczbaOdZeraDoJeden() {}
    LiczbaOdZeraDoJeden(double v) { value = ((v >= 0.0 && v <= 1.0) ? v : 0.0); }

    @Override
    public double toDouble() { return value; }

    @Override
    public String toString() { return String.valueOf(value); }
    
    
}
