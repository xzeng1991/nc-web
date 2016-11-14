package com.job.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronExpression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.core.constant.JobGroupEnum;
import com.job.core.model.JobInfo;
import com.job.core.model.ReturnT;

/**
 * 定时任务信息控制器
 * 
 * @author zengxing
 *
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

	@RequestMapping
	public String index(Model model) {
		// 添加任务组列表
		model.addAttribute("jobGroupList", JobGroupEnum.values());
		return "jobinfo/index";
	}

	/**
	 * 分页查询定时任务信息
	 * 
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobName
	 * @param filterTime
	 * @return
	 */
	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String jobGroup, String jobName,
			String filterTime) {
		List<JobInfo> list = new ArrayList<JobInfo>();
		int listSize = 0;
		// TODO fill job info

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", listSize);
		maps.put("recordsFiltered", listSize);
		maps.put("data", list);

		return maps;
	}

	/**
	 * 新增一个任务
	 * 
	 * @param jobInfo
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody ReturnT<String> add(JobInfo jobInfo) {
		if (JobGroupEnum.match(jobInfo.getJobGroup()) == null) {
			return new ReturnT<String>(500, "请选择任务组");
		}
		if (StringUtils.isBlank(jobInfo.getJobName())) {
			return new ReturnT<String>(500, "请输入“任务名”");
		}
		if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
			return new ReturnT<String>(500, "“corn”不合法");
		}
		if (StringUtils.isBlank(jobInfo.getJobDesc())) {
			return new ReturnT<String>(500, "请输入“任务描述”");
		}
		// if (StringUtils.isBlank(handler_address)) {
		// return new ReturnT<String>(500, "请输入“执行器地址”");
		// }
		// if (jobInfo.getGlueSwitch() == 0 &&
		// StringUtils.isBlank(handler_name)) {
		// return new ReturnT<String>(500, "请输入“jobHandler”");
		// }
		if (StringUtils.isBlank(jobInfo.getAuthor())) {
			return new ReturnT<String>(500, "请输入“负责人”");
		}
		if (StringUtils.isBlank(jobInfo.getAlarmEmail())) {
			return new ReturnT<String>(500, "请输入“报警邮件”");
		}
		if (jobInfo.getAlarmThreshold() < 0) {
			jobInfo.setAlarmThreshold(0);
		}

		// try{
		// if(DynamicSchedulerUtil.checkExists(jobName,jobGroup)){
		// return new ReturnT<String>(500, "此任务已存在，请更换任务组或任务名");
		// }
		// }catch(SchedluerException e){
		// return new ReturnT<String>(500, "此任务已存在，请更换任务组或任务名");
		// }

		HashMap<String, String> jobDataMap = new HashMap<String, String>();
		// jobDataMap.put(HandlerRepository.HANDLER_ADDRESS, value)
		// jobDataMap.put(HandlerRepository.HANDLER_NAME, value)
		// jobDataMap.put(HandlerRepository.HANDLER_PARAMS, value)

		// TODO jobinfo save to db

		// TODO add job to quartz
		return ReturnT.FAIL;
	}

	@RequestMapping("/reschedule")
	public @ResponseBody ReturnT<String> reschedule(JobInfo jobInfo) {
		if (JobGroupEnum.match(jobInfo.getJobGroup()) == null) {
			return new ReturnT<String>(500, "请选择任务组");
		}
		if (StringUtils.isBlank(jobInfo.getJobName())) {
			return new ReturnT<String>(500, "请输入“任务名”");
		}
		if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
			return new ReturnT<String>(500, "“corn”不合法");
		}
		if (StringUtils.isBlank(jobInfo.getJobDesc())) {
			return new ReturnT<String>(500, "请输入“任务描述”");
		}
		// if (StringUtils.isBlank(handler_address)) {
		// return new ReturnT<String>(500, "请输入“执行器地址”");
		// }
		// if (jobInfo.getGlueSwitch() == 0 &&
		// StringUtils.isBlank(handler_name)) {
		// return new ReturnT<String>(500, "请输入“jobHandler”");
		// }
		if (StringUtils.isBlank(jobInfo.getAuthor())) {
			return new ReturnT<String>(500, "请输入“负责人”");
		}
		if (StringUtils.isBlank(jobInfo.getAlarmEmail())) {
			return new ReturnT<String>(500, "请输入“报警邮件”");
		}
		if (jobInfo.getAlarmThreshold() < 0) {
			jobInfo.setAlarmThreshold(0);
		}

		// parse jobDataMap
		HashMap<String, String> jobDataMap = new HashMap<String, String>();
		// jobDataMap.put(HandlerRepository.HANDLER_ADDRESS, handler_address);
		// jobDataMap.put(HandlerRepository.HANDLER_NAME, handler_name);
		// jobDataMap.put(HandlerRepository.HANDLER_PARAMS, handler_params);

		// TODO add jobInfo to db
		// TODO reschedule job
		return ReturnT.FAIL;
	}

	@RequestMapping("/remove")
	public @ResponseBody ReturnT<String> remove(String jobGroup, String jobName) {
		// TODO remove jobinfo
		return ReturnT.FAIL;
	}

	@RequestMapping("/pause")
	public @ResponseBody ReturnT<String> pause(String jobGroup, String jobName) {
		// TODO pause the job
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/resume")
	public @ResponseBody ReturnT<String> resume(String jobGroup, String jobName) {
		// TODO resume the job
		return ReturnT.SUCCESS;
	}
}
