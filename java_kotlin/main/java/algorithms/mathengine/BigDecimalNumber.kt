package algorithms.mathengine

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.roundToInt

/**
 * Implementation of [Number] that stores string value and performs arithmetic operations with [BigDecimal]
 */
internal class BigDecimalNumber(
  value: String
) : Number {
  
  private val value = value.withNegativePrefixAsLetterIfNeeded().removeSuffix(DOT_ZERO)
  
  override fun equals(other: Any?): Boolean {
    if (other == null) return false
    if (other !is Number) return false
    return this.toString() == other.toString()
  }
  
  override fun hashCode(): Int {
    return value.hashCode()
  }
  
  override fun toString(): String {
    var result = if (value[0].isNegativeNumber) {
      val temp = value.removeRange(0, 1)
      value[0].toNormalNumberRepresentation() + temp
    } else {
      value
    }
    // If result has trailing zeros, remove them
    result = result.removeTrailingZeros()
    if (result.startsWith(MINUS_ZERO) && !result.contains(DOT)) {
      // Result starts with zero and is not a fraction -> remove leading "-"
      result = result.removePrefix(MINUS)
    }
    return result
  }
  
  override fun rawStringValue(): String {
    return value
  }
  
  override fun toInt(): Int {
    return if (value[0].isNegativeNumber) {
      val temp = value.removeRange(0, 1)
      (value[0].toNormalNumberRepresentation() + temp).toDouble().roundToInt()
    } else {
      value.toDouble().roundToInt()
    }
  }
  
  override fun toDouble(): Double {
    return if (value[0].isNegativeNumber) {
      val temp = value.removeRange(0, 1)
      (value[0].toNormalNumberRepresentation() + temp).toDouble()
    } else {
      value.toDouble()
    }
  }
  
  override fun plus(other: Number): Number {
    return BigDecimalNumber(BigDecimal(this.toString()).add(BigDecimal(other.toString())).toString())
  }
  
  override fun minus(other: Number): Number {
    return BigDecimalNumber(BigDecimal(this.toString()).subtract(BigDecimal(other.toString())).toString())
  }
  
  override fun times(other: Number): Number {
    return BigDecimalNumber(BigDecimal(this.toString()).multiply(BigDecimal(other.toString())).toString())
  }
  
  override fun div(other: Number): Number {
    return BigDecimalNumber(
      BigDecimal(this.toString()).divide(BigDecimal(other.toString()), MathContext.DECIMAL64).toString())
  }
}