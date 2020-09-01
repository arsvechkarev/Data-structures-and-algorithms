package utils

fun <E> Array<E>.swap(i: Int, j: Int) {
  val temp = this[i]
  this[i] = this[j]
  this[j] = temp
}

fun <E> ArrayList<E>.swap(i: Int, j: Int) {
  val temp = this[i]
  this[i] = this[j]
  this[j] = temp
}