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
		assertEquals(get, "no");
		assertNotNull(hashTable);
	}
	
	@Test
	public void testRemove() {
		hashTable.put(1, "hello");
		hashTable.remove(1);
		
		String get = hashTable.get(1);
		assertEquals(get, null);
		
		hashTable.put(8, "hello");
		hashTable.put(16, "pizza");
		hashTable.remove(16);
		hashTable.remove(26);
		hashTable.put(17, "pizza");
		hashTable.put(9, "taco");
		hashTable.put(32, "milk");
		hashTable.put(6, "milk");
		
		get = hashTable.get(16);
		assertEquals(get, null);
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
		hashTable.put(2, "new pizza");
		remove = hashTable.toString();
		assertFalse(remove.contains("TOMBSTONE"));
	}
	
	/**
	 * testing some of the edge cases
	 */
	@Test
	public void testEdgeCases() {
		hashTable = new HashTable<>(8);
		
		hashTable.put(1, "one");
	    hashTable.remove(1);
	    assertNull(hashTable.get(1));

	    hashTable.put(2, "two");
	    hashTable.put(3, "three");
	    hashTable.remove(2);
	    assertNull(hashTable.get(2));
	}
	
	/**
	 * Test the resize function
	 */
	@Test
	public void testResizing() {
	    hashTable = new HashTable<>(2);

	    hashTable.put(1, "one");
	    hashTable.put(2, "two");
	    hashTable.put(3, "three");

	    assertEquals("one", hashTable.get(1));
	    assertEquals("two", hashTable.get(2));
	    assertEquals("three", hashTable.get(3));
	}
	
	/**
	 * Test hash functions
	 */
	@Test
	public void testHashFunctions() {
		hashTable = new HashTable<>(8);
		
		int hash1 = hashTable.hash1(5);
	    int hash2 = hashTable.hash2(5);
	    assertNotEquals(hash1, hash2);

	    int hash3 = hashTable.hash1(10);
	    int hash4 = hashTable.hash2(10);
	    assertNotEquals(hash3, hash4);
	}
	
	/**
	 * Test hashtable with different types
	 */
	@Test
	public void testTypes() {
		HashTable<Integer, Character> hashCharacter = new HashTable<>(4);
		HashTable<Integer, Integer> hashInteger= new HashTable<>(4);
		HashTable<Integer, Handle> hashHandle = new HashTable<>(4);
		Handle handle1 = new Handle(5, 5);
		Handle handle2 = new Handle(5, 5);
		Handle handle3 = new Handle(5, 5);
		
		hashCharacter.put(4, 'A');
		hashCharacter.put(1, 'B');
		hashCharacter.put(7, 'C');
		hashCharacter.remove(7);
		char c = hashCharacter.get(4);
		assertEquals(c, 'A');
		
		hashInteger.put(3, 1);
		hashInteger.put(1, 2);
		hashInteger.put(5, 4);
		hashInteger.remove(7);
		int i = hashInteger.get(5);
		assertEquals(i, 4);
		
		hashHandle.put(3, handle1);
		hashHandle.put(1, handle2);
		hashHandle.put(5, handle3);
		hashHandle.remove(3);
		Handle h = hashHandle.get(5);
		assertEquals(h.getLength(), 5);
		assertEquals(h.getStartPosition(), 5);
	}
}
