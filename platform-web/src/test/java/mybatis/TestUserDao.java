package mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import model.domain.UserInfo;
import repository.UserDao;

public class TestUserDao extends TestBaseDao{
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testFindOne(){
		int userId = 1;
		UserInfo userInfo = userDao.findOne(userId);
		
		System.out.println(userInfo);
	}
}
