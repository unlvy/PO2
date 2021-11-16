import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** class containing Vector3D tests */
public class Vector3DTest {

    @Test
    public void setTest() {
        Vector3D test = new Vector3D();
        test.set(12.5, 123.3, -7889.13);
        assertEquals("[12.5, 123.3, -7889.13]", test.toString());
    }
    
    @Test
    public void setXTest() {
        Vector3D test = new Vector3D();
        test.setX(89.13);
        assertEquals("[89.13, 0.0, 0.0]", test.toString());
    }
    
    @Test
    public void setYTest() {
        Vector3D test = new Vector3D();
        test.setY(1110.3);
        assertEquals("[0.0, 1110.3, 0.0]", test.toString());
    }
    
    @Test
    public void setZTest() {
        Vector3D test = new Vector3D();
        test.setZ(-8);
        assertEquals("[0.0, 0.0, -8.0]", test.toString());
    }
    
    @Test
    public void getXTest() {
        Vector3D test = new Vector3D(80.3, 0, 0);
        assertEquals(80.3, test.getX(), 1e-12);
    }
    
    @Test
    public void getYTest() {
        Vector3D test = new Vector3D(0, 1, 0);
        assertEquals(1.0, test.getY(), 1e-12);
    }
    
    @Test
    public void getZTest() {
        Vector3D test = new Vector3D(0, 0, 18.3);
        assertEquals(18.3, test.getZ(), 1e-12);
    }
    
    @Test
    public void addTest1() {
        Vector3D v1 = new Vector3D(0, 0, 18.3);
        Vector3D v2 = new Vector3D(2.0, 5.0, 0);
        assertEquals(Vector3D.add(v1, v2), new Vector3D(2.0, 5.0, 18.3));
    }
    
    @Test
    public void addTest2() {
        Vector3D v1 = new Vector3D(0, 0, 18.3);
        Vector3D v2 = new Vector3D(-2.0, -5.0, 0);
        assertEquals(Vector3D.add(v1, v2), new Vector3D(-2.0, -5.0, 18.3));
    }

    @Test
    public void subtractTest1() {
        Vector3D v1 = new Vector3D(0, 0, 18.3);
        Vector3D v2 = new Vector3D(-2.0, -5.0, 0);
        assertEquals(Vector3D.subtract(v1, v2), new Vector3D(2.0, 5.0, 18.3));
    }
    
    @Test
    public void subtractTest2() {
        Vector3D v1 = new Vector3D(0, 0, 0);
        Vector3D v2 = new Vector3D(1.0, 1.0, 0);
        assertEquals(Vector3D.subtract(v1, v2), new Vector3D(-1.0, -1.0, 0.0));
    }

    @Test
    public void multiplyTest1() {
        assertEquals(Vector3D.multiply(2.0, new Vector3D(1, 0, 0)), new Vector3D(2, 0, 0));
    }
    
    @Test
    public void multiplyTest2() {
        assertEquals(Vector3D.multiply(3.0, new Vector3D(1, 0, 2)), new Vector3D(3, 0, 6));
    }

    @Test
    public void dotProductTest1() {
        assertEquals(Vector3D.dotProduct(new Vector3D(1.5, 2, 3), new Vector3D(4, 5, 6)), 34, 1e-12);
    }
    
    @Test
    public void dotProductTest2() {
        assertEquals(Vector3D.dotProduct(new Vector3D(1.5, 2, 3), new Vector3D(4, 1, 6)), 26, 1e-12);
    }
    
    @Test
    public void crossProductTest1() {
        assertEquals(Vector3D.crossProduct(new Vector3D(1.1, 2, 3), new Vector3D(5.9, 5, 6)), new Vector3D(-3, 11.1, -6.3));
    }
    
    @Test
    public void crossProductTest2() {
        assertEquals(Vector3D.crossProduct(new Vector3D(1.5, 2, 3), new Vector3D(4, 5, 0)), new Vector3D(-15, 12, -0.5));
    }

}
