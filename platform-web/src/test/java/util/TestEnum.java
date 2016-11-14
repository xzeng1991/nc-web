package util;

import org.junit.Test;

import constants.enums.UserType;

public class TestEnum {
	@Test
	public void test() {
		for (UserType item : UserType.values()) {
			System.out.println(item.getType() + "---" + item.name());
		}
	}
}
