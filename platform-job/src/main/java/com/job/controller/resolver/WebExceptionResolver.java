package com.job.controller.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理系统
 * 
 * @author zengxing
 *
 */
public class WebExceptionResolver implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object param,
			Exception e) {
		logger.error(e.getMessage());
		return null;
	}

}
