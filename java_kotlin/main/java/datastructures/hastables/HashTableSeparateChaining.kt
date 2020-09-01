package datastructures.hastables

import datastructures.hastables.HashTable.Entry
import datastructures.lists.DoublyLinkedList
import kotlin.math.abs

class HashTableSeparateChaining<K, V>(
  private var capacity: Int = DEFAULT_CAPACITY,
  private var loadFactor: Float = DEFAULT_MAX_LOAD_FACTOR
) : HashTable<K, V> {
  
  private var table = arrayOfNulls<DoublyLinkedList<Entry<K, V>>>(capacity)
  private var _size = 0
  
  override val size get() = _size
  
  override fun put(key: K, value: V) {
    putInTable(key, value)
  }
  
  override fun get(key: K): V? {
    val entry = searchInTable(key)
    return entry?.value
  }
  
  override fun has(key: K): Boolean {
    return searchInTable(key) != null
  }
  
  override fun remove(key: K): Boolean {
    return removeInTable(key)
  }
  
  private fun searchInTable(key: K): Entry<K, V>? {
    val index = normalizedHashOf(key)
    var bucket = table[index]
    if (bucket == null) {
      bucket = DoublyLinkedList()
      table[index] = bucket
    }
    for (entry in bucket) {
      if (entry.key == key) {
        return entry
      }
    }
    return null
  }
  
  private fun putInTable(key: K, value: V) {
    val index = normalizedHashOf(key)
    var bucket = table[index]
    if (bucket == null) {
      bucket = DoublyLinkedList()
      table[index] = bucket
    }
    val existingEntry = searchInTable(key)
    if (existingEntry != null) {
      existingEntry.value = value
    } else {
      val newEntry = Entry(key, value)
      bucket.add(newEntry)
      if (++_size > capacity * loadFactor) {
        resizeTable()
      }
    }
  }
  
  private fun removeInTable(key: K): Boolean {
    val index = normalizedHashOf(key)
    val bucket = table[index] ?: return false
    var resultEntry: Entry<K, V>? = null
    for (entry in bucket) {
      if (entry.key == key) {
        resultEntry = entry
      }
    }
    if (resultEntry == null) return false
    bucket.remove(resultEntry)
    _size--
    return true
  }
  
  private fun resizeTable() {
    capacity *= 2
    val newTable = arrayOfNulls<DoublyLinkedList<Entry<K, V>>>(capacity)
    for (bucket in table) {
      if (bucket == null) continue
      for (entry in bucket) {
        val newIndex = normalizedHashOf(entry.key)
        var newBucket = newTable[newIndex]
        if (newBucket == null) {
          newBucket = DoublyLinkedList()
          newTable[newIndex] = newBucket
        }
        newBucket.add(entry)
      }
      bucket.clear()
    }
    table = newTable
  }
  
  private fun normalizedHashOf(key: K): Int {
    return abs(key.hashCode()) % capacity
  }
  
  companion object {
    const val DEFAULT_CAPACITY = 10
    const val DEFAULT_MAX_LOAD_FACTOR = 0.75f
  }
}