import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
	
	    Lista test = new Lista();
	    test.add("12").add("seven").add(3).add(123.45).add(1e12);
	    System.out.println("List: " + test);
	    System.out.println("First element: " + test.getFirst() + ", last element: " + test.getLast() + ", element at index 2: " + test.get(2));
	                    
	    test.delete(2);            
	    System.out.println("Removing element at index 2: " + test);
	    test.deleteAll();
	    System.out.println("Removing all elements: " + test);
	    
	    Liczba l1 = new LiczbaWymierna(3, 5);
	    Liczba l2 = new LiczbaOdZeraDoJeden(0.3333);
	    System.out.println("l1 = " + l1 + ", l2 = " + l2);
	    System.out.println("l1 + l2 = " + (l1.toDouble() + l2.toDouble()));
	    
	    ListaLiczb test2 = new ListaLiczb();
	    test2.add(l1).add(l2).add(new LiczbaWymierna(1, 6)).add(new LiczbaWymierna(-3, 122)).add(new LiczbaOdZeraDoJeden());
	    //test2.add(13);
	    System.out.println("List of numbers: " + test2);
	    System.out.println("Min: " + test2.min());
	    System.out.println("Max: " + test2.max());
	    
	    System.out.println("List as array of doubles: " + Arrays.toString(test2.array()));
	    test2.sort();
	    System.out.println("Sorted: " + test2);
	        
	}
	
}
