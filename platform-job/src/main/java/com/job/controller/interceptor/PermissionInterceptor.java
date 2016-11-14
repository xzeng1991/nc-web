package com.job.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.job.controller.annotation.PermessionLimit;
import com.job.core.util.CookieUtil;

/**
 * 权限拦截
 * 
 * @author zengxing
 *
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

	public static final String LOGIN_IDENTITY_KEY = "LOGIN_IDENTITY";
	public static final String LOGIN_IDENTITY_VAL = "sdf!121sdf$78sd!8";

	/**
	 * 登陆完，添加cookie
	 * 
	 * @param response
	 * @param ifRemember
	 * @return
	 */
	public static boolean login(HttpServletResponse response, boolean ifRemember) {
		CookieUtil.set(response, LOGIN_IDENTITY_KEY, LOGIN_IDENTITY_VAL, ifRemember);
		logger.info("====== add cookie success. ======");
		return true;
	}

	/**
	 * 登出后，删除cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.remove(request, response, LOGIN_IDENTITY_KEY);
		logger.info("remove cookie success.");
	}

	/**
	 * 通过cookie判断用户是否登陆
	 * 
	 * @param request
	 * @return
	 */
	public static boolean ifLogin(HttpServletRequest request) {
		String indentityInfo = CookieUtil.get(request, LOGIN_IDENTITY_KEY);
		if (indentityInfo == null || !LOGIN_IDENTITY_VAL.equals(indentityInfo)) {
			return false;
		}
		return true;
	}

	/**
	 * 权限校验
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("====== permissioninterceptor start... ======");
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}

		if (!ifLogin(request)) {	// 校验未登陆
			HandlerMethod method = (HandlerMethod) handler;
			PermessionLimit permission = method.getMethodAnnotation(PermessionLimit.class);
			if (permission == null || permission.limit()) {
				throw new Exception("登陆失效.");
			}
		}
		return super.preHandle(request, response, handler);
	}

}
