
/**
 * class representing 3-dimensional vector
 * */
public class Vector3D {
    
    /** x value */
    double x;
    /** y value */
    double y;
    /** z value */
    double z;
    
    /**
     * constructor
     * */
    Vector3D() {
        set(0.0, 0.0, 0.0);
    }
        
    /**
     * constructor
     * */
    Vector3D(double x, double y, double z) {
        set(x, y ,z);
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        Vector3D v = (Vector3D)o;
        return (Math.abs(v.x - x) < 1e-12) 
               && (Math.abs(v.y - y) < 1e-12) 
               && (Math.abs(v.z - z) < 1e-12);
    }
    
    /**
     * sets vector coordinate values
     * @param x new value of x
     * @param y new value of y
     * @param z new value of z
     * */
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * sets x 
     * @param x new value of x
     * */
    public void setX(double x) { this.x = x; }
    
    /**
     * sets y 
     * @param y new value of y
     * */
    public void setY(double y) { this.y = y; }
    
    /**
     * sets z 
     * @param z new value of z
     * */
    public void setZ(double z) { this.z = z; }
    
    /**
     * returns value of x
     * @return x
     * */
    public double getX() { return x; }
    
    /**
     * returns value of y
     * @return y
     * */
    public double getY() { return y; }
    
    /**
     * returns value of z
     * @return z
     * */
    public double getZ() { return z; }
    
    /**
     * adds two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return result
     * */
    public static Vector3D add(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    /**
     * subtracts two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return result
     * */
    public static Vector3D subtract(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    
    /**
     * multiplies vector by value
     * @param multipler 
     * @param v vector
     * @return result
     * */
    public static Vector3D multiply(double multipler, Vector3D v) {
        return new Vector3D(multipler * v.x, multipler * v.y, multipler * v.z);
    }
    
    /**
     * performs dot product
     * @param v1 first vector
     * @param v2 second vector
     * @return result
     * */
    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    
    /**
     * performs cross product
     * @param v1 first vector
     * @param v2 second vector
     * @return result
     * */
    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.y * v2.z - v1.z * v2.y, - v1.x * v2.z + v1.z * v2.x, v1.x * v2.y - v1.y * v2.x);
    }
    
}
