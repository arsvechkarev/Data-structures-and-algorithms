package algorithms.mathengine.searches

import algorithms.mathengine.expressions.Expression

/**
 * Searches for the next expression to compute taking into account priority of operations
 */
internal object ExpressionSearches {
  
  /**
   * Returns next expression to compute, or null, if [string] is just a number
   */
  fun tryFindExpression(string: String, startIndex: Int, endIndex: Int): Expression? {
    return FactorialExpressionSearch.tryFindItself(string, startIndex, endIndex)
        ?: BinaryExpressionSearch.tryFindItself(string, startIndex, endIndex)
  }
}