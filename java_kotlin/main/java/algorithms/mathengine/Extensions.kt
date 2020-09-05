package algorithms.mathengine

fun String.replaceNegativeNumbersWithLetters(): String {
  val builder = StringBuilder(this.length)
  for (i in 1..this.lastIndex) {
    val prev = this[i - 1]
    val char = this[i]
    if (char.isDigit() && prev.toString() == MINUS) {
      builder.append(char.toNegativeNumberCode())
    } else {
      builder.append(char)
    }
  }
  return builder.toString()
}

fun String.replaceLettersWithNegativeNumbers(): String {
  val builder = StringBuilder(this.length)
  for (i in 0..this.lastIndex) {
    val char = this[i]
    if (char.isNegativeNumber) {
      builder.append(char.toNormalNumberRepresentation())
    } else {
      builder.append(char)
    }
  }
  return builder.toString()
}

fun String.withNegativePrefixAsLetter(): String {
  if (this.length == 1) {
    return this
  }
  return if (this.startsWith(MINUS)) {
    val temp = this.removeRange(0, 2)
    this[1].toNegativeNumberCode() + temp
  } else {
    this
  }
}

val Char.isNegativeNumber: Boolean
  get() {
    return this.toInt() >= 'A'.toInt() && this.toInt() <= 'I'.toInt()
  }

fun Char.isDigitOrNegativeNumber(): Boolean {
  return this.isDigit() || this.isNegativeNumber
}

fun Char.toNegativeNumberCode(): Char {
  return (this.toInt() + ('A' - '1')).toChar()
}

fun Char.toNormalNumberRepresentation(): String {
  return "-" + (this.toInt() - ('A' - '1')).toChar()
}
