import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the hashtable
 * 
 * @author mikehanson
 * @version 9/11/23
 */
public class HashTableTest {
    HashTable<Integer, String> hTable;

    /**
     * Setup the initial hashtable
     * 
     * @throws Exception setup exception
     */
    @Before
    public void setUp() throws Exception {
        hTable = new HashTable<>(8);
    }

    /**
     * Tests the losing of the table
     */
    @Test
    public void testLoadHashTable() {
        hTable.insert(8, "hello");
        hTable.insert(16, "no");
        hTable.insert(3, "yes");
        hTable.insert(3, "do");
        hTable.insert(6, "yo");
        hTable.delete(6);
        assertEquals(hTable.getSize(), 3);
        hTable.delete(6);
        hTable.insert(6, "yo");
        System.out.print(hTable.toString());
        String get = hTable.search(16);
        String getNull = hTable.search(17);
        hTable.insert(10, "norp");

        assertNull(getNull);
        assertEquals(get, "no");
        assertNotNull(hTable);
    }

    /**
     * Tests the remove function
     */
    @Test
    public void testRemove() {
        hTable.insert(1, "hello");
        hTable.delete(1);

        String get = hTable.search(1);
        assertEquals(get, null);

        hTable.insert(8, "hello");
        hTable.insert(16, "pizza");
        hTable.delete(16);
        hTable.delete(26);
        hTable.insert(17, "pizza");
        hTable.insert(9, "taco");
        hTable.insert(32, "milk");
        hTable.insert(6, "milk");

        get = hTable.search(16);
        assertEquals(get, null);
    }

    /*
     * Test the OpenDSA example to ensure hashing is working correctly
     */
    @Test
    public void testPositioning() {
        hTable = new HashTable<>(8);
        hTable.insert(1, "hello");
        hTable.insert(2, "pizza");
        hTable.insert(3, "kale");
        hTable.insert(10, "check");
        System.out.print(hTable.toString());

        assertNotNull(hTable);
    }

    /*
     * Test the OpenDSA example to ensure hashing is working correctly
     */
    @Test
    public void testTombstone() {
        hTable = new HashTable<>(8);
        hTable.insert(1, "hello");
        hTable.insert(2, "pizza");
        hTable.insert(3, "kale");
        hTable.insert(10, "check");
        hTable.delete(2);
        String remove = hTable.toString();
        System.out.print(remove);

        assertTrue(remove.contains("TOMBSTONE"));
        hTable.insert(2, "new pizza");
        remove = hTable.toString();
        assertFalse(remove.contains("TOMBSTONE"));
    }

    /**
     * testing some of the edge cases
     */
    @Test
    public void testEdgeCases() {
        hTable = new HashTable<>(8);

        hTable.insert(1, "one");
        hTable.delete(1);
        assertNull(hTable.search(1));

        hTable.insert(2, "two");
        hTable.insert(3, "three");
        hTable.delete(2);
        assertNull(hTable.search(2));
    }

    /**
     * Test the resize function
     */
    @Test
    public void testResizing() {
        hTable = new HashTable<>(2);

        hTable.insert(1, "one");
        hTable.insert(2, "two");
        hTable.insert(3, "three");

        assertEquals("one", hTable.search(1));
        assertEquals("two", hTable.search(2));
        assertEquals("three", hTable.search(3));
    }

    /**
     * Test hash functions
     */
    @Test
    public void testHashFunctions() {
        hTable = new HashTable<>(8);

        int hash1 = hTable.hash1(5);
        int hash2 = hTable.hash2(5);
        assertNotEquals(hash1, hash2);

        int hash3 = hTable.hash1(10);
        int hash4 = hTable.hash2(10);
        assertNotEquals(hash3, hash4);
    }

    /**
     * Test hashtable with different types
     */
    @Test
    public void testTypes() {
        HashTable<Integer, Character> hashCharacter = new HashTable<>(4);
        HashTable<Integer, Integer> hashInteger = new HashTable<>(4);
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

    /**
     * Test the resize function
     */
    @Test
    public void testResize() {
        hTable = new HashTable<>(2);

        hTable.insert(1, "one");
        hTable.insert(2, "two");
        hTable.insert(3, "three");

        assertEquals("one", hTable.search(1));
        assertEquals("two", hTable.search(2));
        assertEquals("three", hTable.search(3));

        assertEquals(8, hTable.getCapacity());
    }

    /**
     * modulo case assertion
     */
    @Test
    public void testH2CalculationWithKeyModuloZero() {
        HashTable<Integer, String> hashTable = new HashTable<>(8);
        int key = 0;

        int h2 = hashTable.hash2(key);

        assertEquals(1, h2);
    }

    /**
     * modulo case assertion
     */
    @Test
    public void testH2CalculationWithEvenKeyAndTableLength() {
        HashTable<Integer, String> hashTable = new HashTable<>(8);
        int key = 6; // Even key and even table length

        int h2 = hashTable.hash2(key);

        assertEquals(1, h2);
    }

    /**
     * modulo case assertion
     */
    @Test
    public void testH2CalculationWithOddKeyAndTableLength() {
        HashTable<Integer, String> hashTable = new HashTable<>(7);
        int key = 5; // Odd key and odd table length

        int h2 = hashTable.hash2(key);

        assertEquals(1, h2);
    }

    /**
     * modulo case assertion
     */
    @Test
    public void testH2CalculationWithKeyModuloTableLengthEqualsOne() {
        HashTable<Integer, String> hashTable = new HashTable<>(10);
        int key = 11; // Key % table.length == 1

        int h2 = hashTable.hash2(key);

        assertEquals(3, h2);
    }

    /**
     * modulo case assertion
     */
    @Test
    public void testH2CalculationWithKeyModuloTableLengthGreaterThanOne() {
        HashTable<Integer, String> hashTable = new HashTable<>(10);
        int key = 7; // Key % table.length > 1

        int h2 = hashTable.hash2(key);

        assertEquals(1, h2);
    }

    /**
     * assert resize update
     */
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

    /**
     * Test probing case
     */
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
