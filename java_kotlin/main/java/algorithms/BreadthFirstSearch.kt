package algorithms

import datastructures.lists.DoublyLinkedList

fun breadthFirstSearch(
  graph: Map<String, Map<String, Int>>,
  startNode: String,
  onNodeVisited: (String, Map<String, Int>) -> Unit = { _, _ -> }
) {
  val queue = DoublyLinkedList<String>()
  val visited = HashSet<String>()
  
  queue.add(startNode)
  visited.add(startNode)
  
  while (queue.size() != 0) {
    val current = queue[0]
    queue.remove(0)
    
    onNodeVisited(current, graph.getValue(current))
    
    val neighbours = graph.getValue(current)
    for (node in neighbours) {
      if (!visited.contains(node.key)) {
        queue.add(node.key)
        visited.add(node.key)
      }
    }
  }
}