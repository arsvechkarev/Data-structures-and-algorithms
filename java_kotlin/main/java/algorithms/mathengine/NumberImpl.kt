package algorithms.mathengine

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
    return if (value[0].isNegativeNumber) {
      val temp = value.removeRange(0, 1)
      value[0].toNormalNumberRepresentation() + temp
    } else {
      value
    }
  }
  
  override fun toInt(): Int {
    return if (value[0].isNegativeNumber) {
      val temp = value.removeRange(0, 1)
      (value[0].toNormalNumberRepresentation() + temp).toInt()
    } else {
      value.toInt()
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
    return NumberImpl((this.toInt() + other.toInt()).toString())
  }
  
  override fun minus(other: Number): Number {
    return NumberImpl((this.toInt() - other.toInt()).toString())
  }
  
  override fun times(other: Number): Number {
    return NumberImpl((this.toInt() * other.toInt()).toString())
  }
  
  override fun div(other: Number): Number {
    return NumberImpl((this.toDouble() / other.toDouble()).toString())
  }
}

fun main() {
  val n1 = NumberImpl("I") // -9
  
  val message = (n1 * NumberImpl("-3")).toString()
  println(message)
}