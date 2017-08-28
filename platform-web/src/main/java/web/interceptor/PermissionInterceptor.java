package web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import service.RedisManager;
import util.CookieUtil;
import web.annotation.PermessionLimit;

/**
 * 权限拦截器
 * 
 * @author zengxing
 *
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	public static final String LOGIN_IDENTITY_KEY = "LOGIN_IDENTITY";

	//@Autowired
	private RedisManager redisManager;

	// 检验用户登陆信息
	private boolean ifLogin(HttpServletRequest request) {
		// 从缓存中取用户ID
		String userId = CookieUtil.getCookieVal(request, LOGIN_IDENTITY_KEY);
		if (userId != null) {
			// 看redis中是否存在用户信息
			String userInfo = redisManager.getValueOps().get(userId.trim());
			if (StringUtils.isNotEmpty(userInfo)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}

		// 当用户没有登陆，检验请求是否需要权限检查
		HandlerMethod method = (HandlerMethod) handler;
		PermessionLimit permission = method.getMethodAnnotation(PermessionLimit.class);
		if ((permission == null || permission.limit()) && !ifLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return false;
		}

		return super.preHandle(request, response, handler);
	}

}
