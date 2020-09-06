package algorithms.mathengine

import org.junit.Assert.assertEquals
import org.junit.Test

class BigDecimalNumberTest {
  
  private val String.num: Number
    get() = Number.of(this)
  
  @Test
  fun `Positive number creation and validation`() {
    assertEquals("123", "123".num.toString())
    assertEquals(123, "123".num.toInt())
    assertEquals(123.0, "123".num.toDouble(), 0.0001)
  }
  
  @Test
  fun `Minus sign at the start creation`() {
    assertEquals("-136.2", "-136.2".num.toString())
    assertEquals(-136, "-136.2".num.toInt())
    assertEquals(-136.2, "-136.2".num.toDouble(), 0.0001)
  }
  
  @Test
  fun `Negative letters creation`() {
    assertEquals("-136", "B36".num.toString())
    assertEquals(-136, "B36".num.toInt())
    assertEquals(-136.0, "B36".num.toDouble(), 0.0001)
  }
  
  @Test
  fun `Various additions`() {
    assertEquals("7".num, "5".num + "2".num)
    assertEquals("2.877".num, "5".num + "-2.123".num)
    assertEquals("-6".num, "5".num + "-11".num)
    assertEquals("-6.5".num, "5".num + "B1.5".num)
  }
  
  @Test
  fun `Various subtractions`() {
    assertEquals("7".num, "5".num - "-2".num)
    assertEquals("-7".num, "-2".num - "5".num)
    assertEquals("-4".num, "5".num - "9".num)
    assertEquals("-0.5555".num.toDouble(), "9".num.toDouble() - "9.5555".num.toDouble(), 0.00001)
  }
  
  @Test
  fun `Various multiplications`() {
    assertEquals("110".num, "5".num * "22".num)
    assertEquals("-10".num, "-2".num * "5".num)
    assertEquals("0".num, "-5".num * "0".num)
    assertEquals("0".num, "15".num * "0".num)
    assertEquals("25".num, "-5".num * "-5".num)
    assertEquals("-26.5".num, "5.3".num * "-5".num)
  }
  
  @Test
  fun `Various divisions`() {
    assertEquals("5".num, "110".num / "22".num)
    assertEquals("-2".num, "-10".num / "5".num)
    assertEquals("-5".num, "25".num / "-5".num)
    assertEquals("5.3".num, "-26.5".num / "-5".num)
  }
}