package db.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import constants.enums.UserStatus;
import constants.enums.UserType;
import db.model.UserInfo;

public class TestUserDao extends TestBaseDao {
	@Autowired
	private UserDao userDao;

	@Test
	public void testSave(){
		Random random = new Random();
		int id = random.nextInt(100) + 3;
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(id);
		userInfo.setUserName("test");
		userInfo.setPassword("passtest");
		userInfo.setUserType(UserType.NORMAL.getType());
		userInfo.setLocked(UserStatus.UNLOCKED.getStatus());
		userInfo.setCreateUser("admin");
		
		userDao.save(userInfo);
	}
	
	@Test
	public void testRemove(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(1);
		
		userDao.remove(userInfo);
	}
	
	@Test
	public void testUpdate(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(1);
		userInfo.setUserName("test");
		userInfo.setCredit(2);
		userInfo.setLocked(UserStatus.LOCKED.getStatus());
		userInfo.setPassword("testPass");
		userInfo.setModifyUser("xzeng");
		userInfo.setUserType(UserType.ADMIN.getType());
		
		userDao.update(userInfo);
	}
	
	@Test
	public void testFindOne() {
		int userId = 1;
		UserInfo userInfo = userDao.findOne(userId);

		System.out.println(userInfo);
	}

	@Test
	public void testFindByParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "xzeng");
		params.put("password", "pass123");

		List<UserInfo> users = userDao.findByParams(params);

		assertEquals(1, users.size());
	}
}
