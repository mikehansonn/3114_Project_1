import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandFileParserTest {
	private CommandFileParser parser;
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testReadCommands() {
		parser.readCommands();
		assertNotNull(parser);
	} 

}
