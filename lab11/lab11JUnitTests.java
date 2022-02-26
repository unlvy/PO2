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

import java.util.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class lab11JUnitTests {

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
        assertNotEquals(a2, a3);
    }

    @Test
    public void testA__1p() {
        int r = rnd.nextInt(10);
        Wierzcholek<String> a = new Wierzcholek<>("A"+r);
        assertEquals( "A"+r, a.getId() );
        Wierzcholek<Integer> b = new Wierzcholek<>(r);
        assertEquals( r, b.getId() );
    }
   
    @Test
    public void testB__1p() {
        Graf<String,Krawedz<String>> g = new Graf<>();
        assertEquals( 0, g.rzad() ); // rzad grafu = liczba wierzcholkow
        Wierzcholek<String> a = new Wierzcholek<>("A");
        g.dodajWierzcholek(a);
        assertEquals( 1, g.rzad() );
        g.dodajWierzcholek(new Wierzcholek<String>("B"));
        assertEquals( 2, g.rzad() );
    }
    
    @Test
    public void testC__1p() {
        Graf<String,Krawedz<String>> g = new Graf<>();
        assertEquals( 0, g.rzad() ); // rzad grafu = liczba wierzcholkow        
        Wierzcholek<String> a = new Wierzcholek<>("A");
        g.dodajWierzcholek(a);
        assertEquals( 1, g.rzad() );
        assertThrows(WierzcholekJuzIstniejeException.class, () -> g.dodajWierzcholek(a) );
        assertThrows(WierzcholekJuzIstniejeException.class, () -> g.dodajWierzcholek(new Wierzcholek<String>("A")) );
        assertEquals( 1, g.rzad() );
    }
     
    @Test
    public void testD__2p() {
        Graf<Integer,Krawedz<Integer>> g = new Graf<>();
        assertEquals( "", g+"" );
        Wierzcholek<Integer> a = new Wierzcholek<>(1);
        Wierzcholek<Integer> b = new Wierzcholek<>(2);
        Wierzcholek<Integer> c = new Wierzcholek<>(3);
        g.dodajWierzcholek(a);
        g.dodajWierzcholek(b);
        g.dodajWierzcholek(c);
        assertEquals( 3, g.rzad() );
        assertEquals( "\n1 --\n2 --\n3 --\n", g+"" ); // lista sasiedztwa (na razie brak krawedzi, czyli polaczen miedzy wierzcholkami - dodamy je w dalszych testach)
    }
    
    @Test
    public void testE__2p() {
        Graf<Integer,Krawedz<Integer>> g = new Graf<>();
        Wierzcholek<Integer> a = new Wierzcholek<>(1);
        Wierzcholek<Integer> b = new Wierzcholek<>(2);
        Wierzcholek<Integer> c = new Wierzcholek<>(3);
        Wierzcholek<Integer> d = new Wierzcholek<>(4);
        Wierzcholek<Integer> e = new Wierzcholek<>(5);
        g.dodajWierzcholek(a);
        g.dodajWierzcholek(b);
        g.dodajWierzcholek(c);
        g.dodajWierzcholek(d);
        g.dodajWierzcholek(e);
        assertEquals( 5, g.rzad() );
        g.dodajKrawedz( new Krawedz<Integer>(1,2) );
        g.dodajKrawedz( new Krawedz<Integer>(1,4) );
        assertEquals( 2, g.rozmiar() ); // rozmiar grafu = liczba krawedzi
        g.dodajKrawedz( new Krawedz<Integer>(2,3) );
        g.dodajKrawedz( new Krawedz<Integer>(2,4) );
        g.dodajKrawedz( new Krawedz<Integer>(4,5) );
        assertEquals( 5, g.rozmiar() );
        assertEquals( "\n1 -- 2 4\n2 -- 1 3 4\n3 -- 2\n4 -- 1 2 5\n5 -- 4\n", g+"" );
    }    
    
    @Test
    public void testF__1p() {
        Graf<String,Krawedz<String>> g = new Graf<>();
        g.dodajWierzcholek( new Wierzcholek<String>("X") );
        g.dodajWierzcholek( new Wierzcholek<String>("Y") );
        g.dodajWierzcholek( new Wierzcholek<String>("Z") );
        g.dodajKrawedz( new Krawedz<String>("X","Y") );
        g.dodajKrawedz( new Krawedz<String>("X","Z") );
        assertEquals(3, g.rzad() );
        assertEquals(2, g.rozmiar() );
        assertThrows(KrawedzJuzIstniejeException.class, () -> g.dodajKrawedz(new Krawedz<String>("X","Y")) );
        assertEquals("\nX -- Y Z\nY -- X\nZ -- X\n", g+"" );
    } 
    
    @Test
    public void testG__2p() {
        Graf<Integer,KrawedzWazona<Integer>> g = new Graf<>();
         g.dodajWierzcholek(new Wierzcholek<Integer>(1));
         g.dodajWierzcholek(new Wierzcholek<Integer>(2));
         g.dodajWierzcholek(new Wierzcholek<Integer>(3));
         g.dodajWierzcholek(new Wierzcholek<Integer>(4));
         assertEquals(4, g.rzad() );
         KrawedzWazona<Integer> t = new KrawedzWazona<Integer>(3,1,0.3);
         g.dodajKrawedz(new KrawedzWazona<Integer>(3,1,0.3) );
         g.dodajKrawedz(new KrawedzWazona<Integer>(2,3,0.9) );
         g.dodajKrawedz(new KrawedzWazona<Integer>(4,3,1.8) );
         assertEquals(3, g.rozmiar() );
    }
 /*   
*/

}   