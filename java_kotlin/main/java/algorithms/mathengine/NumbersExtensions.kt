package algorithms.mathengine

internal fun String.replaceNegativeNumbers(): String {
  val builder = StringBuilder(this.length)
  val string = this.withNegativePrefixAsLetterIfNeeded()
  var i = 0
  while (i <= string.lastIndex) {
    val char = string[i]
    if (char.toString() == MINUS && i - 1 > 0) {
      val prev = string[i - 1]
      val next = string[i + 1]
      if (!prev.isDigit()
          && prev != RIGHT_BRACKET
          && prev.toString() != FACTORIAL
          && next.isDigitOrNegativeNumber()) {
        val toAppend = if (next.isNegativeNumber) {
          next.toNumberRepresentationWithoutMinus()
        } else {
          next.toNegativeNumberRepresentation()
        }
        builder.append(toAppend)
        i += 2
        continue
      } else {
        builder.append(char)
      }
    } else {
      builder.append(char)
    }
    i++
  }
  return builder.toString()
}

internal fun String.withNegativePrefixAsLetterIfNeeded(): String {
  if (this.length < 2) {
    return this
  }
  return if (startsWith(MINUS) && this[1].isDigitOrNegativeNumber()) {
    val temp = removeRange(0, 2)
    val toAppend = if (this[1].isNegativeNumber) {
      this[1].toNumberRepresentationWithoutMinus()
    } else {
      this[1].toNegativeNumberRepresentation()
    }
    toAppend + temp
  } else {
    this
  }
}

internal fun String.consistOfNumbers(): Boolean {
  forEach { char -> if (!char.isDigitOrNegativeNumber() && char != DOT) return false }
  return true
}

internal val Char.isNegativeNumber: Boolean
  get() {
    return this.toInt() >= 'A'.toInt() && this.toInt() <= 'J'.toInt()
  }

internal fun Char.isDigitOrNegativeNumber(): Boolean {
  return this.isDigit() || this.isNegativeNumber
}

internal fun Char.toNegativeNumberRepresentation(): Char {
  return (this.toInt() + ('A' - '0')).toChar()
}

internal fun Char.toNormalNumberRepresentation(): String {
  return MINUS + (this.toInt() - ('A' - '0')).toChar()
}

internal fun Char.toNumberRepresentationWithoutMinus(): Char {
  return (this.toInt() - ('A' - '0')).toChar()
}

internal fun String.removeTrailingZeros(): String {
  val trailingZeros = countTrailingZeros()
  return this.removeRange(length - trailingZeros, length)
}

fun String.countTrailingZeros(): Int {
  var trailingZeros = 0
  for (i in lastIndex downTo 0) {
    if (this[i] == ZERO) {
      trailingZeros++
    } else if (this[i] == DOT) {
      return trailingZeros
    }
  }
  return 0
}