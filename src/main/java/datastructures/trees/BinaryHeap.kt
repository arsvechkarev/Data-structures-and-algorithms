package datastructures.trees

import utils.Assert.assertThat
import utils.swap

class BinaryHeap<E : Comparable<E>>(
  private val isMinHeap: Boolean = true
) {
  
  private val heap = ArrayList<E>()
  
  val size get() = heap.size
  
  // Adding an element to the heap, O(log(n))
  fun add(e: E) {
    heap.add(e)
    swim(heap.lastIndex)
  }
  
  // Removing element with the smallest priority, O(log(n))
  fun poll(): E {
    return removeAt(0)
  }
  
  fun removeAt(index: Int): E {
    assertThat(index >= 0 && index < heap.size) { "Wrong index: index = $index, size = $size" }
    if (index == heap.lastIndex) {
      // Removing last element
      val last = heap.last()
      heap.removeAt(index)
      return last
    }
    val removed = heap[index]
    heap[index] = heap.last()
    heap.removeAt(heap.lastIndex)
    sink(index) 
    swim(index)
    return removed
  }
  
  private fun swim(index: Int) {
    var elemIndex = index
    var parentIndex = (elemIndex - 1) / 2
    
    while (elemIndex > 0 && heap[elemIndex] toLeftOf heap[parentIndex]) {
      heap.swap(elemIndex, parentIndex)
      elemIndex = parentIndex
      parentIndex = (elemIndex - 1) / 2
    }
  }
  
  private fun sink(index: Int) {
    var elemIndex = index
    var left: Int
    var right: Int
    while (true) {
      left = (2 * elemIndex) + 1
      right = (2 * elemIndex) + 2
      if (heap.size == 2) {
        // Only one node and its left child left
        if (heap[left] toLeftOf heap[elemIndex])
          heap.swap(elemIndex, left)
        return
      }
      // Reached heap bounds, sinking finished
      if (right >= heap.size) {
        return
      }
      val smallestChildIndex = if (heap[left] toLeftOf heap[right]) left else right
      if (heap[smallestChildIndex] toRightOf heap[elemIndex]) {
        // Smallest child is equal to or bigger then the element, sinking finished
        return
      }
      heap.swap(elemIndex, smallestChildIndex)
      elemIndex = smallestChildIndex
    }
  }
  
  private infix fun E.toLeftOf(other: E): Boolean {
    if (isMinHeap) {
      return this <= other
    } else {
      return this > other
    }
  }
  
  private infix fun E.toRightOf(other: E): Boolean {
    if (isMinHeap) {
      return this >= other
    } else {
      return this < other
    }
  }
}