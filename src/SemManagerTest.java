import student.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;

/**
 * @author mikehanson
 * @version 9/11/23
 */
public class SemManagerTest extends TestCase {
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restore original
     */
    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Get code coverage of the class declaration.
     * 
     * @throws Exception
     */
    public void testMInitx() throws Exception {
        String[] args = { "64", "4", "P2Sample_input.txt" };
        SemManager sem = new SemManager(1, 2);
        assertNotNull(sem);
        SemManager.main(args);
    }

    /**
     * TEst the individual methods
     * 
     * @throws Exception file not found
     */
    public void testIndividualMethods() throws Exception {
        String[] args = { "128", "2", "P3Test_input.txt" };
        SemManager sem = new SemManager(128, 1);
        SemManager.main(args);
        String printedOutput = outContent.toString();
        System.out.println(printedOutput);
        String expectedOutput = "Memory pool expanded to 256 bytes\r\n" 
                + "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90," 
                + " X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present" 
                + " an overview of HCI research at VT\r\n"
                + "Keywords: HCI, Computer_Science," 
                + " VT, Virginia_Tech\r\n" + "Size: 173\r\n"
                + "Insert FAILED - There is already " 
                + "a record with ID 1\r\n" 
                + "Memory pool expanded to 512 bytes\r\n"
                + "Hash Table expanded to 4 records\r\n" 
                + "Successfully inserted record with ID 2\r\n"
                + "ID: 2, Title: Computational Biology" 
                + " and Bioinformatics in CS at Virginia Tech\r\n"
                + "Date: 0610071600, Length: " 
                + "60, X: 20, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to   " 
                + "bioinformatics and computation biology\r\n"
                + "Keywords: Bioinformatics, computation_biology," 
                + " Biology, Computer_Science, VT, Virginia_Tech\r\n"
                + "Size: 244\r\n" 
                + "Memory pool expanded to 1024 bytes\r\n" 
                + "Hash Table expanded to 8 records\r\n"
                + "Successfully inserted record with ID 3\r\n" 
                + "ID: 3, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, "
                + "X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the   " 
                + "   Computing systems research at      VT\r\n"
                + "Keywords: high_performance_computing," 
                + " grids, VT, computer, science\r\n" + "Size: 192\r\n"
                + "Found record with ID 3:\r\n" 
                + "ID: 3, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30," 
                + " X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the  " 
                + "    Computing systems research at      VT\r\n"
                + "Keywords: high_performance_computing," 
                + " grids, VT, computer, science\r\n" 
                + "Hashtable:\r\n"
                + "1: 1\r\n" 
                + "2: 2\r\n" 
                + "3: 3\r\n" 
                + "total records: 3\r\n" 
                + "Freeblock List:\r\n" 
                + "256: 768\r\n"
                + "Record with ID 2 successfully " 
                + "deleted from the database\r\n" + "";

        assertEquals(printedOutput, expectedOutput);
    }
}
