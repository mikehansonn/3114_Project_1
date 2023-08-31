

public class HashTable<Integer, V> {
	private static class Entry<Integer, V> {
		private int key;
		private V value;
		
		public Entry(int key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}
	
	private int capacity; // the total amount we can go
	private Entry<Integer, V>[] table;
	private int size; // the current number of elements inside
	
	public HashTable(int capacity) {
		this.capacity = capacity;
		this.table = new Entry[capacity];
		this.size = 0;
	}

	public void put(int key, V value) {
		if((double)size / capacity >= .5) {
			rehash();
		}

		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while(table[hash1] != null) {
			if(table[hash1].getKey() == key) {
				return;
			}

			hash1 = (hash1 + hash2) % table.length;
		}

		table[hash1] = new Entry<>(key, value);
		size++;
	}

	public V get(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while(table[hash1] != null) {
			if(table[hash1].getKey() == key) {
				return table[hash1].getValue();
			}

			hash1 = (hash1 + hash2) % table.length;
		}

		return null; // if the index is not found
	}

	public void remove(int key) {
		int hash1 = hash1(key);
		int hash2 = hash2(key);

		while(table[hash1] != null) {
			if(table[hash1].getKey() == key) {
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

		for(Entry<Integer, V> entry : smallTable) {
			if(entry != null) {
				put(entry.getKey(), entry.getValue());
			}
		}
	}
}
