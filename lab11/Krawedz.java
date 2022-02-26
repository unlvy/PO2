
public class Krawedz<T> {

	protected Wierzcholek<T> verticeA;
	protected Wierzcholek<T> verticeB;

	public Krawedz(T a, T b) {
		verticeA = new Wierzcholek<T>(a);
		verticeB = new Wierzcholek<T>(b);
	}

	public Wierzcholek<T>  getA() { return verticeA; }
	public Wierzcholek<T>  getB() { return verticeB; }

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		try {
			Krawedz<T> e = (Krawedz<T>)o;
			return e.getA().equals(getA()) && e.getB().equals(getB());
		} catch (ClassCastException e) {
			return false;
		}			
	}

}