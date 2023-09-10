import static org.junit.Assert.*;
import org.junit.Test;

public class MemManagerTest {
    
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
        assertEquals(-1, bytesRead); // -1 indicates that the handle is invalid
    }

    @Test
    public void testDoubleSize() {
        MemManager memManager = new MemManager(4);
        byte[] data = "Hello, World!".getBytes();
        int dataSize = data.length;
        
        
        // Insert data into the memory pool that exceeds its initial size
        Handle handle = memManager.insert(data, dataSize);
        
        // Check if the memory pool size has doubled
        assertEquals(16, memManager.getPoolsize());
        
        // Retrieve the data and check if it's correct
        byte[] retrievedData = new byte[dataSize];
        int bytesRead = memManager.get(retrievedData, handle, dataSize);
        
        assertArrayEquals(data, retrievedData);
        assertEquals(dataSize, bytesRead);
    }
}
