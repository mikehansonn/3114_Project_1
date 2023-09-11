import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandFileParserTest {
	private CommandFileParser parser;
	@Before
	public void setUp() throws Exception {
		SemManager manager = new SemManager(512, 4);
		parser = new CommandFileParser("P1Sample_input.txt", manager);
	} 
	 
	@Test
	public void testReadCommands() throws Exception {
		String string = parser.readCommands();
		System.out.print(string);
		
		assertTrue(string.contains
				("ID: 1, Title: Overview of HCI Research at VT"));
		assertTrue(string.contains("hashtable"));
		assertTrue(string.contains("blocks"));
	} 
}
