public class Wahadlowiec implements PojazdKosmiczny, Jezdzi {

    protected String name;
    
    Wahadlowiec(String name) { 
        this.name = "Wahadlowiec " + name;
    } 
    
    public String toString() {
        return name;
    }

}
