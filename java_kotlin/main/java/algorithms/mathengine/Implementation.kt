package algorithms.mathengine

import algorithms.mathengine.BinaryOperation.ADDITION
import algorithms.mathengine.BinaryOperation.DIVISION
import algorithms.mathengine.BinaryOperation.MULTIPLICATION
import algorithms.mathengine.BinaryOperation.SUBTRACTION

/**
 * Computes mathematical expression, like "12 + 2", "8 * 9 - 2", "(5.8 + (-5.6)) - ((8 - 4) * 4.6) + 2", and
 * returns result
 */
fun String.evaluate(): String {
  var current = this.replace("\\s".toRegex(), "").replaceNegativeNumbersWithLetters()
  while (true) {
    val expression = findNextExpression(current)
        ?: // findNextExpression returned number, calculation finished
        return Number.of(current).toString()
    current = replaceExpressionWithResult(current, expression)
  }
}

private fun findNextExpression(current: String): Expression? {
  val leftBracketIndex = current.lastIndexOf(LEFT_BRACKET)
  if (leftBracketIndex != -1) {
    val rightBracketIndex = current.indexOf(RIGHT_BRACKET, leftBracketIndex)
    if (current.substring(leftBracketIndex + 1, rightBracketIndex).consistOfNumbers()) {
      // We've got only one number in bracket, unwrap it and return
      val stringNum = current.substring(leftBracketIndex + 1, rightBracketIndex)
      return NumberExpression(Number.of(stringNum), leftBracketIndex, rightBracketIndex + 1)
    } else {
      return performExpressionSearch(current, leftBracketIndex)
    }
  }
  return performExpressionSearch(current)
}

private fun performExpressionSearch(current: String, startIndex: Int = 0): Expression? {
  return tryFindExpression(firstOperationIndex(current, startIndex, MULTIPLICATION, DIVISION), current,
    startIndex)
      ?: tryFindExpression(firstOperationIndex(current, startIndex, ADDITION, SUBTRACTION), current,
        startIndex)
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

private fun tryFindExpression(operation: BinaryOperation, string: String, startIndex: Int = 0): Expression? {
  val index = string.indexOf(operation.sign, startIndex)
  if (index != -1
      && index > 0 && index < string.lastIndex
      && string[index - 1].isDigitOrNegativeNumber() && string[index + 1].isDigitOrNegativeNumber()) {
    return findBinaryExpressionAround(string, operation, index)
  }
  return null
}

private fun replaceExpressionWithResult(current: String, expression: Expression): String {
  val computationResult = expression.compute().rawStringValue()
  val builder = StringBuilder(current)
  builder.replace(expression.startIndex, expression.endIndex, computationResult)
  return builder.toString()
}
