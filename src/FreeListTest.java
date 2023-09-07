import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeListTest {
	
	private FreeList freeList_noSplit; 
	private FreeList freeList;  

	@Before 
    public void setUp() throws Exception {
		freeList = new FreeList(4); 
		
    }
	
	@Test
	public void testAddBlock_successfulAllocationWithInitialSplit() {
		int startPosition = freeList.addBlock(2);
		assertEquals(0, startPosition);
		
		System.out.println(freeList.toString());
	}
	
	@Test
	public void testAddBlock_successfulAllocationWithoutSplit() {
		// Here we request a block of size 2^4, which matches our initial block size, so no splitting occurs
		int handle = freeList.addBlock(4);
		
		assertEquals(0, handle);
		//System.out.println(freeList.toString());
	}
	 

	@Test
	public void testAddBlock_successfulAllocationAfterInitialSplit() {
	    // First we'll allocate a smaller block to force an initial split
	    int handle1 = freeList.addBlock(2);
	    
	    // Now we'll request another block; this will not force a new split, but will use one of the split blocks created earlier
	    int handle2 = freeList.addBlock(2);
	    
	    // Adding more blocks to test different scenarios
	    int handle3 = freeList.addBlock(1);
	    int handle4 = freeList.addBlock(1);
	    int handle5 = freeList.addBlock(2);
	    
	    // Check that the allocations were successful and that the correct blocks were allocated
	    assertEquals(0, handle1);  // The first block to be allocated should start at position 0
	    assertEquals(4, handle2);  // After the first split, the next block of size 2^2 should start at position 4
	    assertEquals(8, handle3);  // Next free block of size 2^1 should start at position 2
	    assertEquals(10, handle4);  // Next free block of size 2^1 should start at position 6
	    assertEquals(12, handle5);  // The block of size 2^2 should start at position 8 (after another split occurs)

	    //System.out.println(freeList.toString());
	}
 
	
	@Test
	public void testAddBlock_unsuccessfulAllocation() {
		// Allocate all available blocks
		freeList.addBlock(4);
		
		// Now, there should be no more blocks left to allocate
		int handle = freeList.addBlock(1);
		assertEquals(-1, handle);
		//System.out.println(freeList.toString());
	}
	
	
	/*
	@Test
	public void testAddBlock() {
		
		
		
		freeList.addBlock(2); 
		
		
		freeList.addBlock(4);
		 
		assertNotNull(freeList);
		System.out.println(freeList.toString());
	}
	
	@Test
	public void testAddBlockSamePosition() {
		freeList.addBlock(2); 
		freeList.addBlock(2);
		
		assertNotNull(freeList);
		System.out.println(freeList.toString());
	}
	*/

	@Test
	public void testDeallocateBlock() {
		
	}

	@Test
	public void testDoubleMemory() {
		freeList.doubleMemory();
		assertNotNull(freeList);
	}

}
