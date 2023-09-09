import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandFileParserTest {
	private CommandFileParser parser;
	@Before
	public void setUp() throws Exception {
		parser = new CommandFileParser("P1Sample_input.txt", null);
	} 
	 
	@Test
	public void testReadCommands() throws Exception {
		String string = parser.readCommands();
		System.out.print(string);
		
		assertTrue(string.contains
				("ID: 1, Title: Overview of HCI Research at VT"));
		assertTrue(string.contains("hashtable"));
		assertTrue(string.contains("blocks"));
		assertTrue(string.contains("insert"));
		assertTrue(string.contains("delete"));
		assertTrue(string.contains("search"));
		assertTrue(string.contains("print"));
	} 
}
