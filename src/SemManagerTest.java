import java.io.FileNotFoundException;

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
     * @throws FileNotFoundException 
     */
    public void testMInitx() throws FileNotFoundException
    {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        SemManager.main(null);
    }
}

