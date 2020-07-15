package trees

/**
 * Represents binary search tree
 */
interface BinaryTree<E : Comparable<E>> {
  
  /**
   * Returns amount of elements in the tree
   */
  val size: Int
  
  /** Returns true if [element] is in the tree, false otherwise */
  fun contains(element: E): Boolean
  
  /**
   * Adds an [element] to the tree
   *
   * Returns true if element can be added, false otherwise
   */
  fun add(element: E): Boolean
  /**
   * Removes an [element] from the tree
   *
   * Returns true if element can be removed, false otherwise
   */
  fun remove(element: E): Boolean
  
  fun print()
}