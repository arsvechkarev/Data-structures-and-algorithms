package algorithms.mathengine.searches

import algorithms.mathengine.FACTORIAL
import algorithms.mathengine.expressions.Expression
import algorithms.mathengine.expressions.FactorialExpression

/**
 * Searches for factorial expression, such as 5! or 2!
 */
internal object FactorialExpressionSearch : ExpressionSearch {
  
  override fun tryFindItself(string: String, startIndex: Int, endIndex: Int): Expression? {
    val index = string.indexOf(FACTORIAL, startIndex)
    if (index != -1 && string[index - 1].isDigit()) {
      val number = string.findNumberAndPositionToLeftOf(index)
      return FactorialExpression(number.first, number.second, index + 1)
    }
    return null
  }
}