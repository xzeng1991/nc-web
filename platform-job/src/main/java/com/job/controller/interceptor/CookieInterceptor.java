package com.job.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CookieInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(CookieInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("====== CookieInterceptor start...======");
		super.postHandle(request, response, handler, modelAndView);
	}

}
