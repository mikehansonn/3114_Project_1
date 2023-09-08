/**
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */
public class MemManagerTest {
	
	private MemManager memManager;
	private MemManager memManagerSmall;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memManager = new MemManager(64);
		memManagerSmall = new MemManager(8);
    }
	

	@Test
    public void testInsert() {
        byte[] data = {1, 2, 3, 4, 5};
        
        Handle handle = memManagerSmall.insert(data, data.length);
       
        assertEquals(5, handle.getLength());
        
        memManagerSmall.insert(data, data.length);
        
        // test double case
        
    }


}
