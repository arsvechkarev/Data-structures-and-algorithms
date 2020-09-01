package datastructures.trees

import datastructures.trees.BinaryTree.Node
import utils.Recursion

class RecursiveBinaryTree<E : Comparable<E>> : BinaryTree<E> {
  
  private var _size = 0
  
  private var root: Node<E>? = null
  
  override val size get() = _size
  
  override fun add(element: E): Boolean {
    if (contains(element)) {
      return false
    }
    root = add(root, element)
    _size++
    return true
  }
  
  override fun contains(element: E): Boolean {
    return contains(root, element)
  }
  
  override fun remove(element: E): Boolean {
    if (!contains(element)) {
      return false
    }
    root = remove(root, element)
    _size--
    return true
  }
  
  private fun add(current: Node<E>?, element: E): Node<E>? {
    var node = current
    if (node == null) {
      node = Node(element)
    } else {
      if (element < node.data) {
        node.left = add(node.left, element)
      } else {
        node.right = add(node.right, element)
      }
    }
    return node
  }
  
  private fun contains(node: Node<E>?, element: E): Boolean {
    if (node == null) return false
    if (node.data == element) return true
    
    if (element < node.data) {
      return contains(node.left, element)
    } else {
      return contains(node.right, element)
    }
  }
  
  private fun remove(node: Node<E>?, element: E): Node<E>? {
    if (node == null) {
      return node
    }
    if (element == node.data) {
      if (node.left == null) {
        return node.right
      }
      if (node.right == null) {
        return node.left
      }
      val maxNodeOnTheLeft = digRight(node.left!!)
      node.data = maxNodeOnTheLeft.data
      node.left = remove(node.left, maxNodeOnTheLeft.data)
    } else {
      if (element < node.data) {
        node.left = remove(node.left, element)
      } else {
        node.right = remove(node.right, element)
      }
    }
    return node
  }
  
  // Find the maximum node in the left subtree
  private fun digRight(current: Node<E>): Node<E> {
    var node = current
    while (node.right != null) {
      node = node.right!!
    }
    return node
  }
  
  override fun forEachInorder(action: (E) -> Unit) {
    Recursion.forEachInorder(root, action)
  }
}