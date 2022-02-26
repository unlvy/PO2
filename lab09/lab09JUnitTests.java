/*

# pobranie JUnit-a
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.1/junit-platform-console-standalone-1.8.1.jar

# kompilacja testów i reszty projektu
javac *.java -cp junit-platform-console-standalone-1.8.1.jar 

# uruchomienie testów
java -jar junit-platform-console-standalone-1.8.1.jar -cp .  --scan-class-path

*/


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class lab09JUnitTests {

    Random rnd = new Random();

    @Test
    public void test0() {
        class MyClass {
            int n;
            public MyClass(int n) { this.n = n; }
            public String toString() { return "MyClass{"+n+"}"; }
        }
        int r = rnd.nextInt(9);
        MyClass a1 = new MyClass(1);
        MyClass a2 = new MyClass(r);
        MyClass a3 = new MyClass(r);
        assertEquals("MyClass{1}", a1+"" );
        assertEquals("MyClass{"+r+"}", a2+"" );
        assertEquals("MyClass{"+r+"}", a3+"" );
        assertTrue(a1 != a2);
        assertTrue(a2 != a3);
        assertTrue(!a1.equals(a2));
        assertTrue(!a2.equals(a3));
    }

    @Test
    public void testA__3p() {
        int lZ = 1;
        int lA = lZ + rnd.nextInt(2);
        Izotop x = new Izotop('H', lA, lZ); 
        Izotop y = new Izotop('H', lA, lZ); 
        Izotop z = x; 
        Izotop q = new Izotop('H', lA+1, lZ); 
        
        assertTrue( x != y );
        assertTrue( x == z );
        assertTrue( x != q );
        
        assertEquals( "object"+x, "object"+y );
        assertEquals( "object"+x, "object"+z );
        assertNotEquals( "object"+x, "object"+q );
        
        assertEquals( x, y );
        assertEquals( x, z );
        assertNotEquals( x, q );
        
        x.set(lA+2); 
        assertNotEquals( x, y );
        assertEquals( x, z );
        assertNotEquals( x, q );
        assertNotEquals( null, q );
    }
   
    @Test
    public void testB__2p() {
        int lZ = 1;
        int lA = lZ + rnd.nextInt(2);
        Izotop x = new Izotop('H', lA, lZ); 
        Izotop z = x; 
        assertTrue( x == z );
        assertEquals( "object"+x, "object"+z );
        assertEquals( x, z );
        
        z = (Izotop)x.clone(); 
        assertTrue( x != z );
        assertEquals( "object"+x, "object"+z );
        assertEquals( x, z );
        
        Object ox = x;
        Object oz = z;
        assertTrue( ox != oz );
        assertEquals( "object"+ox, "object"+oz );
        assertEquals( ox, oz );
        
        x.set(lA+2); 
        assertNotEquals( x, z );
    }
     
    @Test
    public void testC__1p() {
        int lZ = 1;
        int lA = lZ + rnd.nextInt(2);
        Izotop x = new Izotop('H', lA, lZ); 
        Izotop y = new Izotop('H', lA, lZ); 
        
        assertEquals( x, y );
        assertEquals( x.hashCode(), y.hashCode() );
        
        int hx = x.hashCode();
        int hx2 = x.hashCode();
        assertEquals( hx, hx2 );
        x.set(lA+2); 
        assertNotEquals( x, y );
        int hx3 = x.hashCode();
        assertNotEquals( hx, hx3 );
        
        Izotop z = (Izotop)x.clone();
        assertEquals( x, z );
        assertEquals( x.hashCode(), z.hashCode() );
    }
    
    @Test
    public void testD__2p() {
        Exception e1 = new BrakLiczbAZException();
        Exception e2 = new UjemnaLiczbaAlubZException();
        
        try
        {
            Izotop x = new Izotop('C');
            assertThrows(BrakLiczbAZException.class, () -> { x.liczbaNeutronow(); } );
            Izotop y = new Izotop('C',13,6);
            int yw = y.liczbaNeutronow();
            assertEquals( 7, yw );
        } 
        catch(Exception e) { }
        
        Izotop z = new Izotop('P',-1,7);
        assertThrows(UjemnaLiczbaAlubZException.class, () -> { z.liczbaNeutronow(); } );
        
        Object o = new BrakLiczbAZException();
        assertThrows(ClassCastException.class, () -> {RuntimeException e = (RuntimeException)o; } );
        
        Izotop w = new Izotop('P',7,8);
        assertThrows(UjemnaLiczbaNeutronowException.class, () -> { w.liczbaNeutronow(); } );
        
        Object q = new UjemnaLiczbaNeutronowException();
        assertThrows(ClassCastException.class, () -> {RuntimeException e = (RuntimeException)q; } );
    }
    
    @Test
    public void testE__2p() {
        Izotop x = new Izotop('H');
        Izotop y = new Izotop('C');
        
        try
        {
            x.liczbaNeutronow();
        } 
        catch(Exception e) {
            assertEquals( "X="+"H", e.getMessage() );
        }
        
        try
        {
            y.liczbaNeutronow();
        } 
        catch(Exception e) {
            assertEquals( "X="+"C", e.getMessage() );
        }
        
        Izotop w = new Izotop('P',7,8);
        assertThrows(RuntimeException.class, ()->{ w.liczbyNeutronowProtonow();});
        
        try
        {
            w.liczbyNeutronowProtonow();
        } 
        catch(Exception e) {
            UjemnaLiczbaNeutronowException e1 = (UjemnaLiczbaNeutronowException) e.getCause();
            assertEquals( "UjemnaLiczbaNeutronowException: X="+"P", e.getMessage() );
        }
    }

}

