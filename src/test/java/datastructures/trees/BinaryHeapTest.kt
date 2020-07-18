package datastructures.trees

import org.junit.Assert.assertTrue
import org.junit.Test

class BinaryHeapTest {
  
  @Test
  fun `Adding and polling elements`() {
    val heap = BinaryHeap<Int>()
    
    assertTrue(heap.size == 0)
    
    heap.add(5)
    heap.add(1)
    heap.add(3)
    heap.add(2)
    heap.add(5)
    heap.add(6)
    heap.add(4)
    
    var element = heap.poll()
   
    while (true) {
      if (heap.size == 0)
        break
      
      val element2 = heap.poll()
      assertTrue(element <= element2)
      element = element2
    }
  }
}