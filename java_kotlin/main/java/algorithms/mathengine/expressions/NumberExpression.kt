package algorithms.mathengine.expressions

import algorithms.mathengine.Number

/** Expression that consists of one number */
internal class NumberExpression(
  private val number: Number,
  override val startInclusive: Int,
  override val endExclusive: Int
) : Expression {
  
  override fun compute(): Number {
    return number
  }
}