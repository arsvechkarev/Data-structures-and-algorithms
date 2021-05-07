package datastructures.concurrent;

import datastructures.lists.DoublyLinkedList;
import java.util.concurrent.atomic.AtomicInteger;

class BlockingQueue<T> {

  private final DoublyLinkedList<T> queue = new DoublyLinkedList<T>();

  private final Object LOCK = new Object();

  private final AtomicInteger size = new AtomicInteger(0);

  public T take() {
    synchronized (LOCK) {
      while (queue.size() == 0) {
        try {
          LOCK.wait();
        } catch (InterruptedException e) {
          throw new IllegalStateException();
        }
      }
      T t = queue.get(0);
      queue.remove(0);
      size.set(queue.size());
      return t;
    }
  }

  public void put(T element) {
    synchronized (LOCK) {
      queue.addLast(element);
      size.set(queue.size());
      LOCK.notifyAll();
    }
  }

  public int size() {
    return size.get();
  }
}

