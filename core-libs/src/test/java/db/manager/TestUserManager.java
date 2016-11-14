package db.manager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.SpringContextTestCase;
import db.model.UserInfo;

public class TestUserManager extends SpringContextTestCase {
	@Autowired
	private UserManager userManager;

	@Test
	public void testCheckUser() {
		String userName = "xzeng";
		String password = "pass123";

		UserInfo user = userManager.checkUser(userName, password);

		assertNotNull(user);
	}

	@Test
	public void testFindList() {
		int userType = 1;
		List<UserInfo> userList = userManager.findList(null, userType);

		assertEquals(2, userList.size());
	}
}
