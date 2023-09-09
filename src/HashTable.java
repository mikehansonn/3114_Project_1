/**
 * This is a hashtable implementation for the project
 * 
 * @author mikehanson, matt02
 * @version 9/7/23
 * 
 * @param <K> the key of the entry
 * @param <V> the value of the entry
 */
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
	private int capacity; // the total amount we can go
	private Entry<Integer, V>[] table;
	private int size; // the current number of elements inside

	/**
	 * constructor for the hashtable
	 * 
	 * @param capacity initial size
	 */
	public HashTable(int capacity) {
		this.capacity = capacity;
		this.table = (Entry<Integer, V>[]) new Entry[capacity];
		this.size = 0; 
	}
	
	/**
	 * get the capacity
	 * 
	 * @return current capacity of the table
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * getter for the size
	 * 
	 * @return current size of table
	 */
	public int getSize() {
		return size;
	}

	/**
	 * used to add new entries to hashtable
	 * 
	 * @param key   key to add
	 * @param value value to add 
	 */
	public void insert(int key, V value) {
		if ((double) size / capacity >= .5) {
			rehash();
		}

		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null && !table[hash1].getIsDeleted()) {
			if (table[hash1].getKey() == key) {
				System.out.println("Insert FAILED - There "
						+ "is already a record with ID " + key);
				return; // this record already exists 
			}
 
			hash1 = (hash1 + hash2) % table.length;
		}

		table[hash1] = new Entry<>(key, value);
		size++;
		System.out.println(
				"Successfully inserted record with ID " + key);
	}

	/**
	 * Gets the value at given key
	 * 
	 * @param key key to get value
	 * @return returns the value of the entry
	 */
	public V search(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null) {
			if (!table[hash1].getIsDeleted() && table[hash1].getKey() == key) {
				System.out.println("Found record with ID " + key + ":");
				return table[hash1].getValue();
			}

			hash1 = (hash1 + hash2) % table.length;
		}
		
		System.out.println(
				"Search FAILED -- There is no record with ID " + key);
		return null; // if the index is not found
	}

	/**
	 * removes the entry at given key
	 * 
	 * @param key key to remove entry at
	 */
	public V delete(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null) {
			if (!table[hash1].getIsDeleted() && table[hash1].getKey() == key) {
				table[hash1].delete(); 
				size--; 
				System.out.println(
						"Record with ID " + key + " successfully deleted from the database");
				return table[hash1].getValue();
			}

			hash1 = (hash1 + hash2) % table.length;
		}
		
		System.out.println("Delete FAILED -- There is no record with ID " + key);
		return null;
	}

	/**
	 * The first hash function
	 * 
	 * @param key key to hash
	 * @return returns the hashed value
	 */
	public int hash1(int key) {
		return key % table.length;
	}

	/**
	 * The second hash function
	 * 
	 * @param key key to hash
	 * @return returns the hashed value
	 */
	public int hash2(int key) {
		int h2 = (((key / table.length) % (table.length / 2)) * 2) + 1;
		return h2 % table.length;
	}

	/**
	 * Resize the table if it runs out of space
	 */
	private void rehash() {
		Entry<Integer, V>[] smallTable = table;
		capacity = smallTable.length * 2;
		table = new Entry[capacity];
		size = 0;

		for (Entry<Integer, V> entry : smallTable) {
			if (entry != null && !entry.getIsDeleted()) {
				insert(entry.getKey(), entry.getValue());
			}
		}
		System.out.println(
				"Hash Table expanded to" + capacity + "records");
	}

	/**
	 * Simple toString for the Hashtable
	 * 
	 * @return returns the string of hashes
	 */
	public String toString() {
		String ret = "Hashtable:\n";

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				if (table[i].getIsDeleted()) {
					String add = i + ": " + "TOMBSTONE" + "\n";
					ret += add;
				} 
				else {
					String add = i + ": " + table[i].getKey() + "\n";
					ret += add;
				}
			}
		}
		ret += "total records: " + size + "\n";

		return ret;
	}

	/**
	 * Entry class to store in the hashtable
	 * 
	 * @param <K> key of the entry
	 * @param <V> value of the entry
	 */
	private static class Entry<K, V> {
		private K key;
		private V value;
		private boolean isDeleted;

		/**
		 * Constructor for the entry
		 * 
		 * @param key   of the entry
		 * @param value of the entry
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.isDeleted = false;
		}

		/**
		 * getter for the key
		 * 
		 * @return key of the entry
		 */
		public K getKey() {
			return key;
		}

		/**
		 * getter for value of the entry
		 * 
		 * @return the value of the entry
		 */
		public V getValue() {
			return value;
		}

		/**
		 * checks if the entry has been deleted
		 * 
		 * @return true or false
		 */
		public boolean getIsDeleted() {
			return isDeleted;
		}

		/**
		 * make it so the entry is noted as deleted
		 */
		public void delete() {
			isDeleted = true;
		}
	}
}