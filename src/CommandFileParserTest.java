import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandFileParserTest {
	private CommandFileParser parser;
	@Before
	public void setUp() throws Exception {
		parser = new CommandFileParser("P1Sample_input.txt");
	}

	@Test
	public void testReadCommands() {
		parser.readCommands();
		assertNotNull(parser);
	} 

}
