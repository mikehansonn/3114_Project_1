import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeListTest {
	
	private FreeList freeList;  

	@Before 
    public void setUp() throws Exception {
		freeList = new FreeList(4);  
		
    }
	 
	@Test
	public void testAddBlock_successfulAllocationWithInitialSplit() {
		int startPosition = freeList.addBlock(2);
		System.out.println(startPosition);
		assertEquals(0, startPosition);
		System.out.println(freeList.toString());
		
	}
	
	@Test
	public void testAddBlock_fillArray() {
		int start = freeList.addBlock(4);
		freeList.addBlock(2);
		System.out.println(freeList.toString());
		
	}
	
	@Test
	public void testAddBlock_successfulAllocationWithoutSplit() {
		// Here we request a block of size 2^4, which matches our initial block size, so no splitting occurs
		int startPosition = freeList.addBlock(4);
		
		assertEquals(0, startPosition);
		
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
	    assertEquals(0, handle1);  
	    assertEquals(4, handle2);  
	    assertEquals(8, handle3);  
	    assertEquals(10, handle4);  
	    assertEquals(12, handle5);  

	    //System.out.println(freeList.toString());
	}
 
	@Test
	public void testAddBlock_unsuccessfulAllocation() {
		// Allocate all available blocks
		freeList.addBlock(4);
		
		// Now, there should be no more blocks left to allocate
		int handle = freeList.addBlock(1);
		assertEquals(-1, handle);
		
	}
	
	
	@Test
	public void testDeallocateBlock_noMerge() {
	    // Step 1: Allocate several blocks to create a scenario where a deallocated block has no free buddies to merge with
	    int block1 = freeList.addBlock(2);
	    int block2 = freeList.addBlock(2);
	    int block3 = freeList.addBlock(1);
	  
	    // Step 2: Deallocate a block that does not have free buddy blocks
	    freeList.deallocateBlock(2, block2);

	    // Step 3: Define the expected state of the FreeList after the deallocation
	    String expectedOutput = "Freeblock List:\n" +
						                "2: 10\n"   +
						                "4: 4 12\n";

	    // Step 4: Check that the FreeList is in the expected state
	    assertEquals(expectedOutput, freeList.toString());
		System.out.println(freeList.toString());
	}
	
	@Test
    public void testDeallocateBlock_withMerge() {
        // Allocate a block
        int block1 = freeList.addBlock(2);

        // Deallocate the block
        freeList.deallocateBlock(2, block1);

        // Define the expected state of the FreeList after the deallocation
        String expectedOutput = "Freeblock List:\n" +
                                         "16: 0\n";
				                
                                
        // Check that the FreeList is in the expected state
        assertEquals(expectedOutput, freeList.toString());
    }	
	

	@Test
	public void testDoubleMemory() {
	    String initialState = freeList.toString();

	    String expectedState = "Freeblock List:\n" + 
                "32: 0\n";

	    freeList.doubleMemory();

	    String newState = freeList.toString();
	    
		System.out.println(freeList.toString());

	    assertEquals(expectedState, newState);
	    assertNotEquals(initialState, newState);
	}
	
	@Test
	public void testDoubleMemory_alternativeCase() {
		freeList.addBlock(2);  // Allocating a block of size 16 (2^4)
	    freeList.addBlock(1);  // Allocating another block of size 16 (2^4)

	    freeList.doubleMemory();


	    
		System.out.println(freeList.toString());

	    
	}


}