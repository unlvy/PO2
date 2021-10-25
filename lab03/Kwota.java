/**
 * class representing amount of money
 */
public class Kwota {

    /**
     * amount as sum of grosze
     */
    private int gr;

    /**
     * method returning amount of full zlotowki
     *
     * @return zlotowki
     */
    private int getZl() {
        return gr / 100;
    }

    /**
     * method returning amount of grosze
     *
     * @return grosze
     */
    private int getGr() {
        return gr % 100;
    }

    /**
     * constructor
     *
     * @param zl zlotowki
     * @param gr grosze
     */
    Kwota(int zl, int gr) {
        set(zl, gr);
    }

    /**
     * overridden toString method
     *
     * @return string with amount
     */
    public String toString() {
        return getZl() + "zl " + getGr() + "gr";
    }

    /**
     * method creating deep copy
     *
     * @param agr amount added to new obj
     * @return new obj (deep copy)
     */
    public Kwota createDeepCopy(int agr) {
        return new Kwota(0, this.gr + agr);
    }

    /**
     * method returning new object that is sum of given arguments
     *
     * @param k1 first argument
     * @param k2 second argument
     * @return sum (deepcopy)
     */
    public static Kwota dodaj(Kwota k1, Kwota k2) {
        return k1.createDeepCopy(k2.gr);
    }

    /**
     * method returning new object that is difference of given arguments
     *
     * @param k1 first argument
     * @param k2 second argument
     * @return difference of arguments (deepcopy)
     */
    public static Kwota odejmij(Kwota k1, Kwota k2) {
        return k1.createDeepCopy(-k2.gr);
    }

    /**
     * sets amount
     *
     * @param zl zlotowki
     * @param gr grosze
     */
    public void set(int zl, int gr) {
        this.gr = zl * 100 + gr;
    }

}
