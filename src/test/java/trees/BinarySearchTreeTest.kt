package trees

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BinarySearchTreeTest {
  
  @Test
  fun `Contains test`() {
    val tree = BinarySearchTree<Int>()
    
    assertTrue(tree.add(6))
    assertTrue(tree.add(4))
    assertTrue(tree.add(5))
    assertTrue(tree.add(2))
    assertTrue(tree.add(8))
    assertFalse(tree.add(2))
    assertTrue(tree.add(10))
    assertTrue(tree.add(11))
    
    assertTrue(tree.size == 7)
    
    assertTrue(tree.contains(8))
    assertFalse(tree.contains(667))
  }
  
  @Test
  fun `Removing test`() {
    val tree = BinarySearchTree<Int>()
    
    tree.add(10)
    tree.add(7)
    tree.add(12)
    tree.add(4)
    tree.add(9)
    tree.add(11)
    tree.add(13)
    tree.add(3)
    tree.add(5)
    tree.add(14)
    tree.add(6)
    tree.add(2)
    
    assertTrue(tree.remove(13))
    assertFalse(tree.remove(13))
    assertTrue(tree.remove(14))
    assertTrue(tree.remove(7))
    assertTrue(tree.remove(3))
    
    assertTrue(tree.size == 8)
    
    tree.print()
  }
}