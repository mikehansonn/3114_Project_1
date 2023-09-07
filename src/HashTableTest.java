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
		hashTable.remove(6);
		hashTable.remove(6);
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
		hashTable.put(17, "pizza");
		hashTable.put(9, "taco");
		hashTable.put(32, "milk");
		hashTable.put(6, "milk");
		
		get = hashTable.get(16);
		assertNull(get);
	}
	
	/*
	 * Test the OpenDSA example to ensure
	 * hashing is working correctly
	 */
	@Test
	public void testPositioning() {
		hashTable = new HashTable<>(8);
		hashTable.put(1, "hello");
		hashTable.put(2, "pizza");
		hashTable.put(3, "kale");
		hashTable.put(10, "check");
		System.out.print(hashTable.toString());
		
		assertNotNull(hashTable);
	}

	/*
	 * Test the OpenDSA example to ensure
	 * hashing is working correctly
	 */
	@Test
	public void testTombstone() {
		hashTable = new HashTable<>(8);
		hashTable.put(1, "hello");
		hashTable.put(2, "pizza");
		hashTable.put(3, "kale");
		hashTable.put(10, "check");
		hashTable.remove(2);
		String remove = hashTable.toString();
		System.out.print(remove);
		
		assertTrue(remove.contains("TOMBSTONE"));
	}
}
