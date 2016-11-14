package com.job.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.core.constant.JobGroupEnum;
import com.job.core.model.JobLog;
import com.job.core.model.ReturnT;

/**
 * 定时任务日志控制器
 * 
 * @author zengxing
 *
 */
@Controller
@RequestMapping("/joblog")
public class JobLogController {
	@RequestMapping
	public String index(Model model) {
		//model.addAttribute("jobGroup", jobGroup);
		//model.addAttribute("jobName", jobName);
		model.addAttribute("jobGroupList", JobGroupEnum.values());

		return "joblog/index";
	}

	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String jobGroup, String jobName,
			String filterTime) {
		//Date triggerTimeStart = null;
		//Date triggerTimeEnd = null;
		//if (StringUtils.isNotBlank(filterTime)) {
		//	String[] temp = filterTime.split("-");
		//	if (temp != null && temp.length == 2) {
		//		try {
		//			triggerTimeStart = DateUtils.parseDate(temp[0], new String[] { "yyyy-MM-dd HH:mm:ss" });
		//			triggerTimeEnd = DateUtils.parseDate(temp[1], new String[] { "yyyy-MM-dd HH:mm:ss" });
		//		} catch (ParseException e) {
		//		}
		//	}
		//}

		List<JobLog> list = new ArrayList<JobLog>();
		int listSize = 0;

		// package result
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", listSize); // 总记录数
		maps.put("recordsFiltered", listSize); // 过滤后的总记录数
		maps.put("data", list); // 分页列表

		return maps;
	}
	
	@RequestMapping("logDetail")
	public @ResponseBody ReturnT<String> logDetail(int id){
		JobLog log = null;
		//if(log == null){
		//	return new ReturnT<String>(500,"参数异常");
		//}
		return ReturnT.SUCCESS;
	}
	
	@RequestMapping("/logDetailPage")
	public String logDetailPage(int id,Model model){
		ReturnT<String> data = ReturnT.SUCCESS;
		model.addAttribute("result",data);
		
		return "joblog/logdetail";
	}
	
	@RequestMapping("/logKill")
	public @ResponseBody ReturnT<String> logKill(int id){
		JobLog log = null;
		//if(log == null){
		//	return new ReturnT<String>(500,"参数异常");
		//}
		return ReturnT.SUCCESS;
	}
}
