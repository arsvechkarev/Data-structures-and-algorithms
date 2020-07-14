import java.lang.IllegalArgumentException

fun assertThat(condition: Boolean) {
  if (!condition) {
    throw IllegalArgumentException("Assertion failed")
  }
}