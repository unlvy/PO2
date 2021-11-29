public class SaturnV extends Rakieta {

    protected int number;
    protected String name;
    
    SaturnV(int number) { 
        this.number = number;
        name = "SaturnV nr " + number;
    } 
    
    public String toString() {
        return name;
    }

}
