package datastructures.hastables

import datastructures.hastables.HashTable.Entry
import kotlin.math.abs

class HashTableOpenAddressing<K, V> : HashTable<K, V> {
  
  private var capacity = DEFAULT_CAPACITY
  private var _size = 0
  
  private var table = arrayOfNulls<Entry<K, V>>(capacity)
  
  override val size: Int get() = _size
  
  override fun put(key: K, value: V) {
    val hash = normalizedHashOf(key)
    var index = hash
    
    var count = 0
    while (table[index] != null) {
      if (table[index]!!.key == key) {
        // Updating and old value
        table[index]!!.value = value
        return
      }
      index = nextSlotIndex(hash + count) % capacity
      count++
    }
    table[index] = Entry(key, value)
    if (++_size > capacity * MAX_LOAD_FACTOR) {
      resizeTable()
    }
  }
  
  override fun get(key: K): V? {
    val entry = searchByKey(key)
    return entry?.value
  }
  
  override fun has(key: K): Boolean {
    return searchByKey(key) != null
  }
  
  override fun remove(key: K): Boolean {
    val hash = normalizedHashOf(key)
    var index = hash
    
    var count = 0
    while (table[index] != null) {
      if (table[index]!!.key == key) {
        table[index] = null
        _size--
        return true
      }
      index = nextSlotIndex(hash + count) % capacity
      count++
    }
    return false
  }
  
  private fun searchByKey(key: K): Entry<K, V>? {
    val hash = normalizedHashOf(key)
    var index = hash
    
    var count = 0
    while (table[index] != null) {
      if (table[index]!!.key == key) {
        return table[index]
      }
      index = nextSlotIndex(hash + count) % capacity
      count++
    }
    return null
  }
  
  private fun resizeTable() {
    capacity *= 2
    val newTable = arrayOfNulls<Entry<K, V>>(capacity)
    for (entry in table) {
      if (entry != null) {
        putInTable(newTable, entry)
      }
    }
    table = newTable
  }
  
  private fun putInTable(table: Array<Entry<K, V>?>, entry: Entry<K, V>) {
    val hash = normalizedHashOf(entry.key)
    var index = hash
    
    var count = 0
    while (table[index] != null) {
      index = nextSlotIndex(hash + count) % capacity
      count++
    }
    table[index] = entry
  }
  
  private fun normalizedHashOf(key: K): Int {
    return abs(key.hashCode()) % capacity
  }
  
  private fun nextSlotIndex(i: Int): Int {
    return (i * i + i) / 2
  }
  
  companion object {
    const val DEFAULT_CAPACITY = 8
    const val MAX_LOAD_FACTOR = 0.6f
  }
}