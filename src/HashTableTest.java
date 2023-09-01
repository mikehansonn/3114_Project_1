import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
	HashTable<Integer, String> hashTable;
	
	@Before
	public void setUp() throws Exception {
		hashTable = new HashTable<>(8);
	}

	@Test
	public void testLoadHashTable() {
		hashTable.put(8, "hello");
		hashTable.put(16, "no");
		hashTable.put(3, "yes");
		hashTable.put(3, "do");
		hashTable.put(6, "yo");
		System.out.print(hashTable.toString());
		String get = hashTable.get(16);
		String getNull = hashTable.get(17);
		hashTable.put(10, "norp");
		
		assertNull(getNull);
		assertTrue(get.equals("no"));
		assertNotNull(hashTable);
	}
	
	@Test
	public void testRemove() {
		hashTable.put(1, "hello");
		hashTable.remove(1);
		
		String get = hashTable.get(1);
		assertNull(get);
		
		hashTable.put(8, "hello");
		hashTable.put(16, "pizza");
		hashTable.remove(16);
		hashTable.remove(26);
		
		get = hashTable.get(16);
		assertNull(get);
	}

}
