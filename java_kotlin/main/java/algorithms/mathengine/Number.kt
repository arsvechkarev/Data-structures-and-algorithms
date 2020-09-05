package algorithms.mathengine

internal interface Number {
  
  override fun equals(other: Any?): Boolean
  
  override fun hashCode(): Int
  
  override fun toString(): String
  
  fun rawStringValue(): String
  
  fun toInt(): Int
  
  fun toDouble(): Double
  
  operator fun plus(other: Number): Number
  
  operator fun minus(other: Number): Number
  
  operator fun div(other: Number): Number
  
  operator fun times(other: Number): Number
  
  companion object {
    
    fun of(value: String): Number = NumberImpl(value)
  }
}