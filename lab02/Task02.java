class Task02 {
    public static void main(String[] args) {

        String[] names = {"Adam", "Adrian", "Michal", "Kamil", "Aleksander", "Jan", "Leon", "Andrzej", "Mikolaj", "Marcin"};
        String[] surnames = {"Nowak", "Kowalski", "Szewc", "Stelmach", "Wisniewski", "Wojcik", "Lewandowski", "Szymanski", "Zielinski", "Matlok"};

        BazaLudnosci base = new BazaLudnosci();
        for (int i = 1; i < 50; i++) {
            base.add(new Obywatel(names[(int) (Math.random() * 10)], surnames[(int) (Math.random() * 10)], String.valueOf((int) (Math.random() * 100000))));
        }
        base.add(new Obywatel("Please", "Find-Me", "113432"));

        base.print();
        System.out.println();

        System.out.println("Searching for citizen with PESEL = 113432");
        long start = System.nanoTime();
        Obywatel o = base.find("PESEL", "113432");
        long finish = System.nanoTime();
        if (o != null) {
            o.print();
        }
        System.out.println("Time elapsed [ns]: " + (finish - start));

        System.out.println("Searching for citizen with name \"Please\"");
        start = System.nanoTime();
        o = base.find("NAME", "Please");
        finish = System.nanoTime();
        if (o != null) {
            o.print();
        }
        System.out.println("Time elapsed [ns]:" + (finish - start));

        System.out.println("Searching for citizen with surname \"Find-Me\"");
        start = System.nanoTime();
        o = base.find("SURNAME", "Find-Me");
        finish = System.nanoTime();
        if (o != null) {
            o.print();
        }
        System.out.println("Time elapsed [ns]: " + (finish - start));
    }
}

/**
 * class representing Citizen
 */
class Obywatel {

    /**
     * number of existing instances
     */
    static int counter;

    /**
     * PESEL number
     */
    private final String nrPESEL;
    /**
     * name
     */
    private final String name;
    /**
     * surname
     */
    private final String surname;

    /**
     * constructor
     */
    Obywatel(String name, String surname, String nrPESEL) {
        counter++;
        this.nrPESEL = nrPESEL;
        this.name = name;
        this.surname = surname;
    }

    /**
     * getters
     */

    String getPESEL() {
        return nrPESEL;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    static int getCounter() {
        return counter;
    }

    /**
     * prints citizen
     */
    void print() {
        System.out.println("Name: " + name + " " + surname + ", PESEL: " + nrPESEL);
    }
}

/**
 * class representing node of simple b-tree
 */
class Node {

    /**
     * stored element
     */
    private Obywatel el;
    /**
     * left child
     */
    private Node leftChild;
    /**
     * right child
     */
    private Node rightChild;

    /**
     * adds new node to the tree
     *
     * @param o element being added
     */
    void add(Obywatel o) {
        if (el == null) {
            el = o;
            leftChild = new Node();
            rightChild = new Node();
        } else if (o.getPESEL().equals(el.getPESEL())) {
            System.out.println("ERROR! PESEL number not unique!");
        } else if (o.getPESEL().hashCode() > el.getPESEL().hashCode()) {
            leftChild.add(o);
        } else {
            rightChild.add(o);
        }
    }

    /**
     * prints current node
     *
     * @param spaces size of indentation
     */
    void print(int spaces) {
        if (el != null) {
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            el.print();
            leftChild.print(spaces + 2);
            rightChild.print(spaces + 2);
        }
    }

    /**
     * finds element with matching PESEL number
     *
     * @param nrPESEL key value
     */
    Obywatel findByPESEL(String nrPESEL) {
        if (el == null) {
            return null;
        } else if (el.getPESEL().equals(nrPESEL)) {
            return el;
        } else if (nrPESEL.hashCode() > el.getPESEL().hashCode()) {
            return leftChild.findByPESEL(nrPESEL);
        } else {
            return rightChild.findByPESEL(nrPESEL);
        }
    }

    /**
     * finds first element with matching name/surname
     *
     * @param name   key value
     * @param isName information whether key is name or surname
     */
    Obywatel findByName(String name, boolean isName) {
        if (el != null) {
            if ((el.getName().equals(name) && isName) || (el.getSurname().equals(name) && !isName)) {
                return el;
            } else {
                if (leftChild.findByName(name, isName) != null) {
                    return leftChild.findByName(name, isName);
                }
                if (rightChild.findByName(name, isName) != null) {
                    return rightChild.findByName(name, isName);
                }
            }
        }
        return null;
    }

}

/**
 * class representing node simple b-tree
 */
class BazaLudnosci {

    /**
     * root of the tree
     */
    private Node root = new Node();

    /**
     * method inserting new element to the tree
     *
     * @param o new element
     */
    void add(Obywatel o) {
        root.add(o);
    }

    /**
     * method printing tree
     */
    void print() {
        System.out.println("Printing whole base:\n");
        root.print(0);
    }

    /**
     * method finding element inside the tree
     *
     * @param key   can be either NAME, SURNAME, or PESEL
     * @param value value of key
     */
    Obywatel find(String key, String value) {
        switch (key) {
            case "NAME":
                return root.findByName(value, true);
            case "SURNAME":
                return root.findByName(value, false);
            case "PESEL":
                return root.findByPESEL(value);
            default:
                return null;
        }
    }
}