package com.job.core.util;
import static org.junit.Assert.*;

import org.junit.Test;
public class TestPropertiesUtil {
	@Test
	public void testGetString(){
		String key = "login.username";
		String value = PropertiesUtil.getString(key);
		
		assertEquals("admin", value);
	}
}
