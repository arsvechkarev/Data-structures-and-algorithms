package algorithms.mathengine

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationsTest {
  
  private fun check(expression: String) {
    assertEquals(expression.substringAfter("=").trim(), expression.substringBefore("=").evaluate())
  }
  
  @Test
  fun `Simple operations`() {
    check("2+2=4")
    check("9 - 2 = 7")
    check("3 - 8 = -5")
    check("-3 + 5 = 2")
    check("7 * 5 = 35")
    check("-3 * 9 = -27")
    check("9 / 2 = 4.5")
    check("9 / 3 = 3")
    check("0 / -8 = 0")
    check("-16 / -8 = 2")
  }
  
  @Test
  fun `Compound operations`() {
    check("-88 / 8 + 2 = -9")
    check("-88 / -8 + 2 = 13")
    check("-88 / -8 / 11 + 2 = 3")
    check("8 - 2 * 4 = 0")
    check("8 - 2 * 2 + 4 = 8")
  }
  
  @Test
  fun `Brackets test`() {
    check("7 + (3) = 10")
    check("(10) = 10")
    check("(10 + 1) * (5 - 7) = -22")
    check("7 * (3 - 2) = 7")
    check("(45 * (0 / (45055 - 45056))) * (8 - 8) = 0")
  }
}