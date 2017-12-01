/**
 * 
 */
package web.interceptor;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: LoginProtectInterceptor
 * @Description:登录保护
 * @author: xzeng
 * @date: 2017年12月1日 下午5:09:44
 * @Copyright: 2017 www.yunqi.com Inc. All rights reserved.
 */
public class LoginProtectInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(LoginProtectInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String ctxPath = request.getContextPath();
		HttpSession session = request.getSession();

		boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));

		String uri = request.getRequestURI();
		uri = uri.substring(ctxPath.length());

		if (uri.startsWith("/")) {

			Object user = session.getAttribute("");
			if (user != null) {
				return true;
			} else {
				if (isAjax) {
					Map<String, String> result = new HashMap<String, String>();
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(JSON.toJSONString(result));
				} else {
					response.sendRedirect(ctxPath + "/wechat/login.do?returnUrl=" + URLEncoder.encode(uri, "UTF-8"));
				}
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}