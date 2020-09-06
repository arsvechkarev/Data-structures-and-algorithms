package algorithms.mathengine

import algorithms.mathengine.expressions.Expression
import algorithms.mathengine.expressions.NumberExpression
import algorithms.mathengine.searches.ExpressionSearches

/**
 * Computes mathematical expression, like "12 + 2", "8 * 9 - 2", "(5.8 + (-5.6)) - ((8 - 4) * 4.6) + 2", and
 * returns result
 */
fun String.calculate(): String {
  var current = this.replace("\\s".toRegex(), "").replaceNegativeNumbers()
  while (true) {
    val expression = findNextExpression(current)
        ?: // findNextExpression returned number simple, calculation finished
        return Number.of(current).toString()
    current = replaceExpressionWithResult(current, expression).replaceNegativeNumbers()
  }
}

private fun findNextExpression(current: String): Expression? {
  val leftBracketIndex = current.lastIndexOf(LEFT_BRACKET)
  if (leftBracketIndex != -1) {
    val rightBracketIndex = current.indexOf(RIGHT_BRACKET, leftBracketIndex)
    if (current.substring(leftBracketIndex + 1, rightBracketIndex).consistOfNumbers()) {
      // We've got only one number in brackets, wrap it and return
      val stringNum = current.substring(leftBracketIndex + 1, rightBracketIndex)
      return NumberExpression(Number.of(stringNum), leftBracketIndex, rightBracketIndex + 1)
    } else {
      // There is an expression in brackets, compute it first
      return performExpressionSearch(current, leftBracketIndex, rightBracketIndex)
    }
  }
  return performExpressionSearch(current, 0, current.lastIndex)
}

private fun performExpressionSearch(current: String, startIndex: Int, endIndex: Int): Expression? {
  return ExpressionSearches.tryFindExpression(current, startIndex, endIndex)
}

private fun replaceExpressionWithResult(current: String, expression: Expression): String {
  val computationResult = expression.compute().rawStringValue()
  val builder = StringBuilder(current)
  builder.replace(expression.startInclusive, expression.endExclusive, computationResult)
  return builder.toString()
}
