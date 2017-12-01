package db.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import constants.enums.UserStatus;
import constants.enums.UserType;
import db.dao.UserDao;
import db.model.PageResult;
import db.model.UserInfo;
import db.model.XingzuoInfo;

//@Repository
public class UserManager {
	@Autowired
	private UserDao userDao;

	public void addUser(UserInfo userInfo){
		Random random = new Random();
		int id = random.nextInt(1000) + 3;
		
		userInfo.setUserId(id);
		
		userDao.save(userInfo);
	}
	
	public void removeUser(int userId){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		
		userDao.remove(userInfo);
	}
	
	public void updateUser(UserInfo userInfo){
		userDao.update(userInfo);
	}
	
	public UserInfo findById(int userId) {
		return userDao.findOne(userId);
	}

	// 校验用户的信息
	public UserInfo checkUser(String userName, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("password", password);
		params.put("locked", UserStatus.UNLOCKED.getStatus());

		List<UserInfo> users = userDao.findByParams(params);

		if (CollectionUtils.isEmpty(users)) {
			return null;
		} else {
			return users.get(0);
		}
	}

	/**
	 * 根据用户姓名或用户类型查找用户
	 * 
	 * @param userName
	 * @param userType
	 * @return
	 */
	public List<UserInfo> findList(String userName, Integer userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		if (userType != null) {
			// 找出低权限的用户类型
			List<Integer> userTypes = new ArrayList<Integer>();
			for (UserType item : UserType.values()) {
				if (item.getType() >= userType) {
					userTypes.add(item.getType());
				}
			}
			params.put("userTypes", userTypes);
		}

		List<UserInfo> users = userDao.findByParams(params);

		return users;
	}

	public PageResult<UserInfo> findPage(String userName, Integer userType, int start, int pageSize) {
		List<UserInfo> userList = findList(userName, userType);
		// 组装结果
		PageResult<UserInfo> userInfoResult = new PageResult<UserInfo>();
		userInfoResult.setTotalCount(userList.size());
		int end = start + pageSize;
		if (end > userInfoResult.getTotalCount()) {
			end = userInfoResult.getTotalCount();
		}
		userInfoResult.setItems(userList.subList(start, end));
		userInfoResult.setPageSize(userInfoResult.getItems().size());

		return userInfoResult;
	}
}
