package datastructures.graphs

import java.util.function.Predicate

/**
 * Represents an unweighted directed acyclic graph
 */
class DirectedAcyclicGraph<E> {
  
  private val roots = HashSet<Node<E>>()
  private val nodes = HashMap<E, Node<E>>()
  
  /**
   * Adds node with [parents] and [children] to the graph. If [parents] or [children] are not in
   * the graph already, automatically creates them
   */
  fun addNode(data: E, parents: List<E> = emptyList(), children: List<E> = emptyList()) {
    val node = nodes.computeIfAbsent(data) { Node(data) }
    linkParents(node, parents)
    linkChildren(node, children)
    checkForCycles()
  }
  
  /**
   * Traverse edges in topological ordering
   */
  fun visitNodesInTopologicalOrder(visitor: (E) -> Unit) {
    val visited = ArrayList<Node<E>>()
    roots.forEach { node ->
      visited.add(node)
      visitor.invoke(node.data)
      for (child in node.children) {
        visitNodeInOrder(child, visited, visitor)
      }
    }
  }
  
  private fun linkParents(node: Node<E>, parentsData: List<E>) {
    if (parentsData.isEmpty()) {
      val parents = findParentsForNode(node)
      if (parents.isNotEmpty()) {
        parents.forEach { parent ->
          node.parents.add(parent)
          parent.children.add(node)
        }
      } else {
        roots.add(node)
        nodes[node.data] = node
      }
    }
    for (parent in parentsData) {
      val existingParent = nodes[parent]
      if (existingParent != null) {
        node.parents.add(existingParent)
        existingParent.children.add(node)
      } else {
        val newParentNode = Node(parent)
        roots.add(newParentNode)
        newParentNode.children.add(node)
        node.parents.add(newParentNode)
        nodes[parent] = newParentNode
      }
    }
  }
  
  private fun linkChildren(node: Node<E>, childrenData: List<E>) {
    if (childrenData.isEmpty()) {
      val children = findChildrenForNode(node)
      if (children.isNotEmpty()) {
        children.forEach { child ->
          node.children.add(child)
          child.parents.add(node)
        }
      }
    }
    for (childData in childrenData) {
      val child = nodes[childData]
      if (child != null) {
        node.children.add(child)
        child.parents.add(node)
        roots.remove(child)
      } else {
        val newChildNode = Node(childData)
        newChildNode.parents.add(node)
        node.children.add(newChildNode)
        nodes[childData] = newChildNode
      }
    }
  }
  
  private fun findParentsForNode(node: Node<E>): List<Node<E>> {
    val parents = ArrayList<Node<E>>()
    val predicate = Predicate<Node<E>> { element -> element.children.contains(node) }
    roots.forEach { root -> tryFindNodes(root, ArrayList(), parents, predicate) }
    return parents
  }
  
  private fun findChildrenForNode(node: Node<E>): List<Node<E>> {
    val children = ArrayList<Node<E>>()
    val predicate = Predicate<Node<E>> { element -> element.parents.contains(node) }
    roots.forEach { root -> tryFindNodes(root, ArrayList(), children, predicate) }
    return children
  }
  
  private fun tryFindNodes(
    node: Node<E>,
    visited: ArrayList<Node<E>>,
    found: ArrayList<Node<E>>,
    predicate: Predicate<Node<E>>
  ) {
    if (visited.contains(node)) return
    visited.add(node)
    if (predicate.test(node)) {
      found.add(node)
    }
    for (child in node.children) {
      tryFindNodes(child, visited, found, predicate)
    }
  }
  
  private fun visitNodeInOrder(node: Node<E>, visited: ArrayList<Node<E>>, visitor: (E) -> Unit) {
    if (visited.containsAll(node.parents) && !visited.contains(node)) {
      visited.add(node)
      visitor.invoke(node.data)
      for (child in node.children) {
        visitNodeInOrder(child, visited, visitor)
      }
    }
  }
  
  private fun checkForCycles() {
    if (roots.isEmpty() && nodes.isNotEmpty()) {
      // Roots are empty, but nodes not => there is a cycle somewhere
      // In this case checking for cycle from arbitrary node
      val key = nodes.keys.toList()[0]
      checkForCycleFromRoot(nodes.getValue(key))
    } else {
      roots.forEach(::checkForCycleFromRoot)
    }
  }
  
  private fun checkForCycleFromRoot(root: Node<E>) {
    val visited = ArrayList<Node<E>>()
    visited.add(root)
    for (child in root.children) {
      checkForCycle(child, visited)
    }
  }
  
  private fun checkForCycle(node: Node<E>, visited: ArrayList<Node<E>>) {
    if (visited.contains(node)) {
      error("Found cycle for nodes: ${getCycledNodes(visited)}")
    }
    if (node.children.isNotEmpty()) {
      visited.add(node)
      for (child in node.children) {
        checkForCycle(child, visited)
      }
      visited.remove(node)
      visited.removeAll(node.children)
    }
  }
  
  private fun getCycledNodes(visited: ArrayList<Node<E>>): String {
    val builder = StringBuilder(visited.size)
    visited.forEach { node ->
      builder.append(node.data).append("->")
    }
    builder.append("${visited[0].data}...")
    return builder.toString()
  }
  
  class Node<E>(val data: E) {
    val parents = HashSet<Node<E>>()
    val children = HashSet<Node<E>>()
  }
}
