package algorithms.mathengine

import algorithms.mathengine.BinaryOperation.ADDITION
import algorithms.mathengine.BinaryOperation.DIVISION
import algorithms.mathengine.BinaryOperation.MULTIPLICATION
import algorithms.mathengine.BinaryOperation.SUBTRACTION

fun String.evaluate(): String {
  var current = this.replace("\\s".toRegex(), "").replaceNegativeNumbersWithLetters()
  while (true) {
    val expression = findNextExpression(current)
        ?: // findNextExpression returned number, calculation finished
        return current.replaceLettersWithNegativeNumbers()
    current = replaceExpressionWithResult(current, expression)
  }
}

private fun findNextExpression(current: String): Expression? {
  // TODO (9/5/2020): Add other expressions
  return tryFindSimpleExpression(whicheverOccursFirst(current, MULTIPLICATION, DIVISION), current)
      ?: tryFindSimpleExpression(whicheverOccursFirst(current, ADDITION, SUBTRACTION), current)
}

private fun whicheverOccursFirst(string: String, op1: BinaryOperation, op2: BinaryOperation): BinaryOperation {
  val indexOp1 = string.indexOf(op1.sign)
  val indexOp2 = string.indexOf(op2.sign)
  if (indexOp1 == -1) {
    return op2
  }
  if (indexOp2 == -1) {
    return op1
  }
  return if (indexOp1 < indexOp2) op1 else op2
}

private fun tryFindSimpleExpression(operation: BinaryOperation, string: String): Expression? {
  val index = string.indexOf(operation.sign)
  if (index != -1
      && index > 0 && index < string.lastIndex
      && string[index - 1].isDigitOrNegativeNumber() && string[index + 1].isDigitOrNegativeNumber()) {
    return findBinaryExpressionAround(string, operation, index)
  }
  return null
}

fun replaceExpressionWithResult(current: String, expression: Expression): String {
  val result = expression.compute().toString()
  val builder = StringBuilder(current)
  builder.replace(expression.startIndex, expression.endIndex, result)
  return builder.toString()
}

fun main() {
  println("-4+3*2*2".evaluate())
}
