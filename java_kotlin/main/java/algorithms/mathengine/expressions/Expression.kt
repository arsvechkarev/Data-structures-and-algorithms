package algorithms.mathengine.expressions

import algorithms.mathengine.Number

/** Represents a mathematical expression, such as "5", "-2", "9+8", "(1 * 3)" ect. */
internal interface Expression {
  
  /** Start index of expression (inclusive) */
  val startIndex: Int
  
  /** End index of expression (exclusive) */
  val endIndex: Int
  
  /** Computes expression and returns result */
  fun compute(): Number
}