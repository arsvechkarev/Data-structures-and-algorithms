package algorithms.mathengine

fun findBinaryExpressionAround(string: String, operation: BinaryOperation, index: Int): Expression {
  val leftOperand = findLeftOperand(string, index)
  val rightOperand = findRightOperand(string, index)
  return BinaryExpression(
    leftOperand.first, rightOperand.first, operation,
    leftOperand.second, rightOperand.second
  )
}

private fun findLeftOperand(string: String, index: Int): Pair<Number, Int> {
  var currIndex = index - 1
  val result = StringBuilder()
  while (currIndex >= 0 && string[currIndex].isDigitOrNegativeNumber()) {
    result.append(string[currIndex])
    currIndex--
  }
  return Pair(Number.of(result.reverse().toString()), ++currIndex)
}

private fun findRightOperand(string: String, index: Int): Pair<Number, Int> {
  var currIndex = index + 1
  val result = StringBuilder()
  while (currIndex < string.length && string[currIndex].isDigitOrNegativeNumber()) {
    result.append(string[currIndex])
    currIndex++
  }
  return Pair(Number.of(result.toString()), currIndex)
}