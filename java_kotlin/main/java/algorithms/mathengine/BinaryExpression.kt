package algorithms.mathengine

/**
 * Represents an expression with first operand, second operand and operation
 */
internal class BinaryExpression(
  private val firstOperand: Number,
  private val secondOperand: Number,
  private val operation: BinaryOperation,
  override val startIndex: Int,
  override val endIndex: Int
) : Expression {
  
  override fun compute(): Number {
    return when (operation) {
      BinaryOperation.ADDITION -> firstOperand + secondOperand
      BinaryOperation.SUBTRACTION -> firstOperand - secondOperand
      BinaryOperation.MULTIPLICATION -> firstOperand * secondOperand
      BinaryOperation.DIVISION -> firstOperand / secondOperand
    }
  }
}
