
public class Wierzcholek<T> {

	private T id;

	public Wierzcholek(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		try {
			Wierzcholek<T> w = (Wierzcholek<T>)o;
			return w.id == id;
		} catch (ClassCastException e) {
			return false;
		}			
	}

}