import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemManagerTest {
	private MemManager manager;

	@Before
	public void setUp() throws Exception {
		manager = new MemManager(16);
	}

	@Test
	public void testInsert() {
		byte[] data = { 1, 2, 3, 4 };
		Handle handle = manager.insert(data, data.length);
		assertNotNull(handle);
	}

	@Test
	public void testDump() {
		manager.dump();
	}

}
