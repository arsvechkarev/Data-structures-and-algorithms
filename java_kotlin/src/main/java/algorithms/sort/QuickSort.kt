package algorithms.sort

import utils.swap

fun <E : Comparable<E>> quickSort(array: Array<E>) {
  quickSortRecursive(array, 0, array.size)
}

fun <E : Comparable<E>> quickSortRecursive(array: Array<E>, left: Int, right: Int) {
  if (left < right) {
    val partition = partition(array, left, right)
    quickSortRecursive(array, left, partition)
    quickSortRecursive(array, partition + 1, right)
  }
}

fun <E : Comparable<E>> partition(array: Array<E>, left: Int, right: Int): Int {
  val pivot = array[right - 1]
  var i = left - 1
  for (j in left until right - 1) {
    if (array[j] < pivot) {
      i++
      array.swap(i, j)
    }
  }
  array.swap(i + 1, right - 1)
  return ++i
}

fun main() {
  val ints = arrayOf(5, 8, 1, 3, 6, 2, 7, 4)
  quickSort(ints)
  println(ints.contentToString())
}
