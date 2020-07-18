package datastructures.lists

import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException

class DoublyLinkedListTest {
  
  @Test
  fun `Creating list and adding elements`() {
    val list = DoublyLinkedList<String>()
    
    assertTrue(list.size() == 0)
    
    list.add("One")
    list.add("Arabic")
    list.add("qwerty")
    
    assertTrue(list.size() == 3)
    assertTrue(list[0] == "One")
    assertTrue(list[2] == "qwerty")
    assertTrue(list[1] == "Arabic")
  }
  
  @Test
  fun `Removing elements in small list`() {
    val list = DoublyLinkedList<String>()
    list.addFirst("One")
    list.addLast("Arabic")
    list.addFirst("qwerty")
    
    list.remove(0)
    
    assertTrue(list.size() == 2)
    assertTrue(list[0] == "One")
    assertTrue(list[1] == "Arabic")
    
    list.remove("One")
    
    assertTrue(list.size() == 1)
    assertTrue(list[0] == "Arabic")
  }
  
  @Test
  fun `Removing elements in large list`() {
    val list = DoublyLinkedList<String>()
    
    list.add("3")
    list.add("4")
    list.add("5")
    list.add("1")
    list.add("9")
    list.add("7")
    list.add("8")
    list.add("10")
    list.add("12")
    list.add("15")
    
    assertTrue(list.size() == 10)
    
    list.remove("4")
    
    assertTrue(list.size() == 9)
    
    assertTrue(list[0] == "3")
    assertTrue(list[1] == "5")
    assertTrue(list[2] == "1")
    assertTrue(list[3] == "9")
    assertTrue(list[4] == "7")
    assertTrue(list[5] == "8")
    assertTrue(list[6] == "10")
    assertTrue(list[7] == "12")
    assertTrue(list[8] == "15")
    
    list.remove(2)
    
    assertTrue(list.size() == 8)
    
    assertTrue(list[0] == "3")
    assertTrue(list[1] == "5")
    assertTrue(list[2] == "9")
    assertTrue(list[3] == "7")
    assertTrue(list[4] == "8")
    assertTrue(list[5] == "10")
    assertTrue(list[6] == "12")
    assertTrue(list[7] == "15")
    
    list.remove("8")
    
    assertTrue(list.size() == 7)
  
    list.remove(4)
    list.remove("15")
    list.remove(3)
    list.remove(0)
    // Element "999" is not in list, but here should be no exception
    list.remove("999")
    list.remove(1)
    list.remove(0)
    list.remove(0)
    
    assertTrue(list.size() == 0)
  }
  
  @Test
  fun `Iterating over a list`() {
    val linkedList = DoublyLinkedList<String>()
    val arrayList = ArrayList<String>()
    
    arrayList.add("Lol")
    arrayList.add("Qwerty")
    arrayList.add("Hahaha")
    
    linkedList.add("Lol")
    linkedList.add("Qwerty")
    linkedList.add("Hahaha")
    
    linkedList.forEachIndexed { i, element ->
      assertTrue(arrayList[i] == element)
    }
  }
  
  @Test(expected = IllegalArgumentException::class)
  fun `Checking index out of bounds 1`() {
    val list = DoublyLinkedList<String>()
    
    list.add("One")
    list.add("Two")
    
    list.get(2)
  }
  
  @Test(expected = IllegalArgumentException::class)
  fun `Checking index out of bounds 2`() {
    val list = DoublyLinkedList<String>()
    list.get(-1)
  }
  
  @Test(expected = IllegalArgumentException::class)
  fun `Checking index out of bounds 3`() {
    val list = DoublyLinkedList<String>()
    list.remove(-1)
  }
  
  @Test(expected = UnsupportedOperationException::class)
  fun `Removing with iterator is not allowed`() {
    val list = DoublyLinkedList<String>()
    list.iterator().remove()
  }
}