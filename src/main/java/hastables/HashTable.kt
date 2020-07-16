package hastables

/**
 * Represents a hashtable
 */
interface HashTable<K, V> {
  
  /**
   * Returns amount of all entries in the table
   */
  val size: Int
  
  /**
   * Puts [value] with the [key] to the table
   */
  fun put(key: K, value: V)
  
  /**
   * Returns value for a [key] or null, if an entry with the key does not exist
   */
  fun get(key: K): V?
  
  /**
   * Returns true if value with a [key] exists in the table
   */
  fun has(key: K): Boolean
  
  /**
   * Removes entry with corresponding [key]. Returns true if entry was found and
   * removed, false otherwise
   */
  fun remove(key: K): Boolean
  
  /**
   * Represents an entry in the table
   */
  data class Entry<K, V>(
    val key: K,
    var value: V
  )
}