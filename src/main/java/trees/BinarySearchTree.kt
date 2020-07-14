package trees

class BinarySearchTree<E : Comparable<E>> {
  
  private var size = 0
  
  private var root: Node? = null
  
  fun add(element: E): Boolean {
    if (contains(element)) {
      return false
    }
    
    root = add(root, element)
    size++
    return true
  }
  
  fun contains(element: E): Boolean {
    return false
  }
  
  fun printInorder() {
    printInorder(root)
  }
  
  private fun add(current: Node?, element: E): Node? {
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
  
  private fun printInorder(node: Node?) {
    if (node == null) {
      return
    }
    
    printInorder(node.left)
    println(node.data)
    printInorder(node.right)
  }
  
  inner class Node(
    val data: E,
    var left: Node? = null,
    var right: Node? = null
  )
}