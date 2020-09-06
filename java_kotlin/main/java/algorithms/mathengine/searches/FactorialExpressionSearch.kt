package algorithms.mathengine.searches

import algorithms.mathengine.FACTORIAL
import algorithms.mathengine.expressions.Expression
import algorithms.mathengine.expressions.FactorialExpression

internal object FactorialExpressionSearch : ExpressionSearch {
  
  override fun tryFindItself(string: String, startIndex: Int, endIndexInclusive: Int): Expression? {
    val index = string.indexOf(FACTORIAL, startIndex)
    if (index != -1) {
      val number = string.findNumberAndPositionToLeftOf(index)
      return FactorialExpression(number.first, number.second, index + 1)
    }
    return null
  }
}