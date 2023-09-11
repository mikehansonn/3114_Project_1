import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the handle test
 * 
 * @author mikehanson
 * @version 9/11/23
 */
public class HandleTest {
	private Handle handle;

	/**
	 * Initial setup handle
	 * 
	 * @throws Exception setup exception
	 */
	@Before
	public void setUp() throws Exception {
		handle = new Handle(5, 10);
	}

	/**
	 * Tests the set length
	 */
	@Test
	public void testSetLength() {
		handle.setLength(10);
		int get = handle.getLength();
		assertEquals(get, 10);
	}

	/**
	 * Tests the startPositon
	 */
	@Test
	public void testSetStartPosition() {
		handle.setStartPosition(10);
		int get = handle.getStartPosition();
		assertEquals(get, 10);
	}

	/**
	 * Tests the ToString override
	 */
	@Test
	public void testToString() {
		String string = handle.toString();
		assertNotNull(string);
	}

}
