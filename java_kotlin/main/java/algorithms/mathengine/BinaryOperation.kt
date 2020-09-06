package algorithms.mathengine

/**
 * Possible binary operation types
 *
 * @param sign Operator of the expression
 */
internal enum class BinaryOperation(val sign: String) {
  ADDITION(PLUS),
  SUBTRACTION(MINUS),
  MULTIPLICATION(MULTIPLY),
  DIVISION(DIVIDE)
}