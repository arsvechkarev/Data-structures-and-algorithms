package algorithms.mathengine

internal fun String.replaceNegativeNumbersWithLetters(): String {
  val builder = StringBuilder(this.length)
  val string = this.withNegativePrefixAsLetter()
  var i = 0
  while (i <= string.lastIndex) {
    val char = string[i]
    if (char.toString() == MINUS) {
      val prev = string[i - 1]
      val next = string[i + 1]
      if (!prev.isDigit() && prev.toString() != RIGHT_BRACKET && next.isDigit()) {
        builder.append(next.toNegativeNumberRepresentation())
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

internal fun String.withNegativePrefixAsLetter(): String {
  if (this.length == 1) {
    return this
  }
  return if (startsWith(MINUS)) {
    val temp = removeRange(0, 2)
    this[1].toNegativeNumberRepresentation() + temp
  } else {
    this
  }
}

internal fun String.consistOfNumbers(): Boolean {
  forEach { char -> if (!char.isDigitOrNegativeNumber()) return false }
  return true
}

internal val Char.isNegativeNumber: Boolean
  get() {
    return this.toInt() >= 'A'.toInt() && this.toInt() <= 'K'.toInt()
  }

internal fun Char.isDigitOrNegativeNumber(): Boolean {
  return this.isDigit() || this.isNegativeNumber
}

internal fun Char.toNegativeNumberRepresentation(): Char {
  return (this.toInt() + ('A' - '0')).toChar()
}

internal fun Char.toNormalNumberRepresentation(): String {
  return "-" + (this.toInt() - ('A' - '0')).toChar()
}
