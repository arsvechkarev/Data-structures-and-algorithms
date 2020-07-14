package lists;

public class DynamicArray<E> {

  private static final int GROWING_AMOUNT = 10;
  private static final int DEFAULT_CAPACITY = 20;

  private int size;

  private Object[] data;

  public DynamicArray() {
    this(DEFAULT_CAPACITY);
  }

  public DynamicArray(int size) {
    data = new Object[size];
  }

  public void add(E element) {
    if (size >= data.length) {
      Object[] newData = new Object[data.length + GROWING_AMOUNT];
      System.arraycopy(data, 0, newData, 0, data.length);
      data = newData;
    }
    data[size++] = element;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    return (E) data[index];
  }

  public void remove(int index) {
  }

  public int size() {
    return size;
  }
}
