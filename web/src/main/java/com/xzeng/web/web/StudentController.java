package com.xzeng.web.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xzeng.web.dao.UserInfoDao;
import com.xzeng.web.domain.ReturnT;
import com.xzeng.web.domain.UserInfo;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private UserInfoDao userInfoDao;

	@RequestMapping("/index")
	public String index() {
		return "student/index";
	}

	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String realName, String phone,
			String filterTime) {
		// page list
		List<UserInfo> list = userInfoDao.pageList(start, length, realName, phone);
		int list_count = userInfoDao.pageListCount(start, length, realName, phone);

		// package result
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", list_count); // 总记录数
		maps.put("recordsFiltered", list_count); // 过滤后的总记录数
		maps.put("data", list); // 分页列表
		return maps;
	}

	@RequestMapping("/add")
	public @ResponseBody ReturnT<String> add(UserInfo userInfo) {
		userInfoDao.addUser(userInfo);
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/update")
	public @ResponseBody ReturnT<String> update(UserInfo userInfo) {
		userInfoDao.updateUser(userInfo);
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/remove")
	public @ResponseBody ReturnT<String> remove(String userId) {
		userInfoDao.removeUser(userId);
		return ReturnT.SUCCESS;
	}
}
