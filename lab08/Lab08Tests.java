/*
# kompilacja testów i reszty projektu
javac *.java -cp junit-platform-console-standalone-1.8.1.jar 

# uruchomienie testów
java -jar junit-platform-console-standalone-1.8.1.jar -cp .  --scan-class-path
*/


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Lab08Tests {

    private double eps = 0.5;

    @Test
    public void TestFunkcja() {
    
        assertEquals(Funkcja.X2.wartosc(12.5), 12.5 * 12.5, eps);
        assertEquals(Funkcja.SQRT.wartosc(18.9), Math.sqrt(18.9), eps);
        assertEquals(Funkcja.EXP.wartosc(-3.3), Math.exp(-3.3), eps);
        
        assertThrows(BladFunkcji.class, () -> { Funkcja.SQRT.wartosc(-3.0); } );
    
    }
    
    @Test
    public void TestCalka1() {
    
        CalkaOznaczona cX2 = new CalkaOznaczona(Funkcja.X2);
        CalkaOznaczona cSqrt = new CalkaOznaczona(Funkcja.SQRT);
        CalkaOznaczona cExp = new CalkaOznaczona(Funkcja.EXP);
    
        assertEquals(cX2.wartoscProstokaty(0, 2.0, 1000), 2.667, eps);
        assertEquals(cSqrt.wartoscProstokaty(1.0, 3.0, 1000), 2.79743, eps);
        assertEquals(cExp.wartoscProstokaty(-1.0, 1.0, 1000), 2.3504, eps);
        
        assertThrows(BladCalki.class, () -> { cX2.wartoscProstokaty(12.0, 3.0, -3); } );
        assertThrows(BladCalki.class, () -> { cSqrt.wartoscProstokaty(-7.0, 3.0, 13); } );
    
    }
    
    @Test
    public void TestCalka2() {
    
        CalkaOznaczona cX2 = new CalkaOznaczona(Funkcja.X2);
        CalkaOznaczona cSqrt = new CalkaOznaczona(Funkcja.SQRT);
        CalkaOznaczona cExp = new CalkaOznaczona(Funkcja.EXP);
    
        assertEquals(cX2.wartoscMonteCarlo(0, 2.0, 1000), 2.667, eps);
        assertEquals(cSqrt.wartoscMonteCarlo(1.0, 3.0, 1000), 2.79743, eps);
        assertEquals(cExp.wartoscMonteCarlo(-1.0, 1.0, 1000), 2.3504, eps);
        
        assertThrows(BladCalki.class, () -> { cX2.wartoscMonteCarlo(12.0, 3.0, -3); } );
        assertThrows(BladCalki.class, () -> { cSqrt.wartoscMonteCarlo(-7.0, 3.0, 13); } );
    
    }
    
    @Test
    public void TestCalka3() {
    
        // lambda
        CalkaOznaczona cSin = new CalkaOznaczona((x) -> { return Math.sin(x); });
        
        assertEquals(cSin.wartoscProstokaty(-1.0, 1.0, 1000), 0.0, eps);
        assertEquals(cSin.wartoscMonteCarlo(-1.0, 1.0, 1000), 0.0, eps);
        
        // referencja do metody
        class Linear { public double value(double x) { return 12.3 * x - 8.0; } }
        Linear linear = new Linear();  
        CalkaOznaczona cLin = new CalkaOznaczona(linear::value);
        
        assertEquals(cLin.wartoscProstokaty(-1.0, 1.0, 1000), -16.0, eps);
        assertEquals(cLin.wartoscMonteCarlo(-1.0, 1.0, 1000), -16.0, eps);

        // wykres
        assertEquals(cSin.plot(-2.0, 2.0), true);
        
    }

}
