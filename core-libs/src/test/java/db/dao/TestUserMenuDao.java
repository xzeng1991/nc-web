package db.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import db.model.UserMenu;

public class TestUserMenuDao extends TestBaseDao {
	@Autowired
	private UserMenuDao menuDao;

	@Test
	public void testFindOne() {
		int menuId = 1;
		UserMenu userMenu = menuDao.findOne(menuId);

		System.out.println(userMenu);
	}
}
