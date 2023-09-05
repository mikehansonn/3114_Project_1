import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandleTest {
	private Handle handle;
	@Before
	public void setUp() throws Exception {
		handle = new Handle(5, 10);
	}

	@Test
	public void testSetLength() {
		handle.setLength(10);
		int get = handle.getLength();
		assertEquals(get, 10);
	}

	@Test
	public void testSetStartPosition() {
		handle.setStartPosition(10);
		int get = handle.getStartPosition();
		assertEquals(get, 10);
	}

	@Test
	public void testToString() {
		String string = handle.toString();
		assertNotNull(string);
	}

}
