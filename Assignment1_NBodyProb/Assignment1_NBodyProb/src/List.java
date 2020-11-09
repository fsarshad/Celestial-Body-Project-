// The LinkedList and ArrayList implement this interface
public interface List<T> {

    // add element at the end of the list
    public void add(T obj);

    // add element at the given position
    public void add(T obj, int pos);

    // returns element at the given position
    public T get(int pos);

    // removes element at the given position
    public T remove(int pos);

    public String toString();

    // returns the number of element
    public int size();
}
