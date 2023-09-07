import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;

public class CommandFileParserTest {
	private CommandFileParser parser;
	@Before
	public void setUp() throws Exception {
		parser = new CommandFileParser("P1Sample_input.txt", null);
	}
	
	@Test
	public void testReadCommands() throws FileNotFoundException {
		parser.readCommands();
		assertNotNull(parser);
	} 
}
