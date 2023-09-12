import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the commandfileparser
 * 
 * @author mikehanson
 * @version 9/11/23
 */
public class CommandFileParserTest {
    private CommandFileParser parser;

    /**
     * Sets up the parser for tests
     * 
     * @throws Exception if the file is not found
     */
    @Before
    public void setUp() throws Exception {
        SemManager manager = new SemManager(512, 4);
        parser = new CommandFileParser("P1Sample_input.txt", manager);
    }

    /**
     * The test to read the commands, 
     * and then check to see if we have what we are
     * supposed to
     * 
     * @throws Exception if the file is not found
     */
    @Test
    public void testReadCommands() throws Exception {
        String string = parser.readCommands();
        System.out.print(string);

        assertTrue(string.contains(
                "ID: 1, Title: Overview of HCI Research at VT"));
        assertTrue(string.contains("hashtable"));
        assertTrue(string.contains("blocks"));
    }
}
