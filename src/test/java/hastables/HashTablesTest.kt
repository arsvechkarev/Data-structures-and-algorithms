package hastables

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HashTablesTest {
  
  private var table: HashTable<String, Int>? = null
  
  @Before
  fun setup() {
    table = HashTableOpenAddressing()
  }
  
  @After
  fun tearDown() {
    table = null
  }
  
  @Test
  fun `Adding and checking keys`() {
    val table = table!!
    
    assertFalse(table.has("John"))
    
    table.put("John", 45)
    table.put("Sarah", 42)
    table.put("Albert", 32)
    
    assertTrue(table.size == 3)
    assertFalse(table.has("Lol"))
    assertTrue(table.get("Sarah") == 42)
    
    table.put("Albert", 777)
    
    assertTrue(table.get("Albert") == 777)
  }
  
  @Test
  fun `Removing elements`() {
    val table = table!!
    
    table.put("John", 45)
    table.put("Sarah", 42)
    table.put("Albert", 32)
    
    assertFalse(table.remove("Lol"))
    assertTrue(table.remove("John"))
    
    assertTrue(table.size == 2)
    
    assertFalse(table.remove("John"))
    
    assertTrue(table.size == 2)
  }
  
  @Test
  fun `Resizing a table`() {
    val table = table!!
    
    table.put("John", 45)
    table.put("Sarah", 42)
    table.put("Albert", 32)
    
    assertTrue(table.size == 3)
    
    repeat(10) { i ->
      table.put(i.toString(), i * i)
    }
    
    assertTrue(table.size == 13)
    assertTrue(table.has("John"))
    assertTrue(table.has("Albert"))
    assertTrue(table.get("Sarah") == 42)
    assertTrue(table.get("5") == 25)
  }
}