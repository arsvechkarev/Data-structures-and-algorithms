package algorithms.mathengine.searches

import algorithms.mathengine.BinaryOperation
import algorithms.mathengine.BinaryOperation.ADDITION
import algorithms.mathengine.BinaryOperation.DIVISION
import algorithms.mathengine.BinaryOperation.MULTIPLICATION
import algorithms.mathengine.BinaryOperation.SUBTRACTION
import algorithms.mathengine.expressions.BinaryExpression
import algorithms.mathengine.expressions.Expression
import algorithms.mathengine.isDigitOrNegativeNumber

internal object BinaryExpressionSearch : ExpressionSearch {
  
  override fun tryFindItself(string: String, startIndex: Int, endIndexInclusive: Int): Expression? {
    return binaryExpression(string, startIndex, endIndexInclusive, MULTIPLICATION, DIVISION)
        ?: binaryExpression(string, startIndex, endIndexInclusive, ADDITION, SUBTRACTION)
  }
  
  
  private fun binaryExpression(current: String, startIndex: Int, endIndexInclusive: Int,
                               op1: BinaryOperation, op2: BinaryOperation): Expression? {
    return tryFindExpression(
      firstOperationIndex(current, startIndex, op1, op2),
      current, startIndex
    )
  }
  
  private fun firstOperationIndex(string: String, startIndex: Int, op1: BinaryOperation, op2: BinaryOperation): BinaryOperation {
    val indexOp1 = string.indexOf(op1.sign, startIndex)
    val indexOp2 = string.indexOf(op2.sign, startIndex)
    if (indexOp1 == -1) {
      return op2
    }
    if (indexOp2 == -1) {
      return op1
    }
    return if (indexOp1 < indexOp2) op1 else op2
  }
  
  private fun tryFindExpression(operation: BinaryOperation, string: String, startIndex: Int): Expression? {
    val index = string.indexOf(operation.sign, startIndex)
    if (index != -1
        && index > 0 && index < string.lastIndex
        && string[index - 1].isDigitOrNegativeNumber() && string[index + 1].isDigitOrNegativeNumber()) {
      return findBinaryExpressionAround(string, operation, index)
    }
    return null
  }
  
  private fun findBinaryExpressionAround(string: String, operation: BinaryOperation, index: Int): Expression {
    val leftOperand = string.findNumberAndPositionToLeftOf(index)
    val rightOperand = string.findNumberAndPositionToRightOf(index)
    return BinaryExpression(
      leftOperand.first, rightOperand.first, operation,
      leftOperand.second, rightOperand.second
    )
  }
}