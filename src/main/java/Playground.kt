import trees.BinarySearchTree

fun main() {

  val tree = BinarySearchTree<String>()
  
  tree.add("A")
  tree.add("B")
  tree.add("C")
  
  tree.printInorder()
}
