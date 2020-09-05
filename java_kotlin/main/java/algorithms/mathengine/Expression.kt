package algorithms.mathengine

interface Expression {
  
  val startIndex: Int
  
  val endIndex: Int
  
  fun compute(): Number
}