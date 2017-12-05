package com.xzeng.web.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzeng.web.dao.UserInfoDao;
import com.xzeng.web.domain.ReturnT;
import com.xzeng.web.domain.UserInfo;

@Controller
public class IndexController {
	@Autowired
	private UserInfoDao userInfoDao;

	@RequestMapping("/toLogin")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public @ResponseBody ReturnT<String> loginDo(HttpSession session, String userName, String password) {

		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			UserInfo user = userInfoDao.findUserInfo(userName);

			if (user != null && user.getPassword().equals(password)) {
				session.setAttribute("loginUser", user);
			}
		} else {
			return new ReturnT<String>(500, "账号或密码错误");
		}
		return ReturnT.SUCCESS;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ReturnT<String> logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return ReturnT.SUCCESS;
	}
}
