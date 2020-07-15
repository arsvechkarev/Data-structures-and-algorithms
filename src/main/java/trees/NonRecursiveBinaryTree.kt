package trees

import trees.BinaryTree.Node
import utils.Recursion

class NonRecursiveBinaryTree<E : Comparable<E>> : BinaryTree<E> {
  
  private var _size = 0
  
  private var root: Node<E>? = null
  
  override val size get() = _size
  
  override fun add(element: E): Boolean {
    if (contains(element)) {
      return false
    }
    addNonRecursive(element)
    _size++
    return true
  }
  
  override fun contains(element: E): Boolean {
    if (root?.data == element) return true
    
    var current = root
    while (true) {
      if (current == null) return false
      if (current.data == element) return true
      if (element < current.data) {
        current = current.left
      } else {
        current = current.right
      }
    }
  }
  
  override fun remove(element: E): Boolean {
    if (!contains(element)) {
      return false
    }
    removeNonRecursive(element)
    _size--
    return true
  }
  
  private fun addNonRecursive(element: E) {
    if (root == null) {
      root = Node(element)
      return
    }
    
    var next = root
    var previous = root
    while (true) {
      next = if (element < next!!.data) next.left else next.right
      
      if (next == null) {
        // Reached the end
        if (element < previous!!.data) {
          previous.left = Node(element)
        } else {
          previous.right = Node(element)
        }
        return
      }
      
      previous = next
    }
  }
  
  private fun removeNonRecursive(element: E) {
    var prev = root
    var current = root
    while (true) {
      if (current == null || prev == null) {
        // Element hast't been found, that should not be happening
        throw IllegalStateException()
      }
      if (element == current.data) {
        if (current.left == null) {
          // 1st case: current node has only right subtree or no subtree at all
          if (current.data < prev.data) {
            prev.left = current.right
          } else {
            prev.right = current.right
          }
        } else if (current.right == null) {
          // 2nd case: current node has only left subtree
          if (current.data < prev.data) {
            prev.left = current.left
          } else {
            prev.right = current.left
          }
        } else {
          // 3rd case: current node has both left and right subtree
          val pair = digRight(current.left!!)
          
          // Setting current data to the rightmost node in the left subtree
          current.data = pair.first.data
          
          if (pair.first.data > pair.second.data) {
            pair.second.right = pair.first.right
          } else if (pair.first === pair.second) {
            current.left = pair.first.left
          }
        }
        break
      }
      
      prev = current
      if (element < current.data) {
        current = current.left
      } else {
        current = current.right
      }
    }
  }
  
  /**
   * Finds the most rightmost node relative to a given [node] and returns pair of found node and its successor
   */
  private fun digRight(node: Node<E>): Pair<Node<E>, Node<E>> {
    var prev = node
    var current = node
    while (current.right != null) {
      prev = current
      current = current.right!!
    }
    return Pair(current, prev)
  }
  
  override fun forEachInorder(action: (E) -> Unit) {
    Recursion.forEachInorder(root, action)
  }
}