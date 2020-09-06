package algorithms

fun depthFirstSearch(
  graph: Map<String, Map<String, Int>>,
  startNode: String,
  onNodeVisited: (String, Map<String, Int>) -> Unit = { _, _ -> }
) {
  val visited = mutableListOf<String>()
  dfsRecursive(graph, startNode, visited, onNodeVisited)
}

private fun dfsRecursive(
  graph: Map<String, Map<String, Int>>,
  currentNode: String,
  visited: MutableList<String>,
  onNodeVisited: (String, Map<String, Int>) -> Unit
) {
  if (currentNode in visited) {
    return
  }
  visited.add(currentNode)
  onNodeVisited(currentNode, graph.getValue(currentNode))
  val edges = graph.getValue(currentNode)
  for (key in edges.keys) {
    dfsRecursive(graph, key, visited, onNodeVisited)
  }
}