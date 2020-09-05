package algorithms.mathengine

internal interface Expression {
  
  val startIndex: Int
  
  val endIndex: Int
  
  fun compute(): Number
}