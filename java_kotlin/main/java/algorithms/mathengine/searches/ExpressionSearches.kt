package algorithms.mathengine.searches

import algorithms.mathengine.expressions.Expression

internal object ExpressionSearches {
  
  fun tryFindExpression(string: String, startIndex: Int = 0, endIndexInclusive: Int): Expression? {
    return FactorialExpressionSearch.tryFindItself(string, startIndex, endIndexInclusive)
        ?: BinaryExpressionSearch.tryFindItself(string, startIndex, endIndexInclusive)
  }
}