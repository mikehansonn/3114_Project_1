import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * @throws Exception 
     */
    public void testMInitx() throws Exception
    {
    	String[] args = { "64", "4", "P2Sample_input.txt"};
        SemManager sem = new SemManager(1, 2);
        assertNotNull(sem);
        SemManager.main(args);
    }
}

