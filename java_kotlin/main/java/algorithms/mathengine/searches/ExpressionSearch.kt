package algorithms.mathengine.searches

import algorithms.mathengine.expressions.Expression

internal interface ExpressionSearch {
  
  /**
   * Searches expression in [string] for given [operation] starting from [startIndex]. If found,
   * returns result, if didn't, returns null
   */
  fun tryFindItself(string: String, startIndex: Int, endIndexInclusive: Int): Expression?
}