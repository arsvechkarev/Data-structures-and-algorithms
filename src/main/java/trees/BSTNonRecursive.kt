package trees

class BSTNonRecursive<E : Comparable<E>> {
  
  private var _size = 0
  
  private var root: Node? = null
  
  val size get() = _size
  
  fun add(element: E): Boolean {
    if (contains()) {
      return false
    }
  
    addNonRecursive(element)
    _size++
    return true
  }
  
  fun contains(): Boolean {
    return false
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
  
  inner class Node(
    val data: E,
    var left: Node? = null,
    var right: Node? = null
  )
}