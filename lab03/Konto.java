/**
 * class representing bank account
 */
public class Konto {

    /**
     * account identity number
     */
    private String id;
    /**
     * amount of money on account
     */
    private Kwota kwota;

    /**
     * constructor
     *
     * @param id    identity number
     * @param kwota amount of money (deep copy)
     */
    Konto(String id, Kwota kwota) {
        this.id = id;
        this.kwota = kwota.createDeepCopy(0);
    }

    /**
     * overridden toString method
     *
     * @return string with account info
     */
    public String toString() {
        return "[" + id + "] " + kwota;
    }

    /**
     * returns amount of money on account (deep copy)
     *
     * @return kwota
     */
    public Kwota getStan() {
        return kwota.createDeepCopy(0);
    }

    /**
     * sets account balance (overwrites old value)
     *
     * @param kwota new balance
     */
    public void setStan(Kwota kwota) {
        this.kwota = kwota;
    }

    /**
     * returns id
     *
     * @return id
     */
    public String getID() {
        return this.id;
    }

    /**
     * changes account balance
     *
     * @param kwota amount of money to be added/subtracted
     * @param flag  mode, true - adds, false - subtracts
     */
    public void zmienStan(Kwota kwota, Boolean flag) {
        if (flag) {
            this.kwota = Kwota.dodaj(this.kwota, kwota);
        } else {
            this.kwota = Kwota.odejmij(this.kwota, kwota);
        }
    }
}
