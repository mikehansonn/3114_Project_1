import static org.junit.Assert.*;
import org.junit.Test;


/**
* JUnit tests for the FreeList
* 
* @author mikehanson and matt02
* @version 9/11/23
*/
public class MemManagerTest {
    
	
	/**
	 * Tests Insert method and getLength
	 */
	@Test
    public void testInsertAndLength() {
        MemManager memManager = new MemManager(64); // Initialize with a 64-byte memory pool
        byte[] data = "Hello, World!".getBytes();
        int dataSize = data.length;
        
        // Insert data into the memory pool
        Handle handle = memManager.insert(data, dataSize);
        
        // Check if the length of the inserted data is correct
        assertEquals(dataSize, memManager.length(handle));
    } 
	
	/**
	 * Tests Insert method and getData
	 */
    @Test
    public void testInsertAndGetData() {
        MemManager memManager = new MemManager(64); // Initialize with a 64-byte memory pool
        byte[] data = "Hello, World!".getBytes();
        int dataSize = data.length;
        
        // Insert data into the memory pool
        Handle handle = memManager.insert(data, dataSize);
        
        // Retrieve data from the memory pool
        byte[] retrievedData = new byte[dataSize];
        int bytesRead = memManager.get(retrievedData, handle, dataSize);
        
        // Check if the data retrieved matches the original data
        assertArrayEquals(data, retrievedData);
        assertEquals(dataSize, bytesRead);
    }
    
    /**
	 * Tests insert method upon doubling case
	 */
    @Test
    public void testInsert_DoubleCase() {
    	 MemManager memManager = new MemManager(4);
    	 byte[] data = {1, 2, 3, 4};
         byte[] data1 = {1, 2, 3};
         
         memManager.insert(data, data.length);
         memManager.insert(data1, data1.length);
         
         // Check if the memory pool size has doubled
         assertEquals(8, memManager.getPoolsize());
    }

    /**
	 * Tests the remove() method
	 */
    @Test
    public void testRemove() {
        MemManager memManager = new MemManager(64); // Initialize with a 64-byte memory pool
        byte[] data = "Hello, World!".getBytes();
        int dataSize = data.length;
        
        // Insert data into the memory pool
        Handle handle = memManager.insert(data, dataSize);
        
        // Remove the data from the memory pool
        memManager.remove(handle);
        
        // Try to retrieve the removed data (it should not exist)
        byte[] retrievedData = new byte[dataSize];
        int bytesRead = memManager.get(retrievedData, handle, dataSize);

        // Check if the data was successfully removed
        assertEquals(dataSize, bytesRead);
    }

    /**
	 * Tests DoubleSize method 
	 */
    @Test
    public void testDoubleSize() {
        MemManager memManager = new MemManager(4);
        byte[] data1 = {1, 2, 3, 4, 5};
        
        memManager.insert(data1, data1.length);
        
        // Check if the memory pool size has doubled
        assertEquals(8, memManager.getPoolsize());
    }
    
    /**
	 * Tests dump() method
	 * Most cases tested in Freelist toString() test
	 */
    @Test
    public void testDump() {
    	// Test with no elements
    	MemManager memManager = new MemManager(8);
    	memManager.dump(); 
    	
    	// test with with empty freelist
    	byte[] data1 = {1, 2, 3, 4, 5};
    	memManager.insert(data1, data1.length);
    	memManager.dump();
    }
    
}