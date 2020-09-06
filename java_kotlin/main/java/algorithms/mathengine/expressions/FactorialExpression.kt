package algorithms.mathengine.expressions

import algorithms.mathengine.Number
import java.math.BigInteger

internal class FactorialExpression(
  private val number: Number,
  override val startIndex: Int,
  override val endIndex: Int
) : Expression {
  
  override fun compute(): Number {
    val integer = BigInteger(number.toString())
    var i = BigInteger("1")
    var product = BigInteger("1")
    while (i <= integer) {
      product *= i
      i++
    }
    return Number.of(product.toString())
  }
}