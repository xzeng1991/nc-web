package com.job.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务操作类
 * 
 * @author zengxing
 *
 */
public class HandlerRepository {
	private static Logger logger = LoggerFactory.getLogger(HandlerRepository.class);

	public static final String HANDLER_ADDRESS = "handler_address"; // 处理器地址
	public static final String HANDLER_PARAMS = "handler_params"; // 处理器参数
	public static final String HANDLER_NAME = "handler_name"; // 处理器名称
	public static final String HANDLER_JOB_GROUP = "handler_job_group"; // 处理器任务组
	public static final String HANDLER_JOB_NAME = "handler_job_name"; // 处理器任务名称

}
