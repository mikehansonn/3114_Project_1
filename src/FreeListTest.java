import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the FreeList
 * 
 * @author matt02
 * @version 9/11/23
 */
public class FreeListTest {
    private FreeList freeList;

    /**
     * Setup the initial Freelist
     * 
     * @throws Exception setup exception
     */
    @Before
    public void setUp() throws Exception {
        freeList = new FreeList(4);

    }

    /**
     * Tests addBlock Method when the initial block is split
     */
    @Test
    public void testAddBlocksuccessfulAllocationWithInitialSplit() {
        int startPosition = freeList.addBlock(2);
        System.out.println(startPosition);
        assertEquals(0, startPosition);
        System.out.println(freeList.toString());

    }

    /**
     * Tests addBlock Method when array is full
     */
    @Test
    public void testAddBlockfillArray() {
        int start = freeList.addBlock(4);
        freeList.addBlock(2);
        System.out.println(freeList.toString());

    }

    /**
     * Test addBlock method when there is not block splitting
     */
    @Test
    public void testAddBlocksuccessfulAllocationWithoutSplit() {
        // Here we request a block of size 
        //2^4, which matches our initial block size, so
        // no splitting occurs
        int startPosition = freeList.addBlock(4);
        assertEquals(0, startPosition);
    }

    /**
     * Test addBlock method upon multiple block splits
     */
    @Test
    public void testAddBlocksuccessfulAllocationAfterInitialSplit() {
        // First we'll allocate a smaller block to force an initial split
        int handle1 = freeList.addBlock(2);

        // Now we'll request another block
        // this will not force a new split, but will
        // use one of the split blocks created earlier
        int handle2 = freeList.addBlock(2);

        // Adding more blocks to test different scenarios
        int handle3 = freeList.addBlock(1);
        int handle4 = freeList.addBlock(1);
        int handle5 = freeList.addBlock(2);

        // Check that the allocations were successful
        // and that the correct blocks were allocated
        assertEquals(0, handle1);
        assertEquals(4, handle2);
        assertEquals(8, handle3);
        assertEquals(10, handle4);
        assertEquals(12, handle5);
    }

    /**
     * Test addBlock when allocation is unsuccessful
     */
    @Test
    public void testAddBlockunsuccessfulAllocation() {
        // Allocate all available blocks
        freeList.addBlock(4);

        // Now, there should be no more blocks left to allocate
        int handle = freeList.addBlock(1);
        assertEquals(-1, handle);
    }

    /**
     * Test DeallocateBlock upon no merge.
     */
    @Test
    public void testDeallocateBlocknoMerge() {
        // Allocate several blocks to 
        // create a scenario where a deallocated block has no
        // free buddies to merge with
        int block1 = freeList.addBlock(2);
        int block2 = freeList.addBlock(2);
        int block3 = freeList.addBlock(1);

        // Deallocate a block that does not have free buddy blocks
        freeList.deallocateBlock(2, block2);

        // Define the expected state of the FreeList after the deallocation
        String expectedOutput = "Freeblock List:\n" + "2: 10\n" + "4: 12 4\n";

        // Check that the FreeList is in the expected state
        assertEquals(expectedOutput, freeList.toString());
        System.out.println(freeList.toString());
    }

    /**
     * Test DeallocateBlock with merge.
     */
    @Test
    public void testDeallocateBlockwithMerge() {
        // Allocate a block
        int block1 = freeList.addBlock(2);

        // Deallocate the block
        freeList.deallocateBlock(2, block1);

        // Define the expected state of the FreeList after the deallocation
        String expectedOutput = "Freeblock List:\n" + "16: 0\n";

        // Check that the FreeList is in the expected state
        assertEquals(expectedOutput, freeList.toString());
    }

    /**
     * Tests doubleMemory on a full freelist
     */
    @Test
    public void testDoubleMemory() {
        String expectedState = "Freeblock List:\n" + "32: 0\n";

        freeList.doubleMemory();

        String newState = freeList.toString();

        System.out.println(freeList.toString());

        assertEquals(expectedState, newState);
    }

    /**
     * Test DeallocateBlock when the freelist is partially filled
     */
    @Test
    public void testDoubleMemoryalternativeCase() {
        freeList.addBlock(2);
        freeList.addBlock(1);

        freeList.doubleMemory();

        String expectedState = 
                "Freeblock List:\n" 
                 + "2: 6\n" + "8: 8\n" + "16: 16\n";

        String newState = freeList.toString();

        System.out.println(freeList.toString());

        assertEquals(expectedState, newState);
    }
    
    /**
     * Test Double merge
     */
    @Test
    public void testMergeBlockArithmeticOperationReplacedWithFirstMember() {
        
        FreeList freeList1 = new FreeList(4);
        // Initial setup to create a scenario 
        //where the doubleMemory method will be invoked
        freeList1.doubleMemory(); 
        freeList1.addBlock(4);
        freeList1.deallocateBlock(4, 0); 
        // Deallocating a block to allow potential merge in doubleMemory method

        // Double the memory to invoke the method where mergedBlock is created
        freeList1.doubleMemory(); 

        String freeListStr = freeList1.toString();
        System.out.println(freeListStr);
        assertTrue(freeListStr.contains("64: 0")); 
        // The merged block should have size 64 (2^6), and start at position 0
    }
    
    /**
     * Test Double merge
     */
    @Test
    public void testMergeBlockArithmeticOperationReplacedWithSecondMember() {
        // Initial setup to create a scenario 
        //where the doubleMemory method will be invoked
        
        FreeList freeList1 = new FreeList(4);
        freeList1.doubleMemory(); 
        freeList1.addBlock(4);
        freeList1.deallocateBlock(4, 16); 
        // Deallocating a block to allow potential merge in doubleMemory method

        // Double the memory to invoke the method where mergedBlock is created
        freeList1.doubleMemory(); 

        String freeListStr = freeList1.toString();
        assertFalse(freeListStr.contains("32: 0")); 
        // Ensure that a block with incorrect size (32) is not created
    }
}