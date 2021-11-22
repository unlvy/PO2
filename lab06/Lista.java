/** class representing list that can store any object */
public class Lista {

    /** current size */
    protected int size;
    /** max size */
    protected int maxSize;
    /** array storing elements */
    protected Object[] elements;
    
    { size = 0; maxSize = 100; elements = initArray(maxSize); }
    
    /** initializes array */
    protected Object[] initArray(int size) {
        return new Object[size];
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (size > 0) result.append(elements[size-1]);
        for (int i = size - 2; i >= 0; i--) {
            result.append(", ").append(elements[i]);
        }
        return result.append("]").toString();
    }
    
    /**
     * adds new element to beginning of the list
     * @param o new element
     * @return this
     * */
    public Lista add(Object o) {
        if (size >= maxSize) {
            Object[] temp = initArray(maxSize * 2);
            System.arraycopy(elements, 0, temp, 0, maxSize);
            maxSize *= 2;
            elements = temp;
        }
        elements[size++] = o;
        return this;
    }
    
    /**
     * returns current size of list
     * @return size
     * */
    public int getSize() { return size; }
    
    /**
     * returns head of the list (null if list is empty)
     * @return head
     * */
    public Object getFirst() { 
        if (size == 0) return null; 
        return elements[size-1]; 
    }
    
    /**
     * returns tail of the list (null if list is empty)
     * @return tail
     * */
    public Object getLast() { 
        if (size == 0) return null; 
        return elements[0];
    };
    
    /**
     * returns element at given place (null if index is incorrect)
     * @return element
     * */
    public Object get(int index) {
        if (index < 0 || index > size - 1) return null;
        return elements[size - 1 - index];
    }
    
    /**
     * deletes element at given index
     * */
    public void delete(int index) {
        if (index >= 0 && index <= size - 1) {
            index = size - 1 - index;
            Object[] temp = initArray(maxSize);
            System.arraycopy(elements, 0, temp, 0, index);
            System.arraycopy(elements, index + 1, temp, index, size - index - 1);
            elements = temp;
            size--;
        }
    }

    /**
     * deletes all elements
     * */
    public void deleteAll() {
        elements = initArray(maxSize);
        size = 0;
    }

}
