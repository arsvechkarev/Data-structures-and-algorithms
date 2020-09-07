package datastructures.graphs

import org.junit.Assert

fun <E> buildDAG(block: DirectedAcyclicGraph<E>.() -> Unit): DirectedAcyclicGraph<E> {
  return DirectedAcyclicGraph<E>().apply(block)
}

fun <E> DirectedAcyclicGraph<E>.node(e: E, parents: List<E> = emptyList(), children: List<E> = emptyList()) {
  addNode(e, parents, children)
}

fun <E> DirectedAcyclicGraph<E>.topologicalOrdering(): List<E> {
  val order = ArrayList<E>()
  visitNodesInTopologicalOrder { element -> order.add(element) }
  return order
}

fun <E> List<E>.check(block: OrderChecker<E>.() -> Unit) {
  OrderChecker(this).apply(block)
}

class OrderChecker<E>(private val list: List<E>) {
  
  infix fun Group<E>.isBefore(other: E) {
    Assert.assertTrue(this.maxIndex(list) < list.indexOf(other))
  }
  
  infix fun Group<E>.isAfter(other: E) {
    Assert.assertTrue(this.minIndex(list) > list.indexOf(other))
  }
  
  infix fun Group<E>.isBefore(other: Group<E>) {
    Assert.assertTrue(this.maxIndex(list) < other.minIndex(list))
  }
  
  infix fun Group<E>.isAfter(other: Group<E>) {
    Assert.assertTrue(this.minIndex(list) > other.maxIndex(list))
  }
  
  infix fun E.isBefore(other: E) {
    Assert.assertTrue(list.indexOf(this) < list.indexOf(other))
  }
  
  infix fun E.isAfter(other: E) {
    Assert.assertTrue(list.indexOf(this) > list.indexOf(other))
  }
  
  fun group(vararg elements: E): Group<E> {
    return Group(elements.toList())
  }
}

class Group<E>(private val elements: List<E>) {
  
  fun maxIndex(ordering: List<E>): Int {
    var maxIndex = Int.MIN_VALUE
    elements.forEach { e -> if (ordering.indexOf(e) > maxIndex) maxIndex = ordering.indexOf(e) }
    require(maxIndex != -1) { "Found no max index" }
    return maxIndex
  }
  
  fun minIndex(ordering: List<E>): Int {
    var minIndex = Int.MAX_VALUE
    elements.forEach { e -> if (ordering.indexOf(e) < minIndex) minIndex = ordering.indexOf(e) }
    require(minIndex != -1) { "Found no min index" }
    return minIndex
  }
}
