package algorithms.mathengine.expressions

import algorithms.mathengine.BinaryOperation
import algorithms.mathengine.Number

/**
 * Represents an expression with first operand, second operand and operation
 */
internal class BinaryExpression(
  private val firstOperand: Number,
  private val secondOperand: Number,
  private val operation: BinaryOperation,
  override val startInclusive: Int,
  override val endExclusive: Int
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
