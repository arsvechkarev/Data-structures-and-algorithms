package lists;

public class SinglyLinkedList<E> {

  private Node head = null;

  private int size;

  public void add(E element) {
    if (head == null) {
      head = new Node(element);
    } else {
      Node last = head;
      for (int i = 0; i < size - 1; i++) {
        last = last.next;
      }
      last.next = new Node(element);
    }
    size++;
  }

  public E get(int index) {
    Node last = head;
    for (int i = 0; i < index; i++) {
      last = last.next;
    }
    return last.data;
  }

  public void remove(int index) {
    if (index == 0) {
      head = head.next;
    } else {
      Node beforeRemoving = head;
      for (int i = 0; i < index - 1; i++) {
        beforeRemoving = beforeRemoving.next;
      }
      Node removing = beforeRemoving.next;
      beforeRemoving.next = removing.next;
    }
    size--;
  }

  public void remove(E element) {
    if (element == head) {
      head = head.next;
    } else {
      Node prev = head;
      Node next = head.next;
      while (next != null) {
        if (next.data.equals(element)) {
          prev.next = next.next;
          size--;
          return;
        }
        prev = next;
        next = next.next;
      }
    }
  }

  public int size() {
    return size;
  }

  public void printElements() {
    Node node = head;
    for (int i = 0; i < size; i++) {
      System.out.println(node.data);
      node = node.next;
    }
  }

  private class Node {

    E data;
    Node next;

    Node(E data) {
      this.data = data;
    }

    @Override
    public String toString() {
      String hasNext = (next != null) ? "yes" : "no";
      return "Node{" +
          "data=" + data +
          ", hasNext = " + hasNext + "}";
    }
  }
}
