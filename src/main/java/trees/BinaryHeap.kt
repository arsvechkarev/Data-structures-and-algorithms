package trees

import utils.Assert.assertThat

class BinaryHeap<E : Comparable<E>> {
  
  private val heap = ArrayList<E>()
  
  val size get() = heap.size
  
  fun add(e: E) {
    heap.add(e)
    swim(heap.lastIndex)
  }
  
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
    
    while (elemIndex > 0 && heap[elemIndex] < heap[parentIndex]) {
      swap(elemIndex, parentIndex)
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
        if (heap[left] < heap[elemIndex])
        swap(elemIndex, left)
        break
      }
      
      // Reached heap bounds, sinking finished
      if (left >= heap.size || right >= heap.size)
        break
      
      val smallestChildIndex = if (heap[left] <= heap[right]) left else right
      
      // Smallest child is equal to or bigger then the element, sinking finished
      if (heap[smallestChildIndex] >= heap[elemIndex])
        break
      
      swap(elemIndex, smallestChildIndex)
      elemIndex = smallestChildIndex
    }
  }
  
  private fun swap(i: Int, j: Int) {
    val nodeI = heap[i]
    val nodeJ = heap[j]
    heap[i] = nodeJ
    heap[j] = nodeI
  }
}