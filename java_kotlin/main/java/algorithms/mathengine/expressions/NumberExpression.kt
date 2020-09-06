package algorithms.mathengine.expressions

import algorithms.mathengine.Number

/** Expression that consists of one number */
internal class NumberExpression(
  private val number: Number,
  override val startIndex: Int,
  override val endIndex: Int
) : Expression {
  
  override fun compute(): Number {
    return number
  }
}