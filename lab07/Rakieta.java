public abstract class Rakieta implements PojazdKosmiczny {

    public static String producent() { return "NASA"; }
    
    static class Zaloga {}
    
    public class Ladownik implements PojazdKosmiczny {}
    
    Apollo ladownikApollo() { return new Apollo(new SaturnV(0)); }

}

