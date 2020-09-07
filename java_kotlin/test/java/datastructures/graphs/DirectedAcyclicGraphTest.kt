package datastructures.graphs

import org.junit.Test

class DirectedAcyclicGraphTest {
  
  @Test
  fun `Topological ordering 1`() {
    buildDAG<String> {
      node("A")
      node("B")
      node("C", parents = listOf("A", "B"))
      node("D", parents = listOf("C"))
      
      topologicalOrdering().apply {
        check { "C" isAfter "B" }
        check { "C" isAfter "A" }
        check { "D" isAfter "C" }
        check { group("A", "B") isBefore group("C", "D") }
      }
    }
  }
  
  @Test
  fun `Topological ordering 2`() {
    buildDAG<String> {
      node("A")
      node("B")
      node("C", parents = listOf("A", "B"))
      node("D", parents = listOf("C"))
      node("E", parents = listOf("C"))
      node("F", parents = listOf("D", "E"))
      node("G", parents = listOf("F"))
      node("H", parents = listOf("F"))
      node("I", parents = listOf("F"))
      
      topologicalOrdering().apply {
        check { group("A", "B") isBefore "C" }
        check { group("D", "E") isAfter "C" }
        check { group("D", "E") isBefore "F" }
        check { group("G", "H", "I") isAfter "F" }
      }
    }
  }
  
  @Test
  fun `Topological ordering 3`() {
    buildDAG<String> {
      node("A", children = listOf("B", "C"))
      node("B", parents = listOf("C"))
      node("D", parents = listOf("B"))
      node("E", parents = listOf("C"))
  
      topologicalOrdering().apply {
        check { "A" isBefore "B" }
        check { "C" isBefore "B" }
        check { "D" isAfter "B" }
        check { "E" isAfter "C" }
      }
    }
  }
  
  @Test(expected = IllegalStateException::class)
  fun `Detecting cycles 1`() {
    buildDAG<String> {
      node("A", children = listOf("B"))
      node("B", children = listOf("A"))
    }
  }
  
  @Test(expected = IllegalStateException::class)
  fun `Detecting cycles 2`() {
    buildDAG<String> {
      node("B", parents = listOf("A"))
      node("A", parents = listOf("B"))
    }
  }
  
  @Test(expected = IllegalStateException::class)
  fun `Detecting cycles 3`() {
    buildDAG<String> {
      node("A", children = listOf("B"))
      node("B")
      node("C", parents = listOf("B"))
      node("D", parents = listOf("C"), children = listOf("B"))
    }
  }
}