// ArrayList is backed by a Java array

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 8;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }

    // add element at the end of the list
    @Override
    public void add(T obj) {
        checkCapacity();
        array[size] = obj;
        size++;
    }

    // checks the capacity
    private void checkCapacity(){
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    // add element at the given position
    @Override
    public void add(T obj, int pos) {
        checkCapacity();
        for (int i = size; i > pos; i--){
            array[i] = array[i - 1];
        }
        array[pos] = obj;
    }

    // returns element at the given position
    @Override
    public T get(int pos) {
        return (T) array[pos];
    }

    // removes element at the given position
    @Override
    public T remove(int pos) {
        // shift every element after the position to the left.
        T result = get(pos);
        for(int i = pos; i + 1 < size; i++){
            array[i] = array[i + 1];
        }
        size --;
        return result;
    }

    @Override
    // returns the number of elements in this list.
    public int size() {
        return size;
    }

    @Override
    // Gives a string representation of the contents of array.
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            s.append(array[i]);
            if (i+1 < size) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}

