import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeListTest {
	
	private FreeList freeList;

	@Before 
    public void setUp() throws Exception {
        freeList = new FreeList(4); // Adjust as necessary
    }
	
	@Test
	public void testAddBlock() {
		freeList.addBlock(2);
		freeList.addBlock(4);
		
		assertNotNull(freeList);
		System.out.println(freeList.toString());
	}

	@Test
	public void testDeallocateBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoubleMemory() {
		freeList.doubleMemory();
		assertNotNull(freeList);
	}

}
