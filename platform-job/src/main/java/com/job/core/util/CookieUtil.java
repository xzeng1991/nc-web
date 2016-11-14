package com.job.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie常用操作
 * 
 * @author zengxing
 *
 */
public class CookieUtil {
	// 默认缓存时间，单位／秒，2H
	private static final int COOKIE_MAX_AGE = 2 * 60 * 60;
	// 保存路径，根路径
	private static final String COOKIE_PATH = "/";

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param ifRemember
	 */
	public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
		int age = COOKIE_MAX_AGE;
		if (ifRemember) {
			age = COOKIE_MAX_AGE;
		} else {
			age = -1;
		}

		set(response, key, value, age, COOKIE_PATH);
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param maxAge
	 * @param path
	 */
	public static void set(HttpServletResponse response, String key, String value, int maxAge, String path) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie值
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String get(HttpServletRequest request, String key) {
		Cookie cookie = getCookie(request, key);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param key
	 */
	public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie cookie = getCookie(request, key);
		if (cookie != null) {
			set(response, key, "", 0, COOKIE_PATH);
		}
	}

	/**
	 * 查找cookie
	 * @param request
	 * @param key
	 * @return
	 */
	private static Cookie getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie;
				}
			}
		}
		return null;
	}
}
