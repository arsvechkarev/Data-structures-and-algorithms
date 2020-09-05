package algorithms.mathengine

import kotlin.math.roundToInt

class NumberImpl(
  value: String
) : Number {
  
  private val value = value.withNegativePrefixAsLetter()
  
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
    if (result.endsWith(".0")) {
      // Result ends with ".0" -> we can safely remove this suffix
      result = result.removeSuffix(".0")
    }
    if (result.startsWith("-0") && !result.contains(".")) {
      // Result starts with zero and is not a fraction -> remove leading "-"
      result = result.removePrefix("-")
    }
    return result
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
    return NumberImpl((this.toDouble() + other.toDouble()).toString())
  }
  
  override fun minus(other: Number): Number {
    return NumberImpl((this.toDouble() - other.toDouble()).toString())
  }
  
  override fun times(other: Number): Number {
    return NumberImpl((this.toDouble() * other.toDouble()).toString())
  }
  
  override fun div(other: Number): Number {
    return NumberImpl((this.toDouble() / other.toDouble()).toString())
  }
}