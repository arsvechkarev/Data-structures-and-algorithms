package algorithms.mathengine

/**
 * Represents a number on witch arithmetic operations could be performed.
 */
internal interface Number {
  
  /**
   * Returns number in standard decimal view if number is non-negative, like "0", "547" or "20.33",
   * but if the number is negative, it should return number in special view: Leading minus sign (if any)
   * transforms the following number after this minus to letter code from 'A' to 'J'. For instance, if the
   * number is "-1", this method should return "A", if the number is "-9633", result would be "I633", if
   * the number is "-0.67", result is "A.67" and so on.
   */
  fun rawStringValue(): String
  
  /** Returns int value of the number. If number has fraction, fractional part is dropped */
  fun toInt(): Int
  
  /** Returns double value of the number */
  fun toDouble(): Double
  
  /** Returns true, if this number is equal to [other], false otherwise */
  override fun equals(other: Any?): Boolean
  
  /** Returns hash code of the number */
  override fun hashCode(): Int
  
  /**
   * Returns normal string representation of the number, like "56", "123.33", "-97" and so on
   */
  override fun toString(): String
  
  operator fun plus(other: Number): Number
  
  operator fun minus(other: Number): Number
  
  operator fun div(other: Number): Number
  
  operator fun times(other: Number): Number
  
  companion object {
    
    fun of(value: String): Number = BigDecimalNumber(value)
  }
}