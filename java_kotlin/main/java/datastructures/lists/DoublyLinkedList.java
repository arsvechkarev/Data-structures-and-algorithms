package datastructures.lists;

import static utils.Assert.assertNonNull;
import static utils.Assert.assertThat;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class DoublyLinkedList<E> implements Iterable<E> {

  private Node head = null;
  private Node tail = null;
  private int size;

  public void add(@NotNull E element) {
    addLast(element);
  }

  public void addFirst(@NotNull E element) {
    assertNonNull(element);
    if (size == 0) {
      head = tail = new Node(element);
    } else {
      Node newNode = new Node(element);
      newNode.next = head;
      head.previous = newNode;
      head = newNode;
    }
    size++;
  }

  public void addLast(@NotNull E element) {
    assertNonNull(element);
    if (size == 0) {
      head = tail = new Node(element);
    } else {
      Node newNode = new Node(element);
      newNode.previous = tail;
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  @NotNull
  public E get(int index) {
    assertThat(index >= 0 && index < size,
        () -> "Wrong index: index = " + index + ", size = " + size + "");
    Node current;
    if (index < size / 2) {
      current = head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    } else {
      current = tail;
      for (int i = size - 1; i > index; i--) {
        current = current.previous;
      }
    }
    return current.data;
  }

  public void remove(int index) {
    assertThat(index >= 0 && index < size,
        () -> "Wrong index: index = " + index + ", size = " + size + "");
    if (index == 0) {
      if (size == 1) {
        // Removing both head and tail
        head = null;
        tail = null;
      } else {
        head = head.next;
        head.previous.data = null;
        head.previous = null;
      }
    } else if (index == size - 1) {
      // Removing tail
      tail = tail.previous;
      tail.next.data = null;
      tail.next = null;
    } else {
      if (index > size / 2) {
        Node afterRemoving = tail;
        for (int i = size - 1; i > index + 1; i--) {
          afterRemoving = afterRemoving.previous;
        }
        Node preRemoving = afterRemoving.previous.previous;
        preRemoving.next = afterRemoving;
        afterRemoving.previous = preRemoving;
      } else {
        Node preRemoving = head;
        for (int i = 0; i < index - 1; i++) {
          preRemoving = preRemoving.next;
        }
        Node removing = preRemoving.next;
        Node afterRemoving = removing.next;
        if (afterRemoving != null) {
          preRemoving.next = afterRemoving;
          afterRemoving.previous = preRemoving;
        }
      }
    }
    size--;
  }

  public void remove(@NotNull E element) {
    assertNonNull(element);
    if (head.data.equals(element)) {
      remove(0);
    } else if (tail.data.equals(element)) {
      remove(size - 1);
    } else {
      Node current = head;
      while (current.next != null) {
        if (current.data.equals(element)) {
          Node previous = current.previous;
          Node next = current.next;
          previous.next = next;
          next.previous = previous;
          size--;
          return;
        }
        current = current.next;
      }
    }
  }

  public void clear() {
    for (int i = 0; i < size; i++) {
      remove(0);
    }
  }

  public int size() {
    return size;
  }

  @NotNull
  @Override
  public Iterator<E> iterator() {
    return new LinkedListIterator(head);
  }

  private class Node {

    E data;
    Node next;
    Node previous;

    Node(E data) {
      this.data = data;
    }
  }

  private class LinkedListIterator implements Iterator<E> {

    private Node current;

    LinkedListIterator(Node head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public E next() {
      E data = current.data;
      current = current.next;
      return data;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}