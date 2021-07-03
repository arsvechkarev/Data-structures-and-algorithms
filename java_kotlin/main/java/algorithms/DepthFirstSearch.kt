package algorithms

fun depthFirstSearch(
  graph: Map<String, Map<String, Int>>,
  startNode: String,
  onNodeVisited: (String, Map<String, Int>) -> Unit = { _, _ -> }
) {
  val visited = HashSet<String>()
  dfsRecursive(graph, startNode, visited, onNodeVisited)
}

private fun dfsRecursive(
  graph: Map<String, Map<String, Int>>,
  currentNode: String,
  visited: HashSet<String>,
  onNodeVisited: (String, Map<String, Int>) -> Unit
) {
  if (currentNode in visited) {
    return
  }
  visited.add(currentNode)
  val edges = graph.getValue(currentNode)
  onNodeVisited(currentNode, edges)
  for (key in edges.keys) {
    dfsRecursive(graph, key, visited, onNodeVisited)
  }
}