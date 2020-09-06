package algorithms.mathengine

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationsTest {
  
  private fun check(expression: String) {
    assertEquals(expression.substringAfter("=").trim(), expression.substringBefore("=").calculate())
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
    check("4 / 3 = 1.333333333333333")
    check("0.50000000000 + 0.5 = 1")
    check("-0.50000000000 - 0.5 = -1")
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
  fun `Brackets operations`() {
    check("7 + (3) = 10")
    check("(10) = 10")
    check("(10 + 1) * (5 - 7) = -22")
    check("7 * (3 - 2) = 7")
    check("(45 * (0 / (45055 - 45056))) * (8 - 8) = 0")
    check("(5.3 + 2.7) - (7.8 - (9.2 - 1.4)) = 8")
    check("((7)) * ((((3))) - 2) = 7")
    check("-(7 + 5) + (3) = -9")
    check("-(0 + 5 - 7) + (3) = 5")
  }
  
  @Test
  fun `Factorial operations`() {
    check("5! = 120")
    check("3!! = 720")
    check("(4)! = 24")
    check("5! - 121 = -1")
    check("((121)) - (5!) = 1")
    check("5! - 4! * 5 = 0")
    check("5! - ((((4!))) * 5) = 0")
    check("4! / (5 - 2)! = 4")
  }
}