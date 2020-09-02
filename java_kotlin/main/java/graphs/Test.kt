package graphs


fun main() {
  
  val graph = mapOf(
    "A" to mapOf("B" to 4, "C" to 3),
    "B" to mapOf("A" to 4, "G" to 2),
    "C" to mapOf("A" to 3, "D" to 5, "E" to 8, "F" to 7),
    "D" to mapOf("C" to 5, "E" to 1),
    "E" to mapOf("C" to 8, "D" to 1, "H" to 6, "I" to 5),
    "F" to mapOf("C" to 7),
    "G" to mapOf("B" to 2, "H" to 1),
    "H" to mapOf("G" to 1, "I" to 4),
    "I" to mapOf("E" to 5, "H" to 4, "J" to 3, "K" to 9),
    "J" to mapOf("I" to 3, "K" to 2),
    "K" to mapOf("I" to 9, "J" to 2)
  )
  
  breadthFirstSearch(graph, "A", onNodeVisited = { key, dict ->
    println("$key -> $dict")
  })
}
