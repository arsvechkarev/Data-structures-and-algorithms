package algorithms.mathengine

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