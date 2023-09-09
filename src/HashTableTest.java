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
		hashTable.insert(8, "hello");
		hashTable.insert(16, "no");
		hashTable.insert(3, "yes");
		hashTable.insert(3, "do");
		hashTable.insert(6, "yo");
		hashTable.delete(6);
		assertEquals(hashTable.getSize(), 3);
		hashTable.delete(6);
		hashTable.insert(6, "yo");
		System.out.print(hashTable.toString());
		String get = hashTable.search(16);
		String getNull = hashTable.search(17);
		hashTable.insert(10, "norp");
		
		assertNull(getNull);
		assertEquals(get, "no");
		assertNotNull(hashTable);
	}
	
	@Test
	public void testRemove() {
		hashTable.insert(1, "hello");
		hashTable.delete(1);
		
		String get = hashTable.search(1);
		assertEquals(get, null);
		
		hashTable.insert(8, "hello");
		hashTable.insert(16, "pizza");
		hashTable.delete(16);
		hashTable.delete(26);
		hashTable.insert(17, "pizza");
		hashTable.insert(9, "taco");
		hashTable.insert(32, "milk");
		hashTable.insert(6, "milk");
		
		get = hashTable.search(16);
		assertEquals(get, null);
	}
	
	/*
	 * Test the OpenDSA example to ensure
	 * hashing is working correctly
	 */
	@Test
	public void testPositioning() {
		hashTable = new HashTable<>(8);
		hashTable.insert(1, "hello");
		hashTable.insert(2, "pizza");
		hashTable.insert(3, "kale");
		hashTable.insert(10, "check");
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
		hashTable.insert(1, "hello");
		hashTable.insert(2, "pizza");
		hashTable.insert(3, "kale");
		hashTable.insert(10, "check");
		hashTable.delete(2);
		String remove = hashTable.toString();
		System.out.print(remove);
		
		assertTrue(remove.contains("TOMBSTONE"));
		hashTable.insert(2, "new pizza");
		remove = hashTable.toString();
		assertFalse(remove.contains("TOMBSTONE"));
	}
	
	/**
	 * testing some of the edge cases
	 */
	@Test
	public void testEdgeCases() {
		hashTable = new HashTable<>(8);
		
		hashTable.insert(1, "one");
	    hashTable.delete(1);
	    assertNull(hashTable.search(1));

	    hashTable.insert(2, "two");
	    hashTable.insert(3, "three");
	    hashTable.delete(2);
	    assertNull(hashTable.search(2));
	}
	
	/**
	 * Test the resize function
	 */
	@Test
	public void testResizing() {
	    hashTable = new HashTable<>(2);

	    hashTable.insert(1, "one");
	    hashTable.insert(2, "two");
	    hashTable.insert(3, "three");

	    assertEquals("one", hashTable.search(1));
	    assertEquals("two", hashTable.search(2));
	    assertEquals("three", hashTable.search(3));
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
		
		hashCharacter.insert(4, 'A');
		hashCharacter.insert(1, 'B');
		hashCharacter.insert(7, 'C');
		hashCharacter.delete(7);
		char c = hashCharacter.search(4);
		assertEquals(c, 'A');
		
		hashInteger.insert(3, 1);
		hashInteger.insert(1, 2);
		hashInteger.insert(5, 4);
		hashInteger.delete(7);
		int i = hashInteger.search(5);
		assertEquals(i, 4);
		
		hashHandle.insert(3, handle1);
		hashHandle.insert(1, handle2);
		hashHandle.insert(5, handle3);
		hashHandle.delete(3);
		Handle h = hashHandle.search(5);
		assertEquals(h.getLength(), 5);
		assertEquals(h.getStartPosition(), 5);
	}
	
	@Test
	public void testResize() {
		hashTable = new HashTable<>(2);
		
		hashTable.insert(1, "one");
	    hashTable.insert(2, "two");
	    hashTable.insert(3, "three");
	    
	    assertEquals("one", hashTable.search(1));
	    assertEquals("two", hashTable.search(2));
	    assertEquals("three", hashTable.search(3));

	    assertEquals(8, hashTable.getCapacity());
	}
	
	@Test
	public void testH2CalculationWithKeyModuloZero() {
	    HashTable<Integer, String> hashTable = new HashTable<>(8);
	    int key = 0;

	    int h2 = hashTable.hash2(key);

	    assertEquals(1, h2);
	}

	@Test
	public void testH2CalculationWithEvenKeyAndTableLength() {
	    HashTable<Integer, String> hashTable = new HashTable<>(8);
	    int key = 6; // Even key and even table length

	    int h2 = hashTable.hash2(key);

	    assertEquals(1, h2);
	}

	@Test
	public void testH2CalculationWithOddKeyAndTableLength() {
	    HashTable<Integer, String> hashTable = new HashTable<>(7);
	    int key = 5; // Odd key and odd table length

	    int h2 = hashTable.hash2(key);

	    assertEquals(1, h2);
	}

	@Test
	public void testH2CalculationWithKeyModuloTableLengthEqualsOne() {
	    HashTable<Integer, String> hashTable = new HashTable<>(10);
	    int key = 11; // Key % table.length == 1

	    int h2 = hashTable.hash2(key);

	    assertEquals(3, h2);
	}

	@Test
	public void testH2CalculationWithKeyModuloTableLengthGreaterThanOne() {
	    HashTable<Integer, String> hashTable = new HashTable<>(10);
	    int key = 7; // Key % table.length > 1

	    int h2 = hashTable.hash2(key);

	    assertEquals(1, h2);
	}
	
	@Test
	public void testCapacityUpdateOnResize() {
	    HashTable<Integer, String> hashTable = new HashTable<>(8);

	    // Add some elements that trigger a resize
	    hashTable.insert(1, "one");
	    hashTable.insert(2, "two");
	    hashTable.insert(3, "three");
	    hashTable.insert(4, "four");
	    hashTable.insert(5, "five");

	    // Verify that the capacity has been correctly updated
	    assertEquals(16, hashTable.getCapacity());
	}
	
	@Test
	public void testProbingDuringInsertion() {
	    HashTable<Integer, String> hashTable = new HashTable<>(8);

	    // Add elements that will trigger probing
	    hashTable.insert(1, "one");
	    hashTable.insert(9, "nine");
	    hashTable.insert(17, "seventeen");

	    // Attempt to insert an element with a key that requires probing
	    int keyToInsert = 2; // Key that requires probing
	    String valueToInsert = "two";
	    
	    // Insert the element
	    hashTable.insert(keyToInsert, valueToInsert);

	    // Verify that the element has been inserted at the expected position
	    assertEquals(valueToInsert, hashTable.search(keyToInsert));
	}

}
