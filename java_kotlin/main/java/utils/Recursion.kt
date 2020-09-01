package utils

import datastructures.trees.BinaryTree.Node

object Recursion {
  
  fun <E> forEachInorder(node: Node<E>?, action: (E) -> Unit) {
    if (node == null) return
    forEachInorder(node.left, action)
    action(node.data)
    forEachInorder(node.right, action)
  }
}