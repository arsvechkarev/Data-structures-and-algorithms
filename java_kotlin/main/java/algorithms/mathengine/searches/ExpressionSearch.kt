package algorithms.mathengine.searches

import algorithms.mathengine.expressions.Expression

/**
 * Performs searching for an expression
 */
internal interface ExpressionSearch {
  
  /**
   * Searches expression in [string] from [startIndex] inclusive up to [endIndex] inclusive. If found,
   * returns result, if didn't, returns null
   */
  fun tryFindItself(string: String, startIndex: Int, endIndex: Int): Expression?
}