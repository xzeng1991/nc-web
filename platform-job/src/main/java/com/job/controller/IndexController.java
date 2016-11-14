package com.job.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.controller.annotation.PermessionLimit;
import com.job.controller.interceptor.PermissionInterceptor;
import com.job.core.model.ReturnT;
import com.job.core.util.PropertiesUtil;

@Controller
public class IndexController {
	@RequestMapping("/index")
	@PermessionLimit(limit = false)
	public String index(HttpServletRequest request) {
		if (!PermissionInterceptor.ifLogin(request)) { // 校验未登陆，跳转登陆页面
			return "redirect:/toLogin";
		}
		return "redirect:/jobinfo";
	}

	@RequestMapping("/toLogin")
	@PermessionLimit(limit = false)
	public String toLogin(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public @ResponseBody ReturnT<String> loginDo(HttpServletResponse response, String userName, String password,
			String ifRemember) {
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)
				&& userName.equals(PropertiesUtil.getString("login.username"))
				&& password.equals(PropertiesUtil.getString("login.password"))) {
			boolean ifRem = false;
			if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
				ifRem = true;
			}
			PermissionInterceptor.login(response, ifRem);
		} else {
			return new ReturnT<String>(500, "账号或密码错误");
		}
		return ReturnT.SUCCESS;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public @ResponseBody ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response,
			String userName, String password, String ifRemember) {
		if (PermissionInterceptor.ifLogin(request)) {
			PermissionInterceptor.logout(request, response);
		}
		return ReturnT.SUCCESS;
	}

	@RequestMapping("help")
	public String help() {
		return "help";
	}
}
