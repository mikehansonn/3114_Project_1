@SuppressWarnings("unchecked")

public class HashTable<K, V> {
	private int capacity; // the total amount we can go
	private Entry<Integer, V>[] table;
	private int size; // the current number of elements inside

	public HashTable(int capacity) {
		this.capacity = capacity;
		this.table = (Entry<Integer, V>[]) new Entry[capacity];
		this.size = 0;
	}

	public void put(int key, V value) {
		if ((double) size / capacity >= .5) {
			rehash();
		}

		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null) {
			if (table[hash1].getKey() == key) {
				System.out.println("Insert FAILED - There is already a record with ID " + key);
				return; // this record already exists
			}

			hash1 = (hash1 + hash2) % table.length;
		}

		table[hash1] = new Entry<>(key, value);
		size++;
		System.out.println("Successfully inserted record with ID " + key);
	}

	public V get(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null) {
			if (table[hash1].getKey() == key) {
				return table[hash1].getValue();
			}

			hash1 = (hash1 + hash2) % table.length;
		}

		return null; // if the index is not found
	}

	public void remove(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while (table[hash1] != null) {
			if (table[hash1].getKey() == key) {
				table[hash1] = null;
				size--;

				return;
			}

			hash1 = (hash1 + hash2) % table.length;
		}
	}

	private int hash1(int key) {
		return key % table.length;
	}

	private int hash2(int key) {
		int h2 = (((key / table.length) % (table.length / 2)) * 2) + 1;
		return h2 % table.length;
	}

	private void rehash() {
		Entry<Integer, V>[] smallTable = table;
		table = new Entry[smallTable.length * 2];
		size = 0;

		for (Entry<Integer, V> entry : smallTable) {
			if (entry != null) {
				put(entry.getKey(), entry.getValue());
			}
		}
	}

	public String toString() {
		String ret = "Hashtable:\n";

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				String add = i + ": " + table[i].getKey() + "\n";
				ret += add;
			}
		}
		ret += "total records: " + size + "\n";

		return ret;
	}

	private static class Entry<K, V> {
		private K key;
		private V value;
		private boolean isDeleted;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.isDeleted = false;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public boolean getIsDeleted() {
			return isDeleted;
		}

		public void delete() {
			isDeleted = true;
		}
	}
}