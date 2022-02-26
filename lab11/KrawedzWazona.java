
public class KrawedzWazona<T> extends Krawedz<T> {

	protected double value; 

	public KrawedzWazona(T a, T b, double value) {
		super(a, b);
		this.value = value;
	}

}