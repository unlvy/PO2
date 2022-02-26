public class Izotop implements Cloneable {

    private String name;
    private int nZ;
    private int nA;
    
    Izotop(char letter, int nA, int nZ) {
        this.name = Character.toString(letter);
        this.nA = nA;
        this.nZ = nZ;
    }
    
    Izotop(String name, int nA, int nZ) {
        this.name = name;
        this.nA = nA;
        this.nZ = nZ;
    }
    
    Izotop(char c) {
        this.name = Character.toString(c);
        nA = -200;
        nZ = -199;
    }
    
    @Override
    public String toString() {
        return name + " nA=" + nA + " nZ=" + nZ;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        Izotop izotop = (Izotop)o;
        return izotop.name.equals(name) 
            && izotop.nA == nA
            && izotop.nZ == nZ;
    }
    
    @Override
    public int hashCode() {
        
        return nA * 7 + nZ * 13;
    
    }
    
    public void set(int nA) {
        this.nA = nA;
    }
    
    public Izotop clone() {
        Izotop result;
        try {
            result = (Izotop)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        result.name = name;
        result.nA = nA;
        result.nZ = nZ;
        return result;    
    }
    
    public int liczbaNeutronow() throws Exception {
        if (nA == -200 && nZ == -199)
                throw new BrakLiczbAZException(name);
        else if (nA < 0 || nZ < 0)
            throw new UjemnaLiczbaAlubZException();
        else if (nA - nZ < 0) 
            throw new UjemnaLiczbaNeutronowException();
        return nA - nZ;
    }
    
    public void liczbyNeutronowProtonow() {
    
        try {
            liczbaNeutronow();
        } catch (Exception e) {
          
            throw new RuntimeException(e.toString() + ": X=" + name);
        }
    
    }
    
}
