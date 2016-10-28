package util;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class TestXmlUtils {
	@Test
	public void testToXml() {
	}

	private String user;

	@Before
	public void init() {
		user = null;
	}

	@Test(expected = NullPointerException.class)
	public void testUser() {
		assertNotNull(user.toString());
	}
}
