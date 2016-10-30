package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.domain.UserInfo;
import repository.UserDao;

@Repository
public class UserManager {
	@Autowired
	private UserDao userDao;
	
	public UserInfo findById(int userId){
		return userDao.findOne(userId);
	}
}
