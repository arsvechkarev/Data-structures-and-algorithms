package algorithms.mathengine.searches

import algorithms.mathengine.DOT
import algorithms.mathengine.Number
import algorithms.mathengine.isDigitOrNegativeNumber

/** Returns number to the left of [index] and start position of this number inclusive */
internal fun String.findNumberAndPositionToLeftOf(index: Int): Pair<Number, Int> {
  var currIndex = index - 1
  val result = StringBuilder()
  while (currIndex >= 0
      && (this[currIndex].isDigitOrNegativeNumber() || this[currIndex].toString() == DOT)) {
    result.append(this[currIndex])
    currIndex--
  }
  return Pair(Number.of(result.reverse().toString()), ++currIndex)
}

/** Returns number to the right of [index] and end position of this number exclusive */
internal fun String.findNumberAndPositionToRightOf(index: Int): Pair<Number, Int> {
  var currIndex = index + 1
  val result = StringBuilder()
  while (currIndex < length
      && (this[currIndex].isDigitOrNegativeNumber() || this[currIndex].toString() == DOT)) {
    result.append(this[currIndex])
    currIndex++
  }
  return Pair(Number.of(result.toString()), currIndex)
}