/**
 * class representing list node
 */
class Node {

    /**
     * next node
     */
    Node next;
    /**
     * value stored
     */
    Konto element;

}

/**
 * class representing bank (as simple single-linked list of accounts)
 */
public class Bank {

    /**
     * first element
     */
    private Node head;

    /**
     * constructor
     */
    Bank() {
        head = new Node();
    }

    /**
     * adds new account to the list
     *
     * @param id id number of account
     */
    public void utworzKonto(String id) {
        Node dest = head;
        while (dest.element != null) {
            dest = dest.next;
        }
        dest.element = new Konto(id, new Kwota(0, 0));
        dest.next = new Node();
    }

    /**
     * overridden toString method
     *
     * @return string with list of accounts
     */
    public String toString() {

        String result = "";

        Node current = head;
        while (current.element != null) {
            result += current.element + " ; ";
            current = current.next;
        }
        return result;
    }

    /**
     * changes balance of account with given id
     *
     * @param id    id number of account
     * @param kwota amount of money changing account balance
     * @param flag  mode, true - adds, false - subtracts
     */
    public void zmienStanKonta(String id, Kwota kwota, Boolean flag) {

        Node current = head;

        while (current.element != null && !current.element.getID().equals(id)) {
            current = current.next;
        }

        if (current.element != null) {
            current.element.zmienStan(kwota, flag);
        }
    }

    /**
     * adds amount of money to account balance
     *
     * @param id    id number of account
     * @param kwota amount of money
     */
    public void wplataNaKonto(String id, Kwota kwota) {
        zmienStanKonta(id, kwota, true);
    }

    /**
     * pays out amount of money from account balance
     *
     * @param id    id number of account
     * @param kwota amount of money
     */
    public void wyplataZKonta(String id, Kwota kwota) {
        zmienStanKonta(id, kwota, false);
    }

    /**
     * returns deep copy of account balance with given id
     *
     * @param id id number of account
     * @return deep copy of amount of money stored on account
     */
    public Kwota stanKonta(String id) {

        Node current = head;

        while (current.element != null && !current.element.getID().equals(id)) {
            current = current.next;
        }

        if (current.element != null) {
            return current.element.getStan();
        } else {
            return null;
        }
    }

}
