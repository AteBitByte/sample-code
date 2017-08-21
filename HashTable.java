import java.util.*;

/*
*	A quick hashtable implementation for practice/funzies.
*/
public class HashTable <K, V> {
	
	/**
	*	An object to hold a Key-Value pair mapping.
	**/
	public class Pair {
		private K key;
		private V value;
		
		/**
		*	@param K key The pair's Key.
		* 	@param V value The pair's Value.
		**/
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		*	@return K Getter for a Pair object's key field.
		**/
		public K getKey() {
			return key;
		}
		
		/**
		*	@return V Getter for a Pair object's value field.
		**/
		public V getValue() {
			return value;
		}
	}
	
	/** The HashTable **/
	private ArrayList<ArrayList<Pair>> table;
	
	/** The number of indices in the table **/
	private static final int _TABLE_SIZE = 10;
	
	/**
	*	@param K key The pair's Key.
	* 	@param V value The pair's Value.
	**/
	public <K, V> HashTable() {
		this.table = new ArrayList<ArrayList<Pair>>(_TABLE_SIZE);
		
		for (int i = 0; i < _TABLE_SIZE; i++) {
			table.add(new ArrayList<Pair>());
		}
	}
	
	/**
	*  	Get the insertion index for a given key.
	*	@param K key The key
	*	@return int The insertion index for the given key
	*/
	private int getStorageIndex(K key) {
		
		// Get the key's HashCode
		int hashCode = key.hashCode();
		
		// Map HashCode to an array index
		int index = hashCode % _TABLE_SIZE;
		
		return index;
	}
	
	/**
	*	@param K key The key
	*	@return ArrayList<HashTable<K,V>.Pair> The list at the index corresponding to the given key.
	*/
	private ArrayList<HashTable<K,V>.Pair> getHashList(K key) {
		// Get the storage index
		int index = getStorageIndex(key);
		
		// Get list located at storage index
		ArrayList<HashTable<K, V>.Pair> hashList = table.get(index);
		
		return hashList;
	}
	
	/** 
	*	Insert a key-value pair into the map.
	*	@param K key The key
	*	@param V value The value the key maps to
	*/
	public void insert(K key, V value) {
		if (this.contains(key)) {
			System.err.println("The key '" + key + "' already maps to '" + this.get(key) + "' and cannot be overwritten with '" + value + "'; you must first delete this mapping to add the new pair.");
		}
		else {
			ArrayList<HashTable<K, V>.Pair> hashList = getHashList(key);
			
			// Append new pair to hashList
			hashList.add( (HashTable<K, V>.Pair) new Pair(key, value) );
			
			System.err.println("Inserted (Key, Value) pair: ('" + key + "', '" + value + "').");
		}
	}
	
	/** 
	*	Remove the pair associated with the key from the table.
	*/
	public void remove(K key) {
		ArrayList<HashTable<K, V>.Pair> hashList = getHashList(key);
		
		// Append new pair to hashList
		for (int i = 0; i < hashList.size(); i++) {
			Pair p = hashList.get(i);
			if (p.getKey().equals(key)) {
				hashList.remove(i);
				
				System.err.println("Removed (Key, Value) pair: ('" + p.key + "', '" + p.value + "').");
				break;
			}
		}
	}
	
	/**
	*	@return true if the table contains the key; otherwise, false.
	**/
	public boolean contains(K key) {
		ArrayList<HashTable<K, V>.Pair> hashList = getHashList(key);
		
		for (Pair p : hashList) {
			if (p.getKey().equals(key)) {
				System.err.println("The table contains the (Key, Value) pair ('" + p.key + "', '" + p.value + "').");
				return true;
			}
		}
		
		System.err.println("The table does not currently contain the key '" + key + "'.");
		return false;
	}
	
	/**
	*	@param K A key to find in the map.
	*	@return V The value associated with the key; null if not in the table.
	**/
	public V get(K key) {
		ArrayList<HashTable<K, V>.Pair> hashList = getHashList(key);
		
		V value = null;
		
		for (Pair p : hashList) {
			// If the key is found
			if (p.getKey().equals(key)) {
				// Set value and break the loop
				value = p.getValue();
				break;
			}
		}
		
		return value;
	}
	
	/**
	*	Runs/tests the implementation.
	**/
	public static void run() {
		HashTable<String, String> hashTable = new HashTable<String, String>();
		String key1 = "hello";
		String key2 = "hola";
		String value1 = "world";
		String value2 = "mundo";
		
		hashTable.contains(key1);
		System.err.println();
		hashTable.insert(key1, value1);
		System.err.println();
		hashTable.contains(key1);
		System.err.println();
		hashTable.insert(key2, value2);
		System.err.println();
		hashTable.insert(key1, value2);
		System.err.println();
		hashTable.contains(key1);
		System.err.println();
		hashTable.remove(key1);
		System.err.println();
		hashTable.insert(key1, value2);
		
	}
	
	public static void main(String[] args) {
		HashTable.run();
	}
}
