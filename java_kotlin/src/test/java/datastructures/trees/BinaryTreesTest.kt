package datastructures.trees

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BinaryTreesTest {
  
  private var tree: BinaryTree<Int>? = null
  
  @Before
  fun setup() {
    tree = RecursiveBinaryTree()
  }
  
  @After
  fun shutdown() {
    tree = null
  }
  
  @Test
  fun `Contains elements`() {
    val tree = tree!!
    
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
  
    tree.assertInOrder()
  }
  
  @Test
  fun `Removing elements 1`() {
    val tree = tree!!
    
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
    tree.assertInOrder()
  }
  
  @Test
  fun `Removing elements 2`() {
    val tree = tree!!
    tree.add(15)
    tree.add(12)
    tree.add(13)
    tree.add(18)
    tree.add(16)
    
    assertTrue(tree.remove(18))
    assertTrue(tree.remove(12))
    assertTrue(tree.size == 3)
    tree.assertInOrder()
  }
  
  @Test
  fun `Removing elements 3`() {
    val tree = tree!!
    tree.add(12)
    tree.add(15)
    tree.add(13)
    tree.add(20)
    tree.add(25)
    
    assertTrue(tree.remove(15))
    assertTrue(tree.size == 4)
    tree.assertInOrder()
  }
  
  private fun <E : Comparable<E>> BinaryTree<E>.assertInOrder() {
    var prev: E? = null
    forEachInorder { element ->
      if (prev == null) {
        prev = element
      } else {
        assertTrue(prev!! < element)
        prev = element
      }
    }
  }
}