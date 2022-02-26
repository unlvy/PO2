
import java.util.ArrayList;

public class Graf<T1, T2 extends Krawedz<T1>> {

	private ArrayList<Wierzcholek<T1>> vertices;
	private ArrayList<Krawedz<T1>> edges;

	{ 
		vertices = new ArrayList<Wierzcholek<T1>>(); 
		edges = new ArrayList<Krawedz<T1>>(); 
	}

	public void dodajWierzcholek(Wierzcholek<T1> vertice) {
		for (Wierzcholek<T1> v : vertices) {
			if (v.equals(vertice)) {
				throw new WierzcholekJuzIstniejeException();
			}
		}
		vertices.add(vertice);
	}

	public int rzad() {
		return vertices.size();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (rzad() > 0) { s.append("\n"); }		
		for (Wierzcholek<T1> vertice : vertices) {
			s.append(vertice.getId()).append(" --");
			for (Krawedz<T1> edge : edges) {
				if (edge.getA().equals(vertice)) {
					s.append(" ").append(((Wierzcholek<T1>)edge.getB()).getId());
				} else if (edge.getB().equals(vertice)) {
					s.append(" ").append(((Wierzcholek<T1>)edge.getA()).getId());
				}
			}
			s.append("\n");
		}
		return s.toString();
	}

	public void dodajKrawedz(T2 edge) {
		for (Krawedz<T1> e : edges) {
			if (e.equals(edge)) {
				throw new KrawedzJuzIstniejeException();
			}
		}
		edges.add(edge);
	}

	public int rozmiar() {
		return edges.size();
	}

}