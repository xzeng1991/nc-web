package web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import db.manager.UserManager;
import db.model.UserInfo;
import model.ReturnT;
import service.RedisManager;
import util.CookieUtil;
import web.annotation.PermessionLimit;
import web.interceptor.PermissionInterceptor;

@Controller
public class MainController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private RedisManager redisManager;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/toLogin")
	@PermessionLimit(limit = false)
	public String toLogin(HttpServletRequest request) {
		if (ifLogin(request)) {
			return "index";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public @ResponseBody ReturnT<String> loginDo(HttpServletResponse response, String userName, String password,
			String ifRemember) {
		boolean ifRem = false;
		if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
			ifRem = true;
		}

		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			UserInfo user = userManager.checkUser(userName, password);

			if (user != null) {
				// 登入成功 设置cookie
				CookieUtil.set(response, PermissionInterceptor.LOGIN_IDENTITY_KEY, user.getUserId().toString(), ifRem);
				CookieUtil.set(response, "userName", user.getUserName(), ifRem);
				// 登入成功 将用户信息放入缓存
				redisManager.getValueOps().set(user.getUserId().toString(), JSON.toJSONString(user));
			}
		} else {
			return new ReturnT<String>(500, "账号或密码错误");
		}
		return ReturnT.SUCCESS;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public @ResponseBody ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
		// 清cookie缓存
		Cookie cookie = CookieUtil.remove(request, response, PermissionInterceptor.LOGIN_IDENTITY_KEY);
		CookieUtil.remove(request, response, "userName");
		String userId = cookie.getValue();
		// 清redis缓存
		redisManager.getRedisOps().delete(userId);

		return ReturnT.SUCCESS;
	}

	// 检验用户登陆信息
	private boolean ifLogin(HttpServletRequest request) {
		// 从缓存中取用户ID
		String userId = CookieUtil.getCookieVal(request, PermissionInterceptor.LOGIN_IDENTITY_KEY);
		if (userId != null) {
			// 看redis中是否存在用户信息
			String userInfo = redisManager.getValueOps().get(userId.trim());
			if (StringUtils.isNotEmpty(userInfo)) {
				return true;
			}
		}
		return false;
	}
}
